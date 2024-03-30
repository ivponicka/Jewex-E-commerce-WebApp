package com.example.jewex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
   
    @GetMapping("/shop")
    public String shopping(){
        return "shop";
    }
}
