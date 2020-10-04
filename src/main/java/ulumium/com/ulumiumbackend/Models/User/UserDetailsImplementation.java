package ulumium.com.ulumiumbackend.Models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ulumium.com.ulumiumbackend.Models.Role.Role;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@MappedSuperclass
@Getter
@NoArgsConstructor
public class UserDetailsImplementation implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    private String role;

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority[]{new SimpleGrantedAuthority(this.role)});
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public UserDetailsImplementation setId(int id) {
        this.id = id;
        return this;
    }

    public UserDetailsImplementation setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDetailsImplementation setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDetailsImplementation setRole(Role role) {
        this.role = role.getRole();
        return this;
    }

    public UserDetailsImplementation setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
        return this;
    }

    public UserDetailsImplementation setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
        return this;
    }

    public UserDetailsImplementation setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public UserDetailsImplementation setEnabled(boolean enabled) {
        isEnabled = enabled;
        return this;
    }
}
