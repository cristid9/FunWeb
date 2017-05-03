package funWebMailer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by Bogdanel on 03.05.2017.
 */
public class FunWebMailer {

    private static final String sender = "funwebcontact@gmail.com";

    private void sendTextMail(List<String> recipients, String subject, String text){
        //stub method
    }

    /**
     * @brief Sends the notification mail that every user should get after completing the
     *        registration form.
     * @param name The name int the real life of the user.
     * @param email The email address of this use.
     */
    // TODO: Externalize hardcoded mail string to specified templates
    // MYBE TODO: refactor this method to get an `user` as argument
    public static void sendTextRegisterNotification(String name, String email) {
        List<String> recipients = new ArrayList<String>();
        recipients.add(email);

        String mailContentUnformated = "Salut %s," +
                "Am inregistrat o tentativa de logare de pe aceasta adresa de mail\n" +
                " pentru a-ti activa contul da click pe acest link %s.\n" +
                "Multumesc mult\n," +
                "Echipa FunWeb";

        // really really bad practice, but it will be allowed for the moment
        String resetPasswordUrl = "localhost:8083/reset/" + UUID.randomUUID().toString();

        String mailContent = String.format(mailContentUnformated, name, resetPasswordUrl);

        String subject = "Confirm your registration attempt at FunWeb";

        sendTextMail(recipients, subject, mailContent);
    }

}
