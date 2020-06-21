package utils;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Doktor;

public class Mail {

    private static final String USERNAME = "drzavnabolnica2020@gmail.com";
    private static final String PASSWORD = "drzavnabolnica123";

    public static boolean sendMail(Doktor doktor, String sigurnosniKod) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(doktor.getEmail())
            );
            message.setSubject("Bolnica - Sigurnosni kod");
            String htmlCode = "<h2 style='color: #2196f3; font-size: 24px;'>Zdravo " + doktor.getIme() + " " + doktor.getPrezime() + "!</h2><br>"
                    + "<p style='font-size: 16px;'>Sigurnosni kod je <b>" + sigurnosniKod + "</b>.</p><br><img src='"
                    + "https://image.flaticon.com/icons/svg/504/504093.svg' /><br>";
            message.setContent(htmlCode, "text/html");

            Transport.send(message);

            System.out.println("Uspesno poslat email!");

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
