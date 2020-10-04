package ulumium.com.ulumiumbackend.Security.ServletFilters;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.GenericFilterBean;
import ulumium.com.ulumiumbackend.Models.User.User;
import ulumium.com.ulumiumbackend.Repositories.UserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenChecker extends GenericFilterBean {

    private User user;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public TokenChecker(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        Authentication auth = null;

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null) {
            String token = authorizationHeader.replace("Bearer ", "");
            User dbUser = this.userDao.findByToken(token);
            this.user = dbUser;
            if(dbUser != null) {
                auth = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
            }
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
