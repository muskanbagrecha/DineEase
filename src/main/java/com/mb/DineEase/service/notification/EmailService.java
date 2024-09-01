package com.mb.DineEase.service.notification;

import com.mb.DineEase.model.email.EmailRecipient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Configurable
public class EmailService implements NotificationService {

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private String mailPort;

    @Value("${spring.mail.email}")
    private String from;

    @Value("${spring.mail.password}")
    private String password;

    private Session getSessionObject() throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", mailHost);
        properties.put("mail.smtp.port", mailPort);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return Session.getInstance(properties);
    }

    public MimeMessage composeEmail(EmailRecipient recipient, String subject, String message) throws MessagingException {
        try{
            Session session = getSessionObject();
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(from);
            if(subject!=null){
                mimeMessage.setSubject(subject);
            }
            mimeMessage.setRecipient(recipient.getRecipientType(), new InternetAddress(recipient.getAddress()));
            mimeMessage.setText(message);
            return mimeMessage;
        }
        catch (MessagingException ex){
            System.out.println("Failed to send email: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void sendNotification(String address, String subject, String message) throws MessagingException {
        EmailRecipient emailRecipient = new EmailRecipient(address);
        try {
            MimeMessage mimeMessage = composeEmail(emailRecipient, subject, message);
            if(mimeMessage!=null){
                Transport.send(mimeMessage, from, password);
                System.out.println("Email sent successfully to: " + emailRecipient.getAddress());
            }
        }
        catch (MessagingException ex){
            System.out.println("Failed to send email: " + ex.getMessage());
        }
    }

}

