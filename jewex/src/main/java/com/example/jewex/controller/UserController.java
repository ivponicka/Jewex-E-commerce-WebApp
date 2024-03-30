package com.example.jewex.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.jewex.model.Category;
import com.example.jewex.service.CategoryService;

import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class UserController {

    @Autowired
     CategoryService categoryService;
   
    @GetMapping("/shop")
    public String shopping(){
        return "shop";
    }

      @GetMapping("/admin/products")
    public String products(){
        return "admin_products";
    }

    @GetMapping("/admin")
    public String adminHome(){
        return "admin_home";
    }

     @GetMapping("/admin/categories")
    public String categories(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin_categories";
    }


    @GetMapping("/admin/categories/addCategory")
    public String getAddCategory(Model model){
        model.addAttribute("category", new Category());
        return "admin_add_category";
    }

    @PostMapping("/admin/categories/addCategory")
    public String postAddCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    
     @GetMapping("/admin/categories/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id){
         categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

      @GetMapping("/admin/categories/updateCategory/{id}")
    public String updateCategory(@PathVariable int id, Model model){
        Optional <Category> cat = categoryService.findCategory(id);
        if(cat.isPresent()){
            model.addAttribute("category", cat.get());
            return "admin_add_category";
        } else {
            return "404";
        }

       
    }
}
