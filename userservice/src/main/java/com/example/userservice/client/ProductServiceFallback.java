package com.example.userservice.client;

import org.springframework.stereotype.Component;

import com.example.userservice.model.Product;

import java.util.Collections;
import java.util.List;

@Component
public class ProductServiceFallback implements ProductClient
{

    @Override
    public Product getProductById(Long id) {
        // Return a default product in case of failure
    	System.out.println("in ProductServiceFallback");
        return new Product(id, "Default Product", 0.0);
    }

    @Override
    public List<Product> getAllProducts() {
        // Return an empty list in case of failure
    	System.out.println("working");
        return Collections.emptyList();
    }
}
