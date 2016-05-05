package Util;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by gang.qin on 2016/3/29.
 * <p>Reference:
 * <a href="http://www.w3.org/TR/html4/charset.html">http://www.w3.org/TR/html4/charset.html</a>
 * @see org.springframework.web.util.HtmlUtils
 * @see org.apache.commons.lang.StringEscapeUtils
 */
public class HtmlUtil {

    /**
     * Turn special characters into HTML character references.
     * Handles complete character set defined in HTML 4.01 recommendation.
     * @param input the (unescaped) input string
     * @return the escaped string
     */
    public static String htmlEscape(String input) {
        if (input == null) {
            return null;
        }
        StringBuffer escaped = new StringBuffer(input.length() * 2);
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            String reference = "";//characterEntityReferences.convertToReference(character);
            if (reference != null) {
                escaped.append(reference);
            } else {
                escaped.append(character);
            }
        }
        return escaped.toString();
    }

    public static class HtmlCharacterSetRef {
        private static Properties entityRef = new Properties();
        private static final String HTML_PROPERTIES_FILE = "html.properties";
        private static String[] Character2entityfMap = new String[3000];
        private final Map entity2CharacterMap = new HashMap(252);

        public  HtmlCharacterSetRef () {
            InputStream is = HtmlCharacterSetRef.class.getResourceAsStream(HTML_PROPERTIES_FILE);
            if (is == null) {
                throw new IllegalStateException ("Cannot find reference definition file [html.properties] as class path resource");
            }
            try {
                try {
                    entityRef.load(is);
                } finally {
                    is.close();
                }
            } catch (IOException e) {
                throw new IllegalStateException(
                        "Failed to parse reference definition file [HtmlCharacterEntityReferences.properties]: " +  e);
            }

            Enumeration keys = entityRef.propertyNames();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                int referredChar = Integer.parseInt(key);
                Assert.isTrue((referredChar < 1000 || (referredChar >= 8000 && referredChar < 10000)),
                        "Invalid reference to special HTML entity: " + referredChar);
                int index = (referredChar < 1000 ? referredChar : referredChar - 7000);
                String reference = entityRef.getProperty(key);
//                this.htmlCharacterSetRefMap[index] = REFERENCE_START + reference + REFERENCE_END;
//                this.entityReferenceToCharacterMap.put(reference, new Character((char) referredChar));
            }
        }



    }

}
