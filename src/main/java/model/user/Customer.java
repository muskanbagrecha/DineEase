package model.user;

import lombok.*;
import model.Restaurant;
import model.order.Order;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class Customer extends User{
    //reviews
    @DBRef
    private List<Order> orders = new ArrayList<>();

    @DBRef
    private List<Restaurant> favourites = new ArrayList<>();

    @DBRef
    private List<Restaurant> restaurantsSubscribed = new ArrayList<>();

}
