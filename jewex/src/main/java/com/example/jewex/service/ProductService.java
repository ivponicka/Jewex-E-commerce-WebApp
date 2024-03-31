package com.example.jewex.service;


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
   
   @SuppressWarnings("null")
public void addProduct(Product product){
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
}
