package ulumium.com.ulumiumbackend.Models.User;

import lombok.Getter;
import ulumium.com.ulumiumbackend.Models.Orders.Orders;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class User extends UserDetailsImplementation {

    private String name;

    private String lastname;

    private String address;

    private String phoneNumber;

    private String token;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Orders> orders;

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User setOrders(List<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }
}
