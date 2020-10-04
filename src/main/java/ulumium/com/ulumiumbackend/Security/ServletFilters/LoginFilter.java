package ulumium.com.ulumiumbackend.Security.ServletFilters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ulumium.com.ulumiumbackend.Models.User.User;
import ulumium.com.ulumiumbackend.Repositories.UserDao;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    private User inRequestUser;
    private User dbUser;

    public LoginFilter(
            String defaultFilterProcessesUrl,
            UserDao userDao,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws AuthenticationException, IOException, ServletException {
        User inRequestUser = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
        inRequestUser.setEmail(inRequestUser.getUsername().toLowerCase());
        System.out.println(this.userDao);

        if(userDao.findByEmail(inRequestUser.getUsername()) != null) {
            User dbUser = userDao.findByEmail(inRequestUser.getUsername());
            if(passwordEncoder.matches(inRequestUser.getPassword(), dbUser.getPassword())) {
                this.inRequestUser = inRequestUser;
                this.dbUser = dbUser;
                this.generateAuthenticationResponse(httpServletResponse, AuthenticationStatus.USER_SUCCESSFUL_AUTHENTICATION);
                return
                        getAuthenticationManager()
                        .authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        inRequestUser.getUsername(),
                                        inRequestUser.getPassword(),
                                        dbUser.getAuthorities())
                        );
            } else {
                this.generateAuthenticationResponse(httpServletResponse, AuthenticationStatus.USER_CREDENTIALS_MISMATCH);
                return null;
            }
        } else {
            this.generateAuthenticationResponse(httpServletResponse, AuthenticationStatus.USER_CREDENTIALS_MISMATCH);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException
    {

        String token = Jwts.builder()
                .setSubject(this.inRequestUser.getUsername()).signWith(SignatureAlgorithm.HS512, "key".getBytes()).compact();

        this.dbUser.setToken(token);
        this.userDao.save(this.dbUser);
        response.addHeader("Authorization", "Bearer " + token);
        chain.doFilter(request, response);
    }

    private void generateAuthenticationResponse(
            HttpServletResponse httpServletResponse,
            AuthenticationStatus authenticationStatus
    ) throws IOException {
        String response = "";

                if(true) {
                    response = new JSONObject()
                            .put("status", authenticationStatus.getStatus())
                            .put("statusText", authenticationStatus.getStatusText())
                            .put("successfulAuthentication", authenticationStatus.isAuthenticationSuccessful())
                            .toString();
                } else {
                    response = new JSONObject()
                            .put("status", authenticationStatus.getStatus())
                            .put("statusText", authenticationStatus.getStatusText())
                            .put("successfulAuthentication", authenticationStatus.isAuthenticationSuccessful())
                            .toString();
                }
        httpServletResponse.setContentType("application/json");
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Expose-Headers", "Authorization");
        httpServletResponse.getOutputStream().print(response);
    }
}

enum AuthenticationStatus {
    USER_CREDENTIALS_MISMATCH(0, "Credentials mismatch", false),
    USER_IS_NULL(1, "User has been passed incorrectly", false),
    USER_SUCCESSFUL_AUTHENTICATION(100, "User has been authenticated successfully", true);
    private int status;
    private String statusText;
    private boolean successfulAuthentication;

    public int getStatus() {
        return status;
    }

    public String getStatusText() {
        return statusText;
    }

    public boolean isAuthenticationSuccessful() {
        return this.successfulAuthentication;
    }

    AuthenticationStatus(int status, String statusText, boolean isAuthenticationSuccessful) {
        this.status = status;
        this.statusText = statusText;
        this.successfulAuthentication = isAuthenticationSuccessful;
    }
}
