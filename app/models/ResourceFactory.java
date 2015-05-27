package models;

import java.util.*;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.*;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Account;
import play.Logger;


public final class ResourceFactory {

    private static final String CALL_STATUS_URL = play.Play.application().configuration().getString("twilio.callStatus");
    private static final String SMS_STATUS_URL = play.Play.application().configuration().getString("twilio.smsStatus");
    private static final String VOICE_XML_URL = play.Play.application().configuration().getString("twilio.voice");

    private static final String ACCOUNT_SID = play.Play.application().configuration().getString("twilio.accountSid");
    private static final String AUTH_TOKEN = play.Play.application().configuration().getString("twilio.authToken");

    private static TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
    private static Account mainAccount = client.getAccount();

    private static MessageFactory messageFactory = mainAccount.getMessageFactory();
    private static CallFactory callFactory = mainAccount.getCallFactory();
    private static ResourceFactory instance = new ResourceFactory();

    private static final Logger.ALogger logger = Logger.of(Application.class);

    public ResourceFactory(){

    }

    public static ResourceFactory getInstance(){
        if(instance == null) {
            instance = new ResourceFactory();
        }
        return instance;
    }

    /**
     * Set shared parameters for Message and Calls
     *
     * @param notification - Notification object containing data for API call
     * @return parameter list with shared parameters
     */
    private static ArrayList<NameValuePair> baseParams(Notification notification){
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", notification.getToNumber()));
        params.add(new BasicNameValuePair("From", notification.getFromNumber()));
        return params;
    }

    /**
     * Create a text message object based on the notification passed.
     *
     * @param notification - notification containing the data for the text message
     * @return Twilio Message object
     * @throws TwilioRestException
     */
    public static Message createMessage(Notification notification) throws TwilioRestException{
        if (notification!=null) {
                ArrayList<NameValuePair> params = baseParams(notification);
                params.add(new BasicNameValuePair("Body", notification.getContent()));
                params.add(new BasicNameValuePair("StatusCallback", SMS_STATUS_URL));
                 logger.info("Sending message request: " + Arrays.toString(params.toArray()));
                return messageFactory.create(params);
        }
        return null;
    }

    /**
     * Create Phone call based on the notification passed
     *
     * @param notification - Notification object containing details for the call
     * @return Call object from Twilio API
     * @throws TwilioRestException
     */
    public static Call createCall(Notification notification) throws TwilioRestException{
        if (notification!=null) {
            ArrayList<NameValuePair> params = baseParams(notification);
            params.add(new BasicNameValuePair("Url", VOICE_XML_URL ));
            params.add(new BasicNameValuePair("StatusCallback", CALL_STATUS_URL));
            params.add(new BasicNameValuePair("Method", "GET"));
            logger.info("Sending call request: " + Arrays.toString(params.toArray()));
            return callFactory.create(params);
        }
        return null;
    }


}


