package com.example.jewex.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.jewex.model.Category;
import com.example.jewex.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

     public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

  
	@SuppressWarnings("null")
	public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public Optional<Category> findCategory(int id){
        return categoryRepository.findById(id);
    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }

  

     
}
