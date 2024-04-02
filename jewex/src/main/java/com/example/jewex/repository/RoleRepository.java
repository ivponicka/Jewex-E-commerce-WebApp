package com.example.jewex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jewex.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}