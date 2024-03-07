package com.example.jwt_authentication.controllers;

import com.example.jwt_authentication.models.User;
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
    public ResponseEntity<List<User>> getUser() {
        List<User> users = userServices.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping("/users")
    public ResponseEntity<User> addBook(@RequestBody User user) {

        //TODO: process POST request
        User u=null;

        try {
            u = this.userServices.addUser(user);
            return ResponseEntity.of(Optional.of(u));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



    @GetMapping("/current-user")
    public String getLoggedinUser(Principal principal){
        return principal.getName();
    }
}
