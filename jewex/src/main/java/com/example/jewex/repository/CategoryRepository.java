package com.example.jewex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jewex.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
  
    
}
