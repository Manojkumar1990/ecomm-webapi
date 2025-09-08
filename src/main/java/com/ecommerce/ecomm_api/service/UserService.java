package com.ecommerce.ecomm_api.service;

import com.ecommerce.ecomm_api.models.User;
import com.ecommerce.ecomm_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        var users =userRepository.findAll();
        List<User> userList = new ArrayList<User>();
//        for (var user:users) {
//            User userModel = new User();
//            userModel.setUserName(user.getFirst_name());
//            userModel.setEmail_id(user.getEmail_id());
//            userList.add(userModel);
//        }
        return userList;
    }
}
