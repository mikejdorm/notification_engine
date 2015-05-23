package models;

import java.util.*;

import com.google.common.base.Optional;

import com.twilio.sdk.resource.instance.*;
import com.twilio.sdk.TwilioRestException;

import play.Logger;

public final class NotificationFactory {

    public static final String MSG_TYPE_QRY_STR = play.Play.application().configuration().getString("twilio.messageTypeStr");
    public static final String MSG_BODY_QRY_STR = play.Play.application().configuration().getString("twilio.messageBodyStr");
    public static final String PHONE_QRY_STR = play.Play.application().configuration().getString("twilio.phoneNumberStr");

    public static final String SMS_MSG_TYPE = "SMS";
    public static final String CALL_MSG_TYPE = "PHONE_CALL";

    public static final ResourceFactory resourceFactory = ResourceFactory.getInstance();
    private static NotificationFactory instance;
    private static final Logger.ALogger logger = Logger.of(Application.class);

    private NotificationFactory() {

    }

    public static NotificationFactory getInstance() {
        if(instance == null) {
            instance = new NotificationFactory();
        }
        return instance;
    }

    /**
     *
     * Creates a Notification object and sends the notification API call information
     * to Twilio for processing.
     *
     * @param fromNumber fromNumber used in the request
     * @param queryStrs Query string from the API call
     * @return a Notification object or null if an issue occurs
     * @throws TwilioRestException
     */
    public Notification create(String fromNumber, Map<String, String[]> queryStrs)
            throws  Exception {

        Optional<List<String>> messageType = retrieveQueryStr(queryStrs, MSG_TYPE_QRY_STR);
        Optional<List<String>> messageBody = retrieveQueryStr(queryStrs, MSG_BODY_QRY_STR);
        Optional<List<String>> phoneNumbers = retrieveQueryStr(queryStrs, PHONE_QRY_STR);

        if (messageType.isPresent() && phoneNumbers.isPresent() && phoneNumbers.get().size() > 0) {
            String msgType = messageType.get().get(0);
            String toNumber = phoneNumbers.get().get(0);
            Notification notification;
            if (msgType.equalsIgnoreCase(SMS_MSG_TYPE) && !messageBody.isPresent()) {
                throw new Exception("SMS Messages Require a Message Body. Field Name: Body");
            }
            else if(msgType.equalsIgnoreCase(SMS_MSG_TYPE) && messageBody.isPresent()){
                 notification = new SMSMessage();
                 notification.setContent(messageBody.get().get(0));
                 setBaseValues(notification, fromNumber, toNumber);
                 Message message = resourceFactory.createMessage(notification);
                 setIdValues(notification, message.getSid(), message.getAccountSid());
                 return notification;
            }
            else if (msgType.equalsIgnoreCase(CALL_MSG_TYPE)) {
                notification = new VoiceMessage();
                setBaseValues(notification,fromNumber, toNumber);
                Call call = resourceFactory.createCall(notification);
                setIdValues(notification, call.getSid(), call.getAccountSid());
                return notification;
            }
            else{
                throw new Exception("Unknown Message Type Passed");
            }
        }
        throw new Exception("Information presented in query string incorrect");
    }

    /**
     * Sets the ID fields from the API call to Twilio
     *
     * @param notification - Notification object from notification API request
     * @param id - unique ID from API call
     * @param accountId  - account ID for Twilio
     * @return - Notification with ID values set.
     */
    private static Notification setIdValues(Notification notification, String id, String accountId) {
        if(notification != null && id!=null && accountId !=null) {
            notification.setId(id);
            notification.setAccountId(accountId);
        }
        return notification;
    }

    /**
     *
     * Sets fields shared among all Notification objects.
     *
     * @param notification Notification object created for the API request
     * @param fromNumber - Twilio number the notification will come from
     * @param toNumber - Number the notification is being sent to
     * @return Notification with the base fields set
     */
    private static Notification setBaseValues(Notification notification, String fromNumber, String toNumber) {
        if(notification!=null && fromNumber!=null && toNumber != null) {
            notification.setFromNumber(fromNumber);
            notification.setToNumber(toNumber);
        }
        return notification;
    }

    /**
     *
     * Takes the query string values and a fieldName. If the field exists then
     * the method returns an Optional List<String> otherwise it return an absent Optional
     *
     * @param queryStr query string passed in URL
     * @param fieldName name of the query string parameter
     * @return Optional<List<String>> values for the given parameter
     */

      protected static final Optional<List<String>> retrieveQueryStr(Map<String, String[]> queryStr, String fieldName) {
        if (queryStr != null && queryStr.containsKey(fieldName)) {
            return Optional.of(Arrays.asList(queryStr.get(fieldName)));
        }
        return Optional.absent();
    }

}
