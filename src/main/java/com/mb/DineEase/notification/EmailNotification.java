package com.mb.DineEase.notification;

import com.mb.DineEase.model.email.EmailRecipient;
import com.mb.DineEase.service.notification.EmailService;
import com.mb.DineEase.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

public class EmailNotification implements NotificationStrategy{

   private String address;

    public EmailNotification(String address) {
        super();
        this.address = address;
    }

    @Override
    public void send(NotificationService notificationService, String subject, String msg) {
        try{
            notificationService.sendNotification(address, subject, msg);
        }
        catch(MessagingException messagingException){
            //TODO: Log exception
            System.out.println("Sending email failed due to: " + messagingException);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
