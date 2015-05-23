package models;
import org.junit.Test;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import static org.fest.assertions.Assertions.assertThat;
import com.avaje.ebean.Ebean;

/**
 * Created by michaeldorman on 5/20/15.
 */
public class VoiceMessageTest extends BaseModelTest{

    @Test
    public void create() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                VoiceMessage vm = new VoiceMessage();
                vm.id = "VM_A";
                vm.toNumber = "5556729610";
                vm.fromNumber = "5556489610";
                vm.accountId = "DEF";
                vm.save();
                assertThat(VoiceMessage.find.byId("VM_A")).isNotNull();
            }
        });
    }

    @Test
    public void update() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                VoiceMessage vm = new VoiceMessage();
                vm.id = "VM_A";
                vm.toNumber = "5556729610";
                vm.fromNumber = "5556489610";
                vm.accountId = "DEF";
                vm.save();
                assertThat(VoiceMessage.find.byId("VM_A").status).isNullOrEmpty();
                VoiceMessage updateVm =   VoiceMessage.find.byId("VM_A");
                updateVm.status = "completed";
                Ebean.update(updateVm);
                assertThat(VoiceMessage.find.byId("VM_A").status == "completed");
            }
        });
    }

}
