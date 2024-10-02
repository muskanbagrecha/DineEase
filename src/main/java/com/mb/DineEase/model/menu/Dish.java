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
    private String name;
    private Boolean isVeg;
    private Integer kcal;
    @DBRef
    @JsonIgnore
    private FoodCategory category;
    private String description;
    private String image;
    private Double price;
    private Boolean isAvailable;

    public Dish() {
    }

    public Dish(String name, boolean isVeg, int kcal, String description, String image, double price, boolean isAvailable, FoodCategory category) {
        this.name = name;
        this.isVeg = isVeg;
        this.kcal = kcal;
        this.description = description;
        this.image = image;
        this.price = price;
        this.isAvailable = isAvailable;
        this.category = category;
    }
}
