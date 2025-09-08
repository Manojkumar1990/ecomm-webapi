package com.ecommerce.ecomm_api.controller;

import com.ecommerce.ecomm_api.models.User;
import com.ecommerce.ecomm_api.service.UserService;
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
        return "Valid User";
    }
}
