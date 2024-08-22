package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "restaurants")
public class Restaurant {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private long phone;
    private Address address;

}
