package com.mb.DineEase.restaurant.observer;

import com.mb.DineEase.model.user.Customer;
import com.mb.DineEase.notification.NotificationStrategy;
import com.mb.DineEase.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerObserverImpl implements ObserverInterface {

    private final Customer customer;
    private final NotificationStrategy notificationStrategy;
    private final NotificationService notificationService;

    @Autowired
    public CustomerObserverImpl(Customer customer, NotificationStrategy notificationStrategy, NotificationService notificationService) {
        this.customer = customer;
        this.notificationStrategy = notificationStrategy;
        this.notificationService = notificationService;
    }

    @Override
    public void update(String data) {
        notificationStrategy.send(notificationService, customer.getEmail(), "Restaurant " + data + " is now available.");
    }
}