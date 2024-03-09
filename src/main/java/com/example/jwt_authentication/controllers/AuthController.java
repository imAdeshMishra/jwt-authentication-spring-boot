package com.example.jwt_authentication.controllers;

import com.example.jwt_authentication.entities.UserCredentials;
import com.example.jwt_authentication.models.JwtRequest;
import com.example.jwt_authentication.models.JwtResponse;
import com.example.jwt_authentication.security.JwtHelper;
import com.example.jwt_authentication.services.CustomUserDetailsService;
import com.example.jwt_authentication.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserServices userServices;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        System.out.println(request.getEmail() +" "+ request.getPassword());

        this.doAuthenticate(request.getEmail(), request.getPassword());

        System.out.println(request.getEmail() +" "+ request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        System.out.println("Email is" + userDetails.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            System.out.println("doAuthenticate method called");
            manager.authenticate(authentication);
            System.out.println("doAuthenticate method completed");

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create-user")
    public UserCredentials createUser(@RequestBody UserCredentials user){
        return userServices.createUser(user);
    }
}
