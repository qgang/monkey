package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author steelqin
 * @date 18/6/8
 */
public class PropertiesTest {

    public static void main(String[] args) {
        try {
            Map<String, String> keyValues = new HashMap<>();
            keyValues.put("", "");

            Properties p = new Properties();
            p.putAll(keyValues);

            p.get("");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return;
    }
}
