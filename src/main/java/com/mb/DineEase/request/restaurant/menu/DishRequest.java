package com.mb.DineEase.request.restaurant.menu;

import com.mb.DineEase.model.menu.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishRequest {
    private String restaurantId;
    private Dish dish;
}
