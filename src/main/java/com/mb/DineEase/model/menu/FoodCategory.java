package com.mb.DineEase.model.menu;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class FoodCategory {
    @Id
    private String id;
    private String name;

    public FoodCategory(String name) {
        this.name = name;
    }
}
