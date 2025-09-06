package com.ecommerce.controller;

import com.ecommerce.models.*;
import org.springframework.web.bind.annotation.*;
@RestController
public class UserController {
    @PostMapping("userValidation")
    public String validateUser(User user){
        return  "Success";
    }
}
