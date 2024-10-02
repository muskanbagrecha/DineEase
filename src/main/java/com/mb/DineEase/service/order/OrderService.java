package com.mb.DineEase.service.order;

import com.mb.DineEase.model.order.Order;
import com.mb.DineEase.model.order.OrderItem;

public interface OrderService {
    public String createOrder(String userId, String restaurantId, OrderItem[] items);
    public Order getOrderById(String orderId);
}
