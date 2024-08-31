package com.mb.DineEase.factory;

import com.mb.DineEase.model.user.Customer;
import com.mb.DineEase.model.user.RestaurantManager;
import com.mb.DineEase.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User createUser(String role, String email, String encodedPassword) {
        switch (role.toLowerCase()) {
            case "restaurant_manager":
                RestaurantManager restaurantManager = new RestaurantManager();
                restaurantManager.setEmail(email);
                restaurantManager.setPassword(encodedPassword);
                return restaurantManager;
            case "customer":
                Customer customer = new Customer();
                customer.setEmail(email);
                customer.setPassword(encodedPassword);
                return customer;
            default:
                throw new IllegalArgumentException("Invalid role specified");
        }
    }
}
