package ulumium.com.ulumiumbackend.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ulumium.com.ulumiumbackend.Models.OldAndNewPasswordPair.OldAndNewPasswordPair;
import ulumium.com.ulumiumbackend.Models.User.User;
import ulumium.com.ulumiumbackend.Models.UserCredentials.UserCredentials;
import ulumium.com.ulumiumbackend.Services.UserService;

@RestController
@RequestMapping("user-service/v1/")
@AllArgsConstructor
public class UserAuthAndRegV1Controller {

    UserService userService;

    @PreAuthorize("permitAll()")
    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserCredentials userCredentials) {
        return this.userService.login(userCredentials);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("change-password")
    public ResponseEntity<String> changePassword(@RequestHeader String Authorization, @RequestBody OldAndNewPasswordPair oldAndNewPasswordPair) {
        return this.userService.changePassword(
                oldAndNewPasswordPair.getOldPassword(),
                oldAndNewPasswordPair.getNewPassword(),
                Authorization
        );
    }

    @PreAuthorize("permitAll()")
    @PostMapping("get-user-details")
    public ResponseEntity<String> getUserDetails(@RequestHeader String Authorization) {
        return this.userService.fetchUserData(Authorization);
    }
}
