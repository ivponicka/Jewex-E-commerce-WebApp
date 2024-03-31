package com.example.jewex.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.jewex.dto.ProductDTO;
import com.example.jewex.model.Category;
import com.example.jewex.model.Product;
import com.example.jewex.service.CategoryService;
import com.example.jewex.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;






@Controller
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @Autowired
     CategoryService categoryService;

     @Autowired
     ProductService productService;
   
 


    @GetMapping("/admin")
    public String adminHome(){
        return "admin_home";
    }

    // Admin - categories

     @GetMapping("/admin/categories")
    public String categories(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin_categories";
    }

    @GetMapping("/admin/categories/addCategory")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "admin_add_category";
    }

    @PostMapping("/admin/categories/addCategory")
    public String addCategoryPost(@ModelAttribute("category") Category category) {
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

    // Admin - products
   @GetMapping("/admin/products")
    public String prodcuts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin_products";
    }
    

    @GetMapping("/admin/products/addProduct")
    public String addNewProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin_add_product";
    }
  
    @PostMapping("/admin/products/addProduct")
    public String addNewProductPost(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.findCategory(productDTO.getCategoryID()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }
 
    @GetMapping("/admin/products/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/products/updateProduct/{id}")
    public String updateProduct(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategoryID(product.getCategory().getId());
        productDTO.setImageName(product.getImageName());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("productDTO", productDTO);
        return "admin_add_product";
    }
    
    
}
