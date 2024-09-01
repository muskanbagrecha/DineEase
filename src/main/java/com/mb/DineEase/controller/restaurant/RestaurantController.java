package com.mb.DineEase.controller.restaurant;

import com.mb.DineEase.model.address.Location;
import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.request.restaurant.CreateRestaurantRequest;
import com.mb.DineEase.request.restaurant.RestaurantStatusDTO;
import com.mb.DineEase.response.restaurant.RestaurantCreationResponse;
import com.mb.DineEase.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/")
    public ResponseEntity<RestaurantCreationResponse> addRestaurant(@RequestBody CreateRestaurantRequest restaurantRequest) {
        try{
           Restaurant restaurant = restaurantService.createRestaurant(restaurantRequest);
           return ResponseEntity.ok(new RestaurantCreationResponse(restaurant.getId(), restaurant.getName(), restaurant.getManager().getId()));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String id) {
        try{
            return ResponseEntity.ok(restaurantService.getRestaurantById(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String id, @RequestBody CreateRestaurantRequest restaurantRequest) {
        try{
            return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurantRequest));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/changeStatus")
    public ResponseEntity<String> notifyObservers(@RequestBody RestaurantStatusDTO restaurantStatusDTO) {
        boolean restaurantStatus = restaurantStatusDTO.getIsOpened().equalsIgnoreCase("true");
        restaurantService.updateRestaurantStatus(restaurantStatusDTO.getRestaurantId(), restaurantStatus);
        return ResponseEntity.ok("Status " + restaurantStatusDTO.getIsOpened() + " updated successfully");
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<Restaurant>> getRestaurantByName(@PathVariable String name) {
        try{
            return ResponseEntity.ok(restaurantService.getRestaurantByName(name));
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
