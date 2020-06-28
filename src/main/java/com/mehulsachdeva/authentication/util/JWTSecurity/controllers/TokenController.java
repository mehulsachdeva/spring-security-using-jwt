package com.mehulsachdeva.authentication.util.JWTSecurity.controllers;

import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtUser;
import com.mehulsachdeva.authentication.util.JWTSecurity.security.JwtGenerator;
import com.mehulsachdeva.authentication.util.JWTSecurity.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    private JwtGenerator jwtGenerator;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {
        boolean status = loginService.authorizeUser(jwtUser);
        if(status)
            return jwtGenerator.generate(jwtUser);
        return null;
    }
}
