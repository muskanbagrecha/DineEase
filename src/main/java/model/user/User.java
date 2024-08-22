package model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public abstract class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private long phone;
    private List<Address> addresses;

    public User(String name, String email, String password, long phone, List<Address> addresses) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.addresses = addresses;
    }
}
