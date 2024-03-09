package com.example.jwt_authentication.services;

import com.example.jwt_authentication.dao.UserCredentialsRepo;
import com.example.jwt_authentication.entities.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserServices {

    @Autowired
    UserCredentialsRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserCredentials> getAllUsers(){
        List<UserCredentials> users = (List<UserCredentials>) this.userRepo.findAll();
        System.out.println(users);
        return users;
    }

    public UserCredentials createUser(UserCredentials user){
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


}
