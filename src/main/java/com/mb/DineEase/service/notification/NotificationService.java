package com.mb.DineEase.service.notification;

public interface NotificationService {
    void sendNotification(String address, String subject, String message) throws Exception;
}
