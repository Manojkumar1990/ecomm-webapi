package com.ecommerce.ecommwebapi.service;

import com.ecommerce.ecommwebapi.dao.*;
import com.ecommerce.ecommwebapi.models.*;
import com.ecommerce.ecommwebapi.repository.*;
import com.ecommerce.ecommwebapi.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public List<User> getAllUsers() {
        var users =userRepository.findAll();
        if(users!=null && !users.isEmpty()){
            List<User> userList = new ArrayList<>();
            for(var user:users){
                var userDTO = modelMapper.map(user, User.class);
                userDTO.setRoleName(user.getRole()!=null?user.getRole().getRoleName():null);
                userList.add(userDTO);
            }
            return userList;
        }
        return null;
    }

    public ECommerceCommonResponse validateUser(UpdatePasswordDTO updatePasswordDTO, HttpServletResponse httpResponse) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        var dbUser = userRepository.findByEmailId(updatePasswordDTO.getEmailId());
        if(dbUser!=null){
            if(dbUser.getPassword().equals(updatePasswordDTO.getOldPassword())) {
                String jwtToken = JwtUtil.generateToken(dbUser.getEmailId(), dbUser.getRole().getRoleName());
                Cookie cookie = new Cookie("jwtToken", jwtToken);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                httpResponse.addCookie(cookie);
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
                response.setErrorMessage("User alreay exists");
             return response;
         }
        try {
            UserDAO userDAO = modelMapper.map(userCreateDTO, UserDAO.class);
            RoleDAO roleDAO = roleRepository.findByRoleNameIgnoreCase(userCreateDTO.getRoleName());
            userDAO.setRole(roleDAO);
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
                RoleDAO roleDAO = roleRepository.findByRoleNameIgnoreCase(user.getRoleName());
                dbUser.setRole(roleDAO);
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
