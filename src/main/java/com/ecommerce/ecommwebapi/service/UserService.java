package com.ecommerce.ecommwebapi.service;

import com.ecommerce.ecommwebapi.dao.UserDAO;
import com.ecommerce.ecommwebapi.models.ECommerceCommonResponse;
import com.ecommerce.ecommwebapi.models.UpdatePasswordDTO;
import com.ecommerce.ecommwebapi.models.User;
import com.ecommerce.ecommwebapi.models.UserCreateDTO;
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
        if(users!=null && !users.isEmpty()){
            List<User> userList = new ArrayList<>();
            for(var user:users){
                userList.add(modelMapper.map(user, User.class));
            }
            return userList;
        }
        return null;
    }

    public ECommerceCommonResponse validateUser(UpdatePasswordDTO updatePasswordDTO) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        var dbUser = userRepository.findByEmailId(updatePasswordDTO.getEmailId());
        if(dbUser!=null){
            if(dbUser.getPassword().equals(updatePasswordDTO.getOldPassword())) {
                response.setReturnCode(0);
                response.setErrorMessage("Success");
                return response;
            }else {
                response.setReturnCode(1);
                response.setErrorMessage("Invalid User/Password");
                return response;
            }
        }else{
            response.setReturnCode(1);
            response.setErrorMessage("User does not exist");
            return response;
        }
    }

    public ECommerceCommonResponse createUser(UserCreateDTO userCreateDTO) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
         var dbUser = userRepository.findByEmailId(userCreateDTO.getEmailId());
         if(dbUser!=null){
             response.setReturnCode(1);
                response.setErrorMessage("User already exists");
             return response;
         }
        try {
            UserDAO userDAO = modelMapper.map(userCreateDTO, UserDAO.class);
            userRepository.save(userDAO);
            response.setReturnCode(0);
            response.setErrorMessage("Success");
            return response;
        }catch (Exception ex){
            response.setReturnCode(1);
            response.setErrorMessage("Failed to create user");
            return response;
        }
    }

    public ECommerceCommonResponse updateProfile(User user) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        var dbUser = userRepository.findByEmailId(user.getEmailId());
        if(dbUser!=null){
            try {
                modelMapper.map(user,dbUser);
                userRepository.save(dbUser);
                response.setReturnCode(0);
                response.setErrorMessage("Success");
                return response;
            }catch (Exception ex){
                response.setReturnCode(1);
                response.setErrorMessage("Failed to update profile");
                return response;
            }
        }
        response.setReturnCode(1);
        response.setErrorMessage("User does not exist");
        return response;
    }

    public ECommerceCommonResponse updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        var dbUser = userRepository.findByEmailId(updatePasswordDTO.getEmailId());
        if(dbUser!=null){
            if(dbUser.getPassword().equals(updatePasswordDTO.getOldPassword())) {
                dbUser.setPassword(updatePasswordDTO.getNewPassword());
                userRepository.save(dbUser);
                response.setReturnCode(0);
                response.setErrorMessage("Success");
                return response;
            }else {
                response.setReturnCode(1);
                response.setErrorMessage("Invalid User/Password");
                return response;
            }
        }else{
            response.setReturnCode(1);
            response.setErrorMessage("User does not exist");
            return response;
        }
    }

    public ECommerceCommonResponse deleteUser(String emailId) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        var dbUser = userRepository.findByEmailId(emailId);
        if(dbUser!=null){
            try {
                userRepository.delete(dbUser);
                response.setReturnCode(0);
                response.setErrorMessage("Success");
                return response;
            }catch (Exception ex){
                response.setReturnCode(1);
                response.setErrorMessage("Failed to delete user");
                return response;
            }
        }else{
            response.setReturnCode(1);
            response.setErrorMessage("User does not exist");
            return response;
        }
    }
}
