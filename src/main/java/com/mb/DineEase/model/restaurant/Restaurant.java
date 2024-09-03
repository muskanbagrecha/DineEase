package com.mb.DineEase.model.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mb.DineEase.model.address.Address;
import com.mb.DineEase.model.ContactInformation;
import com.mb.DineEase.model.menu.Dish;
import com.mb.DineEase.model.Review;
import lombok.Data;
import com.mb.DineEase.model.order.Order;
import com.mb.DineEase.model.user.RestaurantManager;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "restaurants")
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Restaurant {

    @Id
    private String id;
    @TextIndexed
    @NotNull
    private String name;
    @TextIndexed
    private String description;

    @DBRef
    @NotNull
    private RestaurantManager manager;
    @TextIndexed
    private List<String> cuisine;
    private Address address;

    @GeoSpatialIndexed
    @NotNull
    private GeoJsonPoint location;

    @DBRef
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    private double rating;
    private int ratingCount;
    private String openingHours;

    private List<String> images;
    private boolean isOpened;
    @TextIndexed
    private List<Dish> menu = new ArrayList<>();

    @DBRef
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @NotNull
    private ContactInformation contactInformation;
}
