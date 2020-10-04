package ulumium.com.ulumiumbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulumium.com.ulumiumbackend.Models.User.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    User findByToken(String token);

}
