package com.example.jewex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.jewex.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class CartController {
    @Autowired
    ProductService productService;;

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
    
    @GetMapping("/addToCard/{id}")
    public String addToCartGet(@PathVariable int id) {
        GlobalData
    }
    
}
