package ulumium.com.ulumiumbackend.Models.User;

import ulumium.com.ulumiumbackend.Models.Orders.Orders;

import java.util.List;

public class UserData {

    private String email;

    private String name;

    private String lastname;

    private String address;

    private String phoneNumber;

    private List<Orders> orders;

    public String getEmail() {
        return email;
    }

    public UserData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserData setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserData setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserData setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserData setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public UserData setOrders(List<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public static UserData of(User user) {
        return new UserData()
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setLastname(user.getLastname())
                .setAddress(user.getAddress())
                .setPhoneNumber(user.getPhoneNumber())
                .setOrders(user.getOrders());
    }

    @Override
    public String toString() {
        return "UserData{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orders=" + orders +
                '}';
    }
}
