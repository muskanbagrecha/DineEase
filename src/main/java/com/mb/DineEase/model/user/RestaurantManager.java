package com.mb.DineEase.model.user;

import com.mb.DineEase.constants.ApplicationConstants;
import com.mb.DineEase.model.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
public class RestaurantManager extends User{

    public RestaurantManager(){
        super();
        this.setRole(ApplicationConstants.RESTAURANT_MANAGER);
    }

    @DBRef
    private Restaurant restaurant;
}
