package com.mb.DineEase.service.customer;

import com.mb.DineEase.model.email.EmailRecipient;
import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.model.user.Customer;
import com.mb.DineEase.model.user.User;
import com.mb.DineEase.notification.EmailNotification;
import com.mb.DineEase.notification.NotificationStrategy;
import com.mb.DineEase.respository.UserRepository;
import com.mb.DineEase.restaurant.observer.CustomerObserverImpl;
import com.mb.DineEase.service.notification.EmailService;
import com.mb.DineEase.service.notification.NotificationService;
import com.mb.DineEase.service.restaurant.RestaurantService;
import com.mb.DineEase.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Override
    public void notifyCustomerOfRestaurantAvailability(String customerId, String restaurantId) {
        User user = userService.getUserById(customerId);
        if(!user.getRole().equals("CUSTOMER")){
            throw new RuntimeException("Only customer can subscribe to a restaurant.");
        }
        Customer customer = (Customer) user;
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId); //TODO: remove this
        if(restaurant.isOpened()==true){
            throw new RuntimeException("Restaurant is already opened.");
        }
        customer.addRestaurantSubscription(restaurant);
        userRepository.save(customer);
        NotificationStrategy notificationStrategy = determineNotificationStrategy(user);
        NotificationService notificationService = determineNotificationService(notificationStrategy);
        CustomerObserverImpl customerObserverInterface = new CustomerObserverImpl(customer, notificationStrategy, notificationService);
        restaurantService.addRestaurantObserver(restaurantId, customerObserverInterface);
    }

    private NotificationStrategy determineNotificationStrategy(User user) {
        return new EmailNotification(user.getEmail());
    }

    private NotificationService determineNotificationService(NotificationStrategy notificationStrategy) {
        if(notificationStrategy instanceof EmailNotification){
            return emailService;
        }
        return null;
    }
}
