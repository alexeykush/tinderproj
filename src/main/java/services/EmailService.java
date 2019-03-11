package services;

import dto.User;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import storage.Storage;

public class EmailService {
    private final static String USER_NAME = "tinderauth@gmail.com";
    private final static String PASSWORD = "@tinderAuth2019";
    private final static String HOST = "http://localhost:8080";
    private Storage storage;
    public EmailService(Storage storage) {
        this.storage = storage;
    }

    public void sendEmail(User user){
        Mailer mailer = MailerBuilder.withSMTPServer("smtp.gmail.com", 587, USER_NAME, PASSWORD).withTransportStrategy(TransportStrategy.SMTP_TLS).buildMailer();
        String uid = String.valueOf(user.hashCode());
        String link = HOST + "/verification?user="+uid;
        String msg = String.format("Confirm your authorization by link: %s",link);

        Email email = EmailBuilder.startingBlank()
                .from("Tinder",USER_NAME)
                .to(user.getLogin())
                .withSubject("Confirm authorization")
                .withPlainText(msg)
                .buildEmail();
        mailer.sendMail(email);
        storage.put(uid,user);
    }

}
