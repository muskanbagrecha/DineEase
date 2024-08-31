package com.mb.DineEase.service.restaurant;

import com.mb.DineEase.model.Restaurant;
import com.mb.DineEase.model.user.RestaurantManager;
import com.mb.DineEase.request.restaurant.CreateRestaurantRequest;
import com.mb.DineEase.respository.restaurant.RestaurantRepository;
import com.mb.DineEase.service.user.UserService;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserService userService;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, String restaurantManagerId) throws NoSuchElementException {
        try{
            RestaurantManager restaurantManager = (RestaurantManager)userService.getRestaurantManagerById(restaurantManagerId);
            Restaurant restaurant = new Restaurant();
            restaurant.setName(request.getName());
            restaurant.setAddress(request.getAddress());
            restaurant.setManager(restaurantManager);
            restaurant.setContactInformation(request.getContactInformation());
            if(request.getDescription()!=null){
                restaurant.setDescription(request.getDescription());
            }
            if(!request.getCuisine().isEmpty()){
                restaurant.setCuisine(request.getCuisine());
            }
            if(!request.getOpeningHours().isEmpty()){
                restaurant.setOpeningHours(request.getOpeningHours());
            }
            if(!request.getImages().isEmpty()){
                restaurant.setImages(request.getImages());
            }
            GeoJsonPoint location = new GeoJsonPoint(request.getLocation().getLongitude(), request.getLocation().getLatitude());
            restaurant.setLocation(location);
            return restaurantRepository.save(restaurant);
        } catch (Exception e){
            throw new MongoException("Error while creating restaurant");
        }
    }

    @Override
    public Restaurant getRestaurantById(String id) {
        try{
            return restaurantRepository.findById(id).orElseThrow();
        } catch (Exception e){
            throw new NoSuchElementException("Restaurant not found");
        }
    }

    @Override
    public Restaurant updateRestaurant(String id, CreateRestaurantRequest newRequest) {
        try{
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            if(newRequest.getName()!=null){
                restaurant.setName(newRequest.getName());
            }
            if(newRequest.getDescription()!=null){
                restaurant.setDescription(newRequest.getDescription());
            }
            if(!newRequest.getCuisine().isEmpty()){
                restaurant.setCuisine(newRequest.getCuisine());
            }
            if(newRequest.getAddress()!=null){
                restaurant.setAddress(newRequest.getAddress());
            }
            if(newRequest.getLocation()!=null){
                GeoJsonPoint location = new GeoJsonPoint(newRequest.getLocation().getLongitude(), newRequest.getLocation().getLatitude());
                restaurant.setLocation(location);
            }
            if(!newRequest.getOpeningHours().isEmpty()){
                restaurant.setOpeningHours(newRequest.getOpeningHours());
            }
            if(!newRequest.getImages().isEmpty()){
                restaurant.setImages(newRequest.getImages());
            }
            return restaurantRepository.save(restaurant);
        } catch (Exception e){
            throw new MongoException("Error while updating restaurant");
        }
    }


    @Override
    public List<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findRestaurantsByName(name);
    }

    @Override
    public List<Restaurant> findRestaurantsWithinRadius(Double latitude, Double longitude, Double radius) {
        return restaurantRepository.findRestaurantsWithinRadius(longitude, latitude, radius);
    }

    @Override
    public void updateRestaurantStatus(String restaurantId, boolean isOpened) {
        try{
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
            restaurant.setOpened(isOpened);
            restaurantRepository.save(restaurant);
        } catch (Exception e){
            throw new MongoException("Error while updating restaurant status");
        }
    }
}
