package com.mehulsachdeva.authentication.util.JWTSecurity.security;

import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtAuthenticationToken;
import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtUser;
import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtUserDetails;
import com.mehulsachdeva.authentication.util.JWTSecurity.security.Constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private JwtValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        JwtUser jwtUser = validator.validate(token);

        if (jwtUser == null) {
            throw new RuntimeException(Constants.INCORRECT_JWT_TOKEN);
        }

        List<GrantedAuthority> grantedAuthorities =  new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(jwtUser.getRole()));

        return new JwtUserDetails(jwtUser.getUsername(),
                jwtUser.getEmployeeId(),
                token,
                grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}