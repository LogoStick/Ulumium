package ulumium.com.ulumiumbackend.Security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ulumium.com.ulumiumbackend.Repositories.UserDao;
import ulumium.com.ulumiumbackend.Security.ServletFilters.LoginFilter;
import ulumium.com.ulumiumbackend.Security.ServletFilters.TokenChecker;
import ulumium.com.ulumiumbackend.Services.UserService;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    PasswordEncoder passwordEncoder;
    UserDao userDao;
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(
                        new TokenChecker(this.userDao, this.passwordEncoder),
                        UsernamePasswordAuthenticationFilter.class
                );
//                .addFilterBefore(
//                        new LoginFilter("/user-service/v1/login", this.userDao, this.passwordEncoder, authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class
//                );
    }


}
