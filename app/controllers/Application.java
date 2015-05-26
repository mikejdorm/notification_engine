package controllers;

import java.util.*;

import play.mvc.*;
import play.data.DynamicForm;
import play.Logger;
import play.Logger.ALogger;

import com.avaje.ebean.Ebean;

import com.google.common.collect.Iterables;

import models.NotificationFactory;
import models.SMSMessage;
import models.VoiceMessage;
import models.Notification;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;


/**
 *
 * Processes requests and sends notifications to a specified phone number.
 * Also, processes responses from the Twilio API regarding updates on
 * prior API calls. The class is initialized with one or more from numbers
 * defined in the application.conf file.
 *
 * Examples of API calls
 *
 * Text Message (SMS):
 *  http://localhost:9000/messages?PhoneNumber=17734845887&MessageType=SMS&Body=%22Hello%22
 *
 * Phone Call:
 *  http://localhost:9000/messages?PhoneNumber=17734845887&MessageType=PHONE_CALL
 *
 **/ 
public class Application extends Controller {


    /**
     * A Twilio account can have multiple from numbers associated with it. This allows
     * an account to send a large amount of calls within having to queue calls. The twilio
     * numbers associated with an account are included in the application.conf as an array of
     * string values.
     *
     */
    public static final List<String> FROM_NUMBERS = play.Play.application().configuration().getStringList("twilio.twilioNumbers");
    public static final String PHONE_GREETING = play.Play.application().configuration().getString("phoneGreeting");
    /**
     *
     * The iterables.cycle iterator cycles through a list indefinitely. The benefit of this is if a large
     * amount of notifications occur, the notifications are distributed over all the numbers defined
     * in the application.conf.
     */
    private static Iterator<String> fromNumbers = Iterables.cycle(FROM_NUMBERS).iterator();

    private static NotificationFactory notificationFactory = NotificationFactory.getInstance();
    private static final ALogger logger = Logger.of(Application.class);

    public static Result index() {
        return ok("Welcome to the notification engine.");
    }

    /**
     *  Processes a message request to the API. The message instructions are
     *  query strings passed in the URL. This method takes the query string values
     *  and sends them to a NotificationFactory instance which creates the call
     *  to the Twilio API and creates a Notification object.
     *
     * @return ok if message is processed badRequest if URL was incorrect.
     * @throws TwilioRestException if the number, accountID parameters are incorrect.
     *
     **/
    public static Result messages(){
        Map<String, String[]> queryStrs = Controller.request().queryString();
        try{
            Notification notification = notificationFactory.create(fromNumbers.next(), queryStrs);
            Ebean.save(notification);
            return ok(notification.toString());
        }
        catch(Exception e){
            logger.error(e.toString());
            return badRequest(e.toString());
        }
    }


    /**
     * 
     * Returns an XML response with the message for voice calls. 
     * The XML format follows the Twilio layout for producing voice
     * messages. 
     * 
     * @return an XML response Result with the specified 
     **/
    public static Result voice() throws TwiMLException {
        TwiMLResponse response = new TwiMLResponse();
        Say message = new Say(PHONE_GREETING);
        message.setVoice("woman");
        response.append(message);
        response().setContentType("text/xml");
        return ok(response.toXML());
    }


    /**
     *  Processes responses to the application CallStatus 
     *  URL specified in the request to the Twilio REST API for
     *  voice calls. 
     * 
     * @return ok after message is processed
     * 
     **/
    public static Result callStatus() {
        DynamicForm df = play.data.Form.form().bindFromRequest();
        VoiceMessage vm = VoiceMessage.find.byId(df.get("CallSid"));
        if(vm!=null){
            vm.setStatus(df.get("CallStatus"));
        }
        Ebean.update(vm);
        return ok();
    }
    
    /**
     *  The fallback URL specified in the Twilio request
     *  for voice calls. 
     * 
     *  @return ok after message is processed. 
     **/
    public static Result fallback(){
        DynamicForm df = play.data.Form.form().bindFromRequest();
        return ok();
    }

    /**
     *  Processes responses from Twilio on the status of
     *  a SMS message. The status of the message record
     *  in the database is updated to reflect the change 
     *  in status. 
     * 
     * @return ok after message is processed. 
     *
     *
     * curl --verbose --data "SmsSid=SM516c2560524847108d84b366119ce673&SmsStatus=manual" http://42a2173c.ngrok.io/status
     *
     **/
    public static Result status(){
        DynamicForm df = play.data.Form.form().bindFromRequest();
        SMSMessage sms = SMSMessage.find.byId(df.get("SmsSid"));
        if(sms!=null){
            sms.setStatus(df.get("SmsStatus"));
        }
        Ebean.update(sms);
        return ok();
    }

}
