package com.mb.DineEase.model.user;

import com.mb.DineEase.model.Restaurant;
import com.mb.DineEase.model.order.Order;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class Customer extends User{
    private String role;
    public Customer(){
        super();
        role = "CUSTOMER";
    }

    //reviews
    @DBRef
    private List<Order> orders = new ArrayList<>();

    @DBRef
    private List<Restaurant> favourites = new ArrayList<>();

    @DBRef
    private List<Restaurant> restaurantsSubscribed = new ArrayList<>();

}
