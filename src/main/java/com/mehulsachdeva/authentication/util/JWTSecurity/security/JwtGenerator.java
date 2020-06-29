package com.mehulsachdeva.authentication.util.JWTSecurity.security;

import com.mehulsachdeva.authentication.util.JWTSecurity.security.Constants.Constants;
import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtUser;
import com.mehulsachdeva.authentication.util.ResponseBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtGenerator {

    @Autowired
    private ResponseBuilder responseBuilder;

    public Map<String, String> generate(JwtUser jwtUser) {

        if(jwtUser != null) {
            Claims claims = Jwts.claims()
                    .setSubject(jwtUser.getUsername());
            claims.put("userId", String.valueOf(jwtUser.getId()));
            claims.put("role", jwtUser.getRole());

            String jwtGenerated = Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + Constants.JWT_TOKEN_EXPIRATION))
                    .signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)
                    .compact();

            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    jwtGenerated,
                    Constants.NO_ERROR
            );
        }else {
            return responseBuilder.createResponse(
                    Constants.FAILURE_STATUS,
                    Constants.EMPTY_JET_GENERATED,
                    Constants.INVALID_CREDENTIALS_FOR_JWT_ERROR
            );
        }
    }
}
