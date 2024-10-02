package com.mb.DineEase.controller.restaurant;

import com.mb.DineEase.model.address.Location;
import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.request.restaurant.RestaurantIdRequest;
import com.mb.DineEase.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        try {
            return ResponseEntity.ok(restaurantService.getRestaurants());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/id")
    public ResponseEntity<Restaurant> getRestaurantById(@RequestBody RestaurantIdRequest id) {
        try{
            return ResponseEntity.ok(restaurantService.getRestaurantById(id.getId()));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Restaurant>> getRestaurantByKeyword(@PathVariable String keyword) {
        try{
            return ResponseEntity.ok(restaurantService.findRestaurantByKeyword(keyword));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/radius")
    public List<Restaurant> getResturantsWithinRadius(@RequestBody Location location, @RequestParam Double radius) {
        return restaurantService.findRestaurantsWithinRadius(location.getLatitude(), location.getLongitude(), radius);
    }
}
