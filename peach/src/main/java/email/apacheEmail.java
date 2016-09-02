package email;

import org.apache.commons.mail.HtmlEmail;

/**
 * Created by gang.qin on 2016/6/16.
 */
public class apacheEmail {

    private static final String HOST = "mail.qq.com";
    private static final String senderAddress = "steel@qq.com";
    private static final String senderName = "steel";
    private static final String receivers = "1179135080@qq.com";
    private static final String title = "test apache email";
    private static final String userName = "steel@qq.com";
    private static final String password = "Qin@0903gang";


    public static void send() {
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(HOST);
            email.setFrom(senderAddress, senderName);
            email.setAuthentication(userName, password);
            email.setSubject(title);
            email.setHtmlMsg("test");
            email.setCharset("UTF-8");

            String[] receiver = receivers.split(",");
            for (String r : receiver) {
                email.addTo(r);
            }

            email.send();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        send();
    }
}
