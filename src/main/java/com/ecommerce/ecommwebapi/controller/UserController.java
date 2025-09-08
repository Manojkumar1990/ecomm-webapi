package com.ecommerce.ecommwebapi.controller;

import com.ecommerce.ecommwebapi.models.User;
import com.ecommerce.ecommwebapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("userValidation")
    public String validateUser(User user){
        return userService.validateUser(user);
    }
    @PostMapping("createUser")
    public String createUser(User user){
        return userService.createUser(user);
    }
}
