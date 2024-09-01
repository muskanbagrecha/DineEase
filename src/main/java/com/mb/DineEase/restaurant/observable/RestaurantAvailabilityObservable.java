package com.mb.DineEase.restaurant.observable;

import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.restaurant.observer.ObserverInterface;
import com.mb.DineEase.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantAvailabilityObservable implements RestaurantObservableInterface {
    @Autowired
    RestaurantService restaurantService;

    Map<String, List<ObserverInterface>> restaurantAvailabilityObservers = new HashMap<>();

    @Override
    public void addObserver(String restaurantId, ObserverInterface observer) {
        addRestaurantObservable(restaurantId);
        restaurantAvailabilityObservers.get(restaurantId).add(observer);
    }

    @Override
    public void removeObserver(String restaurantId, ObserverInterface observer) {
        restaurantAvailabilityObservers.get(restaurantId).remove(observer);
    }

    @Override
    public void notifyObservers(String restaurantId) {
        if(restaurantAvailabilityObservers.get(restaurantId) == null) {
            return;
        }
        for(ObserverInterface observer: restaurantAvailabilityObservers.get(restaurantId)) {
            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
            observer.update(restaurant.getName());
        }
    }

    public void setRestaurantAvailability(String restaurantId, boolean isOpened) {
        if(isOpened){
            notifyObservers(restaurantId);
            restaurantAvailabilityObservers.remove(restaurantId);
        }
    }

    public void addRestaurantObservable(String restaurantId) {
        restaurantAvailabilityObservers.putIfAbsent(restaurantId, new ArrayList<>());
    }

    public void clearObservables() {
        restaurantAvailabilityObservers.clear();
    }
}
