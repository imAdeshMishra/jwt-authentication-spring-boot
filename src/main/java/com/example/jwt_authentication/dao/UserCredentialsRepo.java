package com.example.jwt_authentication.dao;
import com.example.jwt_authentication.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials,Integer> {

    public Optional<UserCredentials> findByEmail(String email);
}
