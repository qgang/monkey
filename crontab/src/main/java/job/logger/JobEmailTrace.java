package job.logger;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gang.qin on 2015/12/17.
 */
public class JobEmailTrace {
    private StringBuffer content = new StringBuffer();

    private long beginStamp = System.currentTimeMillis();
    private long endStamp = System.currentTimeMillis();

    private String host = "mail.51ping.com";
    private String senderAddress = "rs-job@dianping.com";
    private String senderName = "Tile";
    private String receivers = "Receivers";
    private String title = "Title";
    private String userName = "nagios@51ping.com";
    private String password = "NaGiosMail";

    public void error(String txt) {
        content.append("<span style='color:rgb(255,0,0)'>" + txt + "</span>");
        newLine();
    }

    public void info(String txt) {
        content.append(txt);
        newLine();
    }

    public void begin(){
        beginStamp = System.currentTimeMillis();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        content.append("Job Begin ! Begin time is : " + format.format(new Date()));
        newLine();
        content.append("-------------------------");
        newLine();
    }

    public void end(){
        endStamp = System.currentTimeMillis();
        content.append("-------------------------");
        newLine();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        content.append("Job End ! End Time is : " + format.format(new Date()));
        newLine();
        content.append("Job cost (second)：" + (endStamp-beginStamp)/1000);
    }

    public void send() throws EmailException {
        sendEmail(content.toString());
    }

    private void sendEmail(String contant) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(host);
        email.setFrom(senderAddress, senderName);
        email.setAuthentication(userName, password);
        email.setSubject(title);
        email.setHtmlMsg(contant);
        email.setCharset("UTF-8");

        String[] receiver = receivers.split(",");
        for (String r : receiver) {
            email.addTo(r);
        }

        email.send();
    }

    private void newLine() {
        content.append("<br/>");
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
