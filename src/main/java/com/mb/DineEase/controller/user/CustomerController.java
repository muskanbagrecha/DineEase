package com.mb.DineEase.controller.user;

import com.mb.DineEase.request.customer.RestaurantSubscriptionDTO;
import com.mb.DineEase.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PatchMapping("/subscribe")
    public ResponseEntity<String> subscribeToRestaurant(@RequestBody RestaurantSubscriptionDTO restaurantSubscriptionDTO){
        try
        {
            customerService.notifyCustomerOfRestaurantAvailability(restaurantSubscriptionDTO.getCustomerId(), restaurantSubscriptionDTO.getRestaurantId());
            return ResponseEntity.ok("Subscribed to restaurant successfully");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
