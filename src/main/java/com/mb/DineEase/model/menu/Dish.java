package com.mb.DineEase.model.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mb.DineEase.model.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dish {
    @Getter
    private String id;
    @TextIndexed
    @NotNull
    private String name;
    private boolean isVeg;
    private int kcal;
    @DBRef
    @JsonIgnore
    private FoodCategory category;
    private String description;
    private List<String> images;
    private double price;
    private boolean isAvailable;
    @DBRef
    private Restaurant restaurant;

    public Dish(String name, boolean isVeg, int kcal, String description, List<String> images, double price, boolean isAvailable, FoodCategory category) {
        this.name = name;
        this.isVeg = isVeg;
        this.kcal = kcal;
        this.description = description;
        this.images = images;
        this.price = price;
        this.isAvailable = isAvailable;
        this.category = category;
    }
}
