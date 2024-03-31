package com.example.jewex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.jewex.service.CategoryService;
import com.example.jewex.service.ProductService;


@Controller
public class UserController {
    @Autowired
    CategoryService categoryService; 

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
        public String homePage(Model model){
            return "index";
        }

         @GetMapping("/about")
        public String about(){
            return "about";
        }

      @GetMapping("/shop")
        public String shopPage(Model model){
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("products", productService.getAllProducts());
            return "shop";
        }

    @GetMapping("/shop/category/{id}")
        public String showProductsByCategories(@PathVariable int id, Model model){
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("products", productService.getAllProductsByCategoryId(id));
            return "shop";
        }
    
        @GetMapping("/shop/viewproduct/{id}")
        public String getMethodName(@PathVariable int id, Model model) {
            model.addAttribute("product", productService.getProductById(id).get());
            return "view_product";
        }
    
        
}

