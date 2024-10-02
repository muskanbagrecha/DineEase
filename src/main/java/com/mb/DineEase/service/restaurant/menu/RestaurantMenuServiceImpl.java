package com.mb.DineEase.service.restaurant.menu;

import com.mb.DineEase.exceptions.DishValidationException;
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
    public void editDish(String restaurantId, Dish dish) {
        String dishId = dish.getId();
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Dish> menu = restaurant.getMenu();
        for(Dish currDish : menu){
            if(currDish.getId().equals(dishId)){
                if(currDish.getName()!=null){
                    currDish.setName(dish.getName());
                }
                if(currDish.getDescription()!=null){
                    currDish.setDescription(dish.getDescription());
                }
                if(currDish.getImage()!=null){
                    currDish.setImage(dish.getImage());
                }
                if(currDish.getPrice()!=null){
                    currDish.setPrice(dish.getPrice());
                }
                if(currDish.getCategory()!=null){
                    currDish.setCategory(dish.getCategory());
                }
                if(currDish.getIsVeg()!=null){
                    currDish.setIsVeg(dish.getIsVeg());
                }
                if(currDish.getKcal()!=null){
                    currDish.setKcal(dish.getKcal());
                }
                if(currDish.getIsAvailable()!=null){
                    currDish.setIsAvailable(dish.getIsAvailable());
                }
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
        if(dish.getName()!=null){
            throw new DishValidationException("Dish name is required");
        }
        menu.add(dish);
        restaurant.setMenu(menu);
        restaurantRepository.save(restaurant);
    }
}
