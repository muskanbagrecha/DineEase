package com.mb.DineEase.respository.restaurant;

import com.mb.DineEase.model.restaurant.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findRestaurantsByName(String name);
    @Query("{ location: { $geoWithin: { $centerSphere: [ [ ?0, ?1 ], ?2 / 6378.1 ] } } }")
    List<Restaurant> findRestaurantsWithinRadius(double longitude, double latitude, double radius);
}
