package com.example.jwt_authentication.controllers;

import com.example.jwt_authentication.entities.UserCredentials;
import com.example.jwt_authentication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserServices userServices;
    @GetMapping("/users")
    public ResponseEntity<List<UserCredentials>> getUser() {
        List<UserCredentials> users = userServices.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }




    @GetMapping("/current-user")
    public String getLoggedinUser(Principal principal){
        return principal.getName();
    }
}
