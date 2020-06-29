package com.mehulsachdeva.authentication.controllers;

import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtUser;
import com.mehulsachdeva.authentication.util.JWTSecurity.security.JwtGenerator;
import com.mehulsachdeva.authentication.util.JWTSecurity.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private JwtGenerator jwtGenerator;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    public LoginController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public Map<String, String> generate(@RequestBody final JwtUser jwtUser) {
        JwtUser fetchedJwtUser = loginService.authorizeUser(jwtUser);
        return jwtGenerator.generate(fetchedJwtUser);
    }
}
