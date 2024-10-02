package com.mb.DineEase.request.order;

import com.mb.DineEase.model.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String restaurantId;
    private OrderItem[] items;
}
