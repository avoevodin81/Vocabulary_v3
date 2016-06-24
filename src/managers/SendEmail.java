package managers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Test on 13.06.2016.
 */
public class SendEmail {
    private String to;
    private final String forgotMessage = "Your password is: ";
    private final String code = String.valueOf(new Random().nextInt(10000));
    private final String checkMessage = "Input the registration password in to field. The registration password: " + code;

    private final String from = "avoevodin81@gmail.com";
    private final String username = "avoevodin81@gmail.com";
    private final String password = "c4cb1db0c7";

    private final String host = "smtp.gmail.com";


    public SendEmail(String to) {
        this.to = to;
    }

    public void checkSend(){
        send(checkMessage);
    }

    public void forgotSend(){

    }

    private void send(String text){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Email from Dictionary");
            message.setText(text);
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Message is sended. Check your email!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCode() {
        return code;
    }

    public String getCheckMessage() {
        return checkMessage;
    }
}
