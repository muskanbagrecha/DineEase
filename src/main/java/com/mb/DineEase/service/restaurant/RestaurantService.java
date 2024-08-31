package com.mb.DineEase.service.restaurant;

import com.mb.DineEase.model.Restaurant;
import com.mb.DineEase.model.user.RestaurantManager;
 import com.mb.DineEase.request.restaurant.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(CreateRestaurantRequest request, String restaurantManagerId);
    Restaurant getRestaurantById(String id);
    Restaurant updateRestaurant(String id, CreateRestaurantRequest newRequest);
    List<Restaurant> getRestaurantByName(String name);
    List<Restaurant> findRestaurantsWithinRadius(Double latitude, Double longitude, Double radius);
    void updateRestaurantStatus(String restaurantId, boolean isOpened);
}
