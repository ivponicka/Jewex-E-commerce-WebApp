package com.example.jewex.global;

import java.util.ArrayList;
import java.util.List;

import com.example.jewex.model.Product;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<>();
    }
    
}
