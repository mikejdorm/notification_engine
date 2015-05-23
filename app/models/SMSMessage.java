package models;
import javax.persistence.*;

import com.twilio.sdk.resource.instance.Media;

/**
 *
 * A Text (SMS) Notification sent to the requested phone number.
 *
 * Created by michaeldorman on 5/19/15.
 */
@Entity
@Inheritance
@DiscriminatorValue("SMS")
public class SMSMessage extends AbstractNotification {

    public Media media;
    public static final int MAX_TEXT_CHARACTERS = 1600;

    @Override
    public void setContent(String content) throws Exception{
        if(content!=null && content.length() <= MAX_TEXT_CHARACTERS){
            this.content = content;
        }
        else{
            throw new Exception("Length of body is greater than maximum length of 1600 characters");
        }
    }


    public static Finder<String,SMSMessage> find = new Finder<String,SMSMessage>(
            String.class, SMSMessage.class
    );

}
