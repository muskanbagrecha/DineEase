package com.mb.DineEase.service.order;

import com.mb.DineEase.model.order.Order;
import com.mb.DineEase.model.order.OrderItem;
import com.mb.DineEase.model.restaurant.Restaurant;
import com.mb.DineEase.respository.order.OrderRepository;
import com.mb.DineEase.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public String createOrder(String userId, String restaurantId, OrderItem[] items) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if(!restaurant.isOpened()){
            return "Restaurant is not opened";
        }
        if(items.length==0){
            return "No items added";
        }
//        if(!items.stream().allMatch(item -> isItemAvailable(restaurant, item.().getId()))){
//            return "Some items are not available";
//        }

        //check stock
        //create order
        return "";
    }

    private boolean isItemAvailable(Restaurant restaurant, String dishId) {
        return restaurant.getMenu().stream().anyMatch(dish -> dish.getId().equals(dishId));
    }

    @Override
    public Order getOrderById(String orderId) {
        return null;
    }

}
