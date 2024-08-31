package com.mb.DineEase.controller.restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @GetMapping("/menu")
    public String getMenu() {
        return "Menu";
    }

}
