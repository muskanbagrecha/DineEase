package com.mb.DineEase.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.mb.DineEase.model.menu.Dish;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Dish dish;
    private int quantity;
    private double price;
}
