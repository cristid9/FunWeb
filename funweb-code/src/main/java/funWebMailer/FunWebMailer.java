package funWebMailer;

import com.sun.org.apache.xpath.internal.operations.Mult;
import com.sun.xml.internal.ws.protocol.soap.MessageCreationException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by Bogdanel on 03.05.2017.
 */
public class FunWebMailer {

    private static final String from = "funwebcontact@gmail.com";
    private static final String password = "funweb2017";

    private static void sendTextMail(List<String> recipients, String subject, String text){
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[recipients.size()];

            for(int i = 0; i < recipients.size(); i++){
                toAddress[i] = new InternetAddress(recipients.get(i));
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(text);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }

    }

    /**
     * @brief Sends the notification mail that every user should get after completing the
     *        registration form.
     * @param name The name int the real life of the user.
     * @param email The email address of this use.
     */
    // TODO: Externalize hardcoded mail string to specified templates
    // MAYBE TODO: refactor this method to get an `user` as argument
    public static void sendTextRegisterNotification(String name, String email) {
        List<String> recipients = new ArrayList<String>();
        recipients.add(email);

        String mailContentUnformated = "Salut %s, \n" +
                "Bine ai venit la FunWeb.";

        // really really bad practice, but it will be allowed for the moment

        String mailContent = String.format(mailContentUnformated, name);

        String subject = "Confirm your registration attempt at FunWeb";

        sendTextMail(recipients, subject, mailContent);
    }

    public static void setResetPasswordLink(String name, String email, String url) {

        List<String> recipients = new ArrayList<String>();
        recipients.add(email);

        String mailContentUnformated = "Salut %s, \n" +
                "Am vazut ca ti-ai uitat parola. Intra aici ca sa o resetezi: %s";

        // really really bad practice, but it will be allowed for the moment

        String mailContent = String.format(mailContentUnformated, name, url);

        String subject = "Confirm your registration attempt at FunWeb";

        sendTextMail(recipients, subject, mailContent);
    }

}
