package tw.com.ispan.cma.utils;



import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class JavaMail {
    private final String username;
    private final String password;
    private final Properties props;
    private final String memberEmail;
    private final String title;
    private final String memberName;

    public JavaMail(String memberEmail, String memberName) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.title = "Java webapp 網路購物訂單確認信";
        String host = "smtp.gmail.com";
        int port = 587;
        username = "sbbty218@gmail.com";
        password = "xrqonlxqjfhweukl";

        props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
    }

    public String sendMail(String text) {
        String to = memberEmail;
        try {
            Message message = createMessage(to, title , text);
            Transport.send(message);
            return "郵件傳送成功";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPWMail(String text) {
        String to = memberEmail;
        try {
            String content0 = "親愛的"+memberName+"您好，";
            String content1 = "已幫您重設密碼為:";
            Message message = createMessage(to, "您的CMA購物網密碼已經被重置" ,content0+content1+text);
            Transport.send(message);
            return "郵件傳送成功";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message createMessage(String to, String subject, String text) throws MessagingException {
        Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );
        Message message = new MimeMessage(session);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setContent(multipart(text));
        return message;
    }

    private Multipart multipart(String text) throws MessagingException {
        Multipart mp = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(text, "text/html;charset=UTF-8");
        mp.addBodyPart(htmlPart);
        return mp;
    }
}
