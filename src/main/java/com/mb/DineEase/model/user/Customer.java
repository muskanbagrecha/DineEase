package com.mb.DineEase.model.user;

import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.model.order.Order;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class Customer extends User{
    public Customer(){
        super();
        this.setRole("CUSTOMER");
    }

    //reviews
    @DBRef
    private List<Order> orders = new ArrayList<>();

    @DBRef
    private Set<Restaurant> favourites = new HashSet<>();

    @DBRef
    private Set<Restaurant> restaurantsSubscribed = new HashSet<>();

    public void addRestaurantSubscription(Restaurant restaurant){
        this.restaurantsSubscribed.add(restaurant);
    }
}
