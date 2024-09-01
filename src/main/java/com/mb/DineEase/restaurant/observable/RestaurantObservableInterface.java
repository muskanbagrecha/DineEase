package com.mb.DineEase.restaurant.observable;

import com.mb.DineEase.restaurant.observer.ObserverInterface;

public interface RestaurantObservableInterface {
    void addObserver(String restaurantId, ObserverInterface observer);
    void removeObserver(String restaurantId, ObserverInterface observer);
    void notifyObservers(String restaurantId);
}
