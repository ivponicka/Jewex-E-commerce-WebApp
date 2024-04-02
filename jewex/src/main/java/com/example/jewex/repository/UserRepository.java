package com.example.jewex.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jewex.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{
     User findByEmail(String email);
}
