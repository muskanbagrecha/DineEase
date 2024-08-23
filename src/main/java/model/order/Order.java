package model.order;

import model.Dish;
import model.Restaurant;
import model.user.Customer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    @DBRef
    private Customer user;

    @DBRef
    private Restaurant restaurant;

    private OrderStatus status;

    private double amount;

    private Date orderTime;

    private List<Dish> dishes;
}
