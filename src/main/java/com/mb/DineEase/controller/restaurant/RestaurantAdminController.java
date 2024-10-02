package com.mb.DineEase.controller.restaurant;

import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.request.restaurant.CreateRestaurantRequest;
import com.mb.DineEase.request.restaurant.RestaurantStatusDTO;
import com.mb.DineEase.response.restaurant.RestaurantCreationResponse;
import com.mb.DineEase.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant/admin")
public class RestaurantAdminController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantCreationResponse> addRestaurant(@RequestBody CreateRestaurantRequest restaurantRequest) {
        try {
            Restaurant restaurant = restaurantService.createRestaurant(restaurantRequest);
            return ResponseEntity.ok(new RestaurantCreationResponse(restaurant.getId(), restaurant.getName()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String id, @RequestBody CreateRestaurantRequest restaurantRequest) {
        try{//TODO: only restaurant manager can update restaurant
            return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurantRequest));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/changeStatus")
    public ResponseEntity<String> changeRestaurantStatus(@RequestBody RestaurantStatusDTO restaurantStatusDTO) {
        //TODO: only restaurant manager can change restaurant status
        boolean restaurantStatus = restaurantStatusDTO.getIsOpened().equalsIgnoreCase("true");
        restaurantService.updateRestaurantStatus(restaurantStatusDTO.getRestaurantId(), restaurantStatus);
        return ResponseEntity.ok("Status " + restaurantStatusDTO.getIsOpened() + " updated successfully");
    }
}
