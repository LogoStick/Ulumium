package ulumium.com.ulumiumbackend.Models.Role;

import com.google.common.collect.Lists;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    USER(
            "ROLE_USER",
            Lists.newArrayList()
    ),
    ADMIN(
            "ROLE_ADMIN",
            Lists.newArrayList()
    );

    Role(String role, List<String> authorities) {
        this.role = role;
        this.authorities = authorities;
    }

    private String role;

    private List<String> authorities;

    public String getRole() {
        return role;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }

}
