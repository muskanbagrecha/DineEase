package com.mb.DineEase.service.restaurant.menu;

import com.mb.DineEase.model.menu.Dish;
import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.respository.restaurant.RestaurantRepository;
import com.mb.DineEase.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantMenuServiceImpl implements RestaurantMenuService{

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantService restaurantService;

    @Override
    public void addMenu(String restaurantId, List<Dish> menu) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        restaurant.setMenu(menu);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateMenu(String restaurantId, List<Dish> menu) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        restaurant.setMenu(menu);
        restaurantRepository.save(restaurant);
    }

    @Override
    public List<Dish> getMenu(String restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return restaurant.getMenu();
    }

    @Override
    public Dish getDish(String restaurantId, String dishId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Dish> menu = restaurant.getMenu();
        for(Dish dish: menu) {
            if(dish.getId().equals(dishId)) {
                return dish;
            }
        }
        return null;
    }

    @Override
    public void updateDish(String restaurantId, String dishId, Dish dish) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Dish> menu = restaurant.getMenu();
        for(Dish d: menu) {
            if(d.getId().equals(dishId)) {
               d = dish;
               break;
            }
        }
        restaurant.setMenu(menu);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteDish(String restaurantId, String dishId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Dish> menu = restaurant.getMenu();
        menu.removeIf(dish -> dish.getId().equals(dishId));
        restaurant.setMenu(menu);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void addDish(String restaurantId, Dish dish) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Dish> menu = restaurant.getMenu();
        menu.add(dish);
        restaurant.setMenu(menu);
        restaurantRepository.save(restaurant);
    }
}
