package models;

import play.db.ebean.Model;

/**
 *
 * The Notification interface defines methods
 * required for an Object created from a request to the Notification API.
 *
 * A VoiceMessage and SMSMessage are both Notifications
 *
 *
 * Created by michaeldorman on 5/22/15.
 */
public interface Notification {

    public void setId(String id);

    public void setFromNumber(String fromNumber);

    public void setToNumber(String toNumber);

    public void setAccountId(String accountId);

    //public void setContact(Contact contact);

    public void setContent(String content) throws Exception;

    public void setStatus(String status);

    public String getToNumber();

    public String getFromNumber();

    public String getContent();

}
