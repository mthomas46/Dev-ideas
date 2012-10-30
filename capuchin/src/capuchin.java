
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
 
 
public class capuchin {
 
    private static final String SMTP_HOST_NAME = "smtp.mandrillapp.com";
    private static final String SMTP_AUTH_USER = "mthomas46@gatech.edu";
    private static final String SMTP_AUTH_PWD  = "f232b687-da89-434d-a60b-54229ba564d5";
 
    public static void main(String[] args) throws Exception{
       new capuchin().test();
    }
 
    public void test() throws Exception{
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
 
        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();
 
        MimeMessage message = new MimeMessage(mailSession);
 /*
        Multipart multipart = new MimeMultipart("alternative");
 
        BodyPart part1 = new MimeBodyPart();
        part1.setText("This is multipart mail and u read part1......");
 
        BodyPart part2 = new MimeBodyPart();
        part2.setContent("<b>This is multipart mail and u read part2......</b>", "text/html");
        
        BodyPart part3 = new MimeBodyPart();
        part3.setText("blablabla");
 
        multipart.addBodyPart(part1);
        multipart.addBodyPart(part2);
        multipart.addBodyPart(part3);
*/
        message.setText("LOLOLOLOLOLOLOL");
       // message.setContent(multipart);
        
        //me
        message.setFrom(new InternetAddress("mthomas46@gatech.edu"));
        message.setSubject("This is the subject");
        //you
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress("mthomas46@gatech.edu"));
 
        transport.connect();
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
 
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_USER;
           String password = SMTP_AUTH_PWD;
           return new PasswordAuthentication(username, password);
        }
    }
}
