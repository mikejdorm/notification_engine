package models;
import javax.persistence.*;

/**
 *
 * A Voice Message sent to the requested phone number.
 *
 * Created by michaeldorman on 5/19/15.
 */
@Entity
@Inheritance
@DiscriminatorValue("Voice")
public class VoiceMessage extends AbstractNotification {

    public static Finder<String,VoiceMessage> find = new Finder<String,VoiceMessage>(
            String.class, VoiceMessage.class
    );

}
