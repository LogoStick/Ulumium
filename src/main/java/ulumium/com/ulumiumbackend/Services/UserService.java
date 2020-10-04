package ulumium.com.ulumiumbackend.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ulumium.com.ulumiumbackend.HttpResponseGenerators.*;
import ulumium.com.ulumiumbackend.Models.ResponseGenearatorArgument.ResponseGeneratorArgument;
import ulumium.com.ulumiumbackend.Models.Role.Role;
import ulumium.com.ulumiumbackend.Models.User.User;
import ulumium.com.ulumiumbackend.Models.UserCredentials.UserCredentials;
import ulumium.com.ulumiumbackend.Repositories.UserDao;

@Service("UserServiceV1")
@AllArgsConstructor
public class UserService implements AbstractUserDetailsService {

    UserDao userDao;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userDao.findByEmail(username);
    }

    @Override
    public ResponseEntity<String> login(UserCredentials userCredentials) {
        ResponseGenerator responseGenerator = new LoginResponseGenerator();
        String email = userCredentials.getEmail().toLowerCase();
        String password = userCredentials.getPassword();
        User foundUser = this.userDao.findByEmail(email);
        if(foundUser != null) {

            if(passwordEncoder.matches(password, foundUser.getPassword())) {
                String token = Jwts.builder()
                        .signWith(SignatureAlgorithm.HS512, "key".getBytes())
                        .setSubject(userCredentials.getEmail())
                        .compact();
                foundUser.setToken(token);
                this.userDao.save(foundUser);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + token);
                return responseGenerator.generateResponse(
                        new ResponseGeneratorArgument()
                        .setSuccessfulOperation(true)
                        .setExtraHttpHeaders(headers)
                );
            } else {
                return responseGenerator.generateResponse(
                        new ResponseGeneratorArgument().setSuccessfulOperation(false)
                );
            }

        } else {
            return responseGenerator.generateResponse(
                    new ResponseGeneratorArgument().setSuccessfulOperation(false)
            );
        }
    }

    @Override
    public ResponseEntity<String> changePassword(String currentPassword, String newPassword, String token) {
        ResponseGenerator responseGenerator = new ChangePassResponseGenerator();
        token = token.replace("Bearer ", "");
        User foundUser = this.userDao.findByToken(token);
        if(foundUser == null) return responseGenerator.generateResponse(
                new ResponseGeneratorArgument().setSuccessfulOperation(false)
        );
        if (this.passwordEncoder.matches(currentPassword, foundUser.getPassword())) {
            foundUser.setPassword(this.passwordEncoder.encode(newPassword));
            this.userDao.save(foundUser);
            return responseGenerator.generateResponse(
                    new ResponseGeneratorArgument().setSuccessfulOperation(true)
            );
        } else return responseGenerator.generateResponse(
                new ResponseGeneratorArgument().setSuccessfulOperation(false)
        );
    }

    @Override
    public ResponseEntity<String> registerUser(User user) {
        ResponseGenerator responseGenerator = new RegistrationResponseGenerator();
        if(user == null)
            return responseGenerator.generateResponse(
                    new ResponseGeneratorArgument().setSuccessfulOperation(false)
            );
        if(userDao.findByEmail(user.getEmail().toLowerCase()) != null)
            return responseGenerator.generateResponse(
                    new ResponseGeneratorArgument().setSuccessfulOperation(false)
            );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        user.setRole(Role.USER);
        user.setOrders(null);
        this.userDao.save(user);
        return responseGenerator.generateResponse(
                new ResponseGeneratorArgument().setSuccessfulOperation(true)
        );
    }

    @Override
    public ResponseEntity<String> fetchUserData(String token) {
        ResponseGenerator responseGenerator = new UserDataResponseGenerator();
        token = token.replace("Bearer ", "");
        User foundUser = this.userDao.findByToken(token);
        if(foundUser == null) return responseGenerator.generateResponse(
                new ResponseGeneratorArgument().setSuccessfulOperation(false)
        );
        else return responseGenerator.generateResponse(
                    new ResponseGeneratorArgument().setSuccessfulOperation(true).setUser(foundUser)
        );

    }

}
