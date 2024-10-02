package com.mb.DineEase.controller.restaurant.menu;

import com.mb.DineEase.exceptions.DishValidationException;
import com.mb.DineEase.request.restaurant.menu.DishRequest;
import com.mb.DineEase.request.restaurant.menu.Menu;
import com.mb.DineEase.service.restaurant.menu.RestaurantMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant/menu")
public class RestaurantMenuController {

    @Autowired
    RestaurantMenuService  restaurantMenuService;

    @PostMapping()
    public void addMenu(@RequestBody Menu menu) {
        restaurantMenuService.addMenu(menu.getRestaurantId(), menu.getMenu());
    }

    @PostMapping("/dish")
    public ResponseEntity<HttpStatus> addDish(@RequestBody DishRequest dishRequest){
        try{
            restaurantMenuService.addDish(dishRequest.getRestaurantId(), dishRequest.getDish());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (DishValidationException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/dish")
    public void editDish(@RequestBody DishRequest dishRequest){
        restaurantMenuService.editDish(dishRequest.getRestaurantId(), dishRequest.getDish());
    }

    @DeleteMapping("/dish")
    public void deleteDish(@RequestBody DishRequest dishRequest){
        restaurantMenuService.deleteDish(dishRequest.getRestaurantId(), dishRequest.getDish().getId());
    }
}
