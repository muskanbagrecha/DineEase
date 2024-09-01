package com.mb.DineEase.request.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantSubscriptionDTO {
    private String customerId;
    private String restaurantId;
}
