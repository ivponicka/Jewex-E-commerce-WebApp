package com.example.jewex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.jewex.dto.UserRegistrationDTO;
import com.example.jewex.global.GlobalData;
import com.example.jewex.model.Product;
import com.example.jewex.model.User;
import com.example.jewex.service.CategoryService;
import com.example.jewex.service.ProductService;
import com.example.jewex.service.UserService;

import jakarta.validation.Valid;



@Controller
public class UserController {
    @Autowired
    CategoryService categoryService; 

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;


    @GetMapping({ "/", "/home"})
        public String homePage(Model model){
            model.addAttribute("cartCount", GlobalData.cart.size());
              List<Product> newProducts = productService.getProductsAddedLastFourWeeks();
        model.addAttribute("newProducts", newProducts);
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
            model.addAttribute("cartCount", GlobalData.cart.size());
            return "shop";
        }

    @GetMapping("/shop/category/{id}")
        public String showProductsByCategories(@PathVariable int id, Model model){
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("products", productService.getAllProductsByCategoryId(id));
            model.addAttribute("cartCount", GlobalData.cart.size());
            return "shop";
        }
    
        @GetMapping("/shop/viewproduct/{id}")
        public String getMethodName(@PathVariable int id, Model model) {
            model.addAttribute("product", productService.getProductById(id).get());
            model.addAttribute("cartCount", GlobalData.cart.size());
            return "view_product";
        }

        @GetMapping("/myAccount")
         public String myAccount(){
            return "users";
        }

        @GetMapping("/users/orders")
        public String userOrders() {
            return "users_orders";
        }

     @GetMapping("/users/settings")
        public String userSettings() {
            return "users_settings";
        }

        
        @GetMapping("/users/save")
        public String userSettingsUpdate(@Valid @ModelAttribute("user") UserRegistrationDTO userRegistrationDTO,
                               BindingResult result,
                               Model model) {
                User existingUser = userService.findUserByEmail(userRegistrationDTO.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userRegistrationDTO);
            return "/user_settings";
        }

        userService.saveUser(userRegistrationDTO);
        return "redirect:/user_settings?success";
    }
        
  
}

