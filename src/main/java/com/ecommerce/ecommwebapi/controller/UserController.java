package com.ecommerce.ecommwebapi.controller;

import com.ecommerce.ecommwebapi.models.ECommerceCommonResponse;
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
    public ECommerceCommonResponse validateUser(@RequestBody User user){
        return userService.validateUser(user);
    }
    @PostMapping("createUser")
    public ECommerceCommonResponse createUser(User user){
        return userService.createUser(user);
    }
    @PutMapping("updateProfile")
    public ECommerceCommonResponse updateProfile(@RequestBody User user) {
        return userService.updateProfile(user);
    }
    @PutMapping("updatePassword")
    public ECommerceCommonResponse updatePassword(@RequestBody User user) {
        return userService.updatePassword(user);
    }
}
