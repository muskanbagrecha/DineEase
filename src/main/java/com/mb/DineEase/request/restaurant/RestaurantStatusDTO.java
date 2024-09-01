package com.mb.DineEase.request.restaurant;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantStatusDTO {

    private String restaurantId;
    private String isOpened;

}