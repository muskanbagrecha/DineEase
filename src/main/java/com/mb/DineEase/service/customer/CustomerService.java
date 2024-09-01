package com.mb.DineEase.service.customer;

import org.springframework.stereotype.Service;

public interface CustomerService {
    void notifyCustomerOfRestaurantAvailability(String customerId, String restaurantId);
}
