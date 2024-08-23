package model;

import lombok.Data;
import model.order.Order;
import model.user.RestaurantManager;
import model.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "restaurants")
public class Restaurant {

    @Id
    private String id;
    private String name;
    private String description;
    @DBRef
    private RestaurantManager manager;
    private String cuisine;
    private Address address;

    @DBRef
    private List<Order> orders = new ArrayList<>();

    private double rating;
    private int ratingCount;
    private String openingHours;

    private List<String> images;
    private boolean isOpened;
    private List<Dish> menu = new ArrayList<>();
}
