package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dish {
    @Getter
    private String id;
    private String name;
    private boolean isVeg;
    private int kcal;
//    private FoodCategory category;
    private String description;
    private List<String> images;
    private double price;

    public Dish(String name, boolean isVeg, int kcal, String description, List<String> images, double price) {
        this.name = name;
        this.isVeg = isVeg;
        this.kcal = kcal;
        this.description = description;
        this.images = images;
        this.price = price;
    }
}
