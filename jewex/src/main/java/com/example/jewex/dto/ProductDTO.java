package com.example.jewex.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private int categoryID;
    private String description;
    private String imageName;
    
    
}
