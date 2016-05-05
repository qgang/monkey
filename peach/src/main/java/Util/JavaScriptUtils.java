package Util;

/**
 * Created by gang.qin on 2016/3/29.
 *
 * Escapes based on the JavaScript 1.5 recommendation.
 *
 * <p>Reference:
 * <a href="http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Guide:Literals#String_Literals">
 * Core JavaScript 1.5 Guide
 * </a>
 */
public class JavaScriptUtils {

    /**
     * Turn special characters into escaped characters conforming to JavaScript.
     * Handles complete character set defined in HTML 4.01 recommendation.
     * @param input the input string
     * @return the escaped string
     * @see org.springframework.web.util.JavaScriptUtils
     */
    public static String javaScriptEscape(String input) {
        if (input == null) {
            return input;
        }

        StringBuffer filtered = new StringBuffer(input.length());
        char prevChar = '\u0000';
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '"') {
                filtered.append("\\\"");
            }
            else if (c == '\'') {
                filtered.append("\\'");
            }
            else if (c == '\\') {
                filtered.append("\\\\");
            }
            else if (c == '/') {
                filtered.append("\\/");
            }
            else if (c == '\t') {
                filtered.append("\\t");
            }
            else if (c == '\n') {
                if (prevChar != '\r') {
                    filtered.append("\\n");
                }
            }
            else if (c == '\r') {
                filtered.append("\\n");
            }
            else if (c == '\f') {
                filtered.append("\\f");
            }
            else {
                filtered.append(c);
            }
            prevChar = c;

        }
        return filtered.toString();
    }

}
