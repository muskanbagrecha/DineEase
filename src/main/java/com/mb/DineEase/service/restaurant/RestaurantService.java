package com.mb.DineEase.service.restaurant;

import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.request.restaurant.CreateRestaurantRequest;
import com.mb.DineEase.restaurant.observer.ObserverInterface;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(CreateRestaurantRequest request);
    Restaurant getRestaurantById(String id);
    Restaurant updateRestaurant(String id, CreateRestaurantRequest newRequest);
    List<Restaurant> getRestaurantByName(String name);
    List<Restaurant> findRestaurantsWithinRadius(Double latitude, Double longitude, Double radius);
    void updateRestaurantStatus(String restaurantId, boolean isOpened);
    void addRestaurantObserver(String restaurantId, ObserverInterface observerInterface);
}
