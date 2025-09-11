package com.ecommerce.ecommwebapi.controller;

import com.ecommerce.ecommwebapi.models.*;
import com.ecommerce.ecommwebapi.service.UserService;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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
    public ECommerceCommonResponse validateUser(@RequestBody UpdatePasswordDTO user, HttpServletResponse httpResponse){
        return userService.validateUser(user,httpResponse);
    }
    @PostMapping("user")
    public ECommerceCommonResponse createUser(@RequestBody UserCreateDTO user){
        return userService.createUser(user);
    }
    @PutMapping("user")
    public ECommerceCommonResponse updateProfile(@RequestBody User user) {
        return userService.updateProfile(user);
    }
    @PutMapping("updatePassword")
    public ECommerceCommonResponse updatePassword(@RequestBody UpdatePasswordDTO user) {
        return userService.updatePassword(user);
    }
    @DeleteMapping("user")
    public ECommerceCommonResponse deleteUser(@RequestParam(name="emailId") String emailId) {
        return userService.deleteUser(emailId);
    }
}
