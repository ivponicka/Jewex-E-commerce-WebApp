package com.example.jewex.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.jewex.dto.UserRegistrationDTO;
import com.example.jewex.model.User;
import com.example.jewex.service.UserService;

import jakarta.validation.Valid;


@Controller
public class UserRegistrationController {


    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    
   @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserRegistrationDTO user = new UserRegistrationDTO();
        model.addAttribute("user", user);
        return "register";
    }

    
    // handler method to handle user registration form submit request
    @SuppressWarnings("null")
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserRegistrationDTO userRegistrationDTO,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userRegistrationDTO.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userRegistrationDTO);
            return "/register";
        }

        userService.saveUser(userRegistrationDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserRegistrationDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
