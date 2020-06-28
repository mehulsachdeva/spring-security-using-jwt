package com.mehulsachdeva.authentication.util.JWTSecurity.service;

import com.mehulsachdeva.authentication.util.JWTSecurity.models.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<JwtUser, Long> {
    Optional<JwtUser> findByUsernameAndAndPassword(String username, String password);
}