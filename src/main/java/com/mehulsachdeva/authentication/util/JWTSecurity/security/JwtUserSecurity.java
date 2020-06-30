package com.mehulsachdeva.authentication.util.JWTSecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehulsachdeva.authentication.models.Employee;
import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUserSecurity {
    public boolean hasAccess(Employee employee, Authentication authentication) {
        long jwtUserId = ((JwtUserDetails)authentication.getPrincipal()).getEmployeeId();
        if(employee.getEmployeeId() == jwtUserId)
            return true;
        return false;
    }
}
