package com.mb.DineEase.model.cart;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class Cart {
//    private String id;
    @DBRef
    private String userId;
    @DBRef
    private String restaurantId;
    @DBRef
    private List<CartItem> items = new ArrayList<>();
    private double total;
}
