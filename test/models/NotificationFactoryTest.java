package models;

import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;



import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by michaeldorman on 5/23/15.
 */
public class NotificationFactoryTest {

    @Test
    public void testRetrieveQueryStrNoFieldValue(){
        Map<String, String[]> queryStr = new HashMap<String,String[]>();
        NotificationFactory factory = NotificationFactory.getInstance();
        Optional<List<String>> result =  factory.retrieveQueryStr(queryStr, null);
        assertThat(!result.isPresent());
    }

    @Test
    public void testRetrieveQueryStrFieldValueNoData(){
        Map<String, String[]> queryStr = new HashMap<String,String[]>();
        NotificationFactory factory = NotificationFactory.getInstance();
        Optional<List<String>> result =  factory.retrieveQueryStr(queryStr, "PhoneNumber");
        assertThat(!result.isPresent());
    }

    @Test
    public void testRetrieveQueryStrQueryStrNull(){
        NotificationFactory factory = NotificationFactory.getInstance();
        Optional<List<String>> result =  factory.retrieveQueryStr(null, "PhoneNumber");
        assertThat(!result.isPresent());
    }

    @Test
    public void testRetrieveQueryStrFieldValueDataPresent(){
        Map<String, String[]> queryStr = new HashMap<String,String[]>();
        String [] data = new String[1];
        data[0] = "5551234567";
        queryStr.put("PhoneNumber", data);
        NotificationFactory factory = NotificationFactory.getInstance();
        Optional<List<String>> result =  factory.retrieveQueryStr(queryStr, "PhoneNumber");
        assertThat(result.isPresent());
    }

}
