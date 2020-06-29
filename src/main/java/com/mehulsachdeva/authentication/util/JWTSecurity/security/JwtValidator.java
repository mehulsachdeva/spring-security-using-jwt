package com.mehulsachdeva.authentication.util.JWTSecurity.security;

import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtUser;
import com.mehulsachdeva.authentication.util.JWTSecurity.security.Constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    private String secret = Constants.SECRET_KEY;

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setEmployeeId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));

        }catch (Exception e) {
            System.out.println(e);
        }
        return jwtUser;
    }
}