package com.mb.DineEase.response.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantCreationResponse {
    private String restaurantId;
    private String restaurantName;
    private String restaurantManagerId;
}
