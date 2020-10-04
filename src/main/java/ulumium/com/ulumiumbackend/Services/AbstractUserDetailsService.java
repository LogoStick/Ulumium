package ulumium.com.ulumiumbackend.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ulumium.com.ulumiumbackend.Models.User.User;
import ulumium.com.ulumiumbackend.Models.UserCredentials.UserCredentials;

@Service
public interface AbstractUserDetailsService extends UserDetailsService {
    ResponseEntity<String> registerUser(User user);
    ResponseEntity<String> login(UserCredentials userCredentials);
    ResponseEntity<String> changePassword(String currentPassword, String newPassword, String token);
    ResponseEntity<String> fetchUserData(String token);
}
