package models;

import com.avaje.ebean.Ebean;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by michaeldorman on 5/19/15.
 */
public class SMSMessageTest extends BaseModelTest {


    @Test
    public void create() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
               SMSMessage sms = new SMSMessage();
                sms.id = "SMS_A";
                sms.toNumber = "5556729610";
                sms.fromNumber = "5556489610";
                sms.accountId = "DEF";
                sms.save();
                assertThat(SMSMessage.find.byId("SMS_A")).isNotNull();
            }
        });
    }

    @Test
    public void update() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                SMSMessage sms = new SMSMessage();
                sms.id = "SMS_A";
                sms.toNumber = "5556729610";
                sms.fromNumber = "5556489610";
                sms.accountId = "DEF";
                sms.save();
                assertThat(SMSMessage.find.byId("SMS_A").status).isNullOrEmpty();
                SMSMessage updateSms = SMSMessage.find.byId("SMS_A");
                updateSms.status = "completed";
                Ebean.update(updateSms);
                assertThat(SMSMessage.find.byId("SMS_A").status == "completed");
            }
        });
    }


}
