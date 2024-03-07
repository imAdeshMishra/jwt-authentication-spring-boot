package com.example.jwt_authentication.dao;

import com.example.jwt_authentication.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {

}
