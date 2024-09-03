package com.mb.DineEase.service.restaurant.menu;

import com.mb.DineEase.model.menu.Dish;

import java.util.List;

public interface RestaurantMenuService {
    void addMenu(String restaurantId, List<Dish> menu);
    void updateMenu(String restaurantId, List<Dish> menu);
    List<Dish> getMenu(String restaurantId);
    Dish getDish(String restaurantId, String dishId);
    void updateDish(String restaurantId, String dishId, Dish dish);
    void deleteDish(String restaurantId, String dishId);
    void addDish(String restaurantId, Dish dish);
}
