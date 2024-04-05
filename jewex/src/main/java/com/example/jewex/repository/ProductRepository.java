package com.example.jewex.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.jewex.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
     List<Product> findAllByCategory_Id(int id);
     List<Product> findAllByCategory_Name(String name);
     List<Product> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);
   

     
}