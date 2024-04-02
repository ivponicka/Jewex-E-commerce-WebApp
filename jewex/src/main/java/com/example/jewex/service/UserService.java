package com.example.jewex.service;

import java.util.List;

import com.example.jewex.dto.UserRegistrationDTO;
import com.example.jewex.model.User;


public interface UserService  {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    User findUserByEmail(String email);

    List<UserRegistrationDTO> findAllUsers();
}
