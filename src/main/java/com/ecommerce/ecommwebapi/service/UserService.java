package com.ecommerce.ecommwebapi.service;

import com.ecommerce.ecommwebapi.dao.UserDAO;
import com.ecommerce.ecommwebapi.models.User;
import com.ecommerce.ecommwebapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        var users =userRepository.findAll();
        if(users!=null && users.size()>0){
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.typeMap(UserDAO.class, User.class)
                    .addMappings(
                            mapper -> {mapper.skip(User::setPassword);}
                    );
            List<User> userList = new ArrayList<>();
            for(var user:users){
                userList.add(modelMapper.map(user, User.class));
            }
            return userList;
        }
        return null;
    }

    public String validateUser(User user) {
        var dbUser = userRepository.findByEmailId(user.getEmailId());
        if(dbUser!=null){
            if(dbUser.getPassword().equals(user.getPassword()))
                return "Success";
            else
                return "Invalid User/Password";
        }else{
            return "User does not exist";
        }
    }

    public String createUser(User user) {
        try {
            UserDAO userDAO = modelMapper.map(user, UserDAO.class);
            userRepository.save(userDAO);
            return "Success";
        }catch (Exception ex){
            System.out.println(ex);
            return "Failed to create user";
        }
    }
}
