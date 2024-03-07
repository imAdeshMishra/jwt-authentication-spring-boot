package com.example.jwt_authentication.services;

import com.example.jwt_authentication.dao.UserRepo;
import com.example.jwt_authentication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserServices {

    @Autowired
    UserRepo userRepo;

    public List<User> getAllUsers(){
        List<User> users = (List<User>) this.userRepo.findAll();
        System.out.println(users);
        return users;
    }

    public User addUser(User u){
        // books.add(b);
        User result = userRepo.save(u);
        return result;
    }


}
