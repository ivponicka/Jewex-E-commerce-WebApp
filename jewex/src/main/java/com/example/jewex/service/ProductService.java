package com.example.jewex.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jewex.model.Product;
import com.example.jewex.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

   public List<Product> getAllProducts(){
    return productRepository.findAll();
   }
   

public void addProduct(Product product){
    product.setCreationDate(LocalDate.now());
      productRepository.save(product);
   }

   public void deleteProductById(long id){
     productRepository.deleteById(id);;
   }

   public Optional<Product> getProductById(long id){
     return productRepository.findById(id);
   }

 
   public List<Product> getAllProductsByCategoryId(int id){
     return productRepository.findAllByCategory_Id(id);
   }

   
    public List<Product> getProductsAddedLastFourWeeks() {
        // Calculate the start date (4 weeks ago) and end date (today)
        LocalDate startDate = LocalDate.now().minusWeeks(4);
        LocalDate endDate = LocalDate.now();
        
        // Retrieve products added within the specified date range
        return productRepository.findByCreationDateBetween(startDate, endDate);
    }

}
