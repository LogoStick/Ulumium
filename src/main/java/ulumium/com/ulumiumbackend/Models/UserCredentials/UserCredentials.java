package ulumium.com.ulumiumbackend.Models.UserCredentials;

import lombok.Getter;

@Getter
public class UserCredentials {

    private String email;
    private String password;

    public UserCredentials setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserCredentials setPassword(String password) {
        this.password = password;
        return this;
    }
}
