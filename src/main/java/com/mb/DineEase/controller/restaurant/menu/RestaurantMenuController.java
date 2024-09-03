package com.mb.DineEase.controller.restaurant.menu;

import com.mb.DineEase.model.menu.Dish;
import com.mb.DineEase.request.restaurant.menu.Menu;
import com.mb.DineEase.service.restaurant.menu.RestaurantMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant/menu")
public class RestaurantMenuController {

    @Autowired
    RestaurantMenuService  restaurantMenuService;

    @PostMapping("/")
    public void addMenu(@RequestBody Menu menu) {
        restaurantMenuService.addMenu(menu.getRestaurantId(), menu.getMenu());
    }

}
