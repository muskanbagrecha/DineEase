package com.mb.DineEase.notification;

import com.mb.DineEase.service.notification.EmailService;
import com.mb.DineEase.service.notification.NotificationService;

public interface NotificationStrategy {
    void send(NotificationService notificationService, String subject, String message);
}
