package com.mb.DineEase.request.restaurant.menu;

import com.mb.DineEase.model.menu.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Menu {
    private String restaurantId;
    private List<Dish> menu;
}
