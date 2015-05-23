package models;
import play.test.Helpers;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import play.test.FakeApplication;
/**
 * Created by michaeldorman on 5/23/15.
 */
public class BaseModelTest {

        public static FakeApplication app;

        @BeforeClass
        public static void startApp() {
            app = Helpers.fakeApplication(Helpers.inMemoryDatabase("test"));
            Helpers.start(app);
        }

        @AfterClass
        public static void stopApp() {
            Helpers.stop(app);
        }

}
