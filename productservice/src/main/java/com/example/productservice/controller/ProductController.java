package com.example.productservice.controller;

import com.example.productservice.model.Product;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController

//@RequestMapping("/products")
public class ProductController {

	private List<Product> productList = Arrays.asList(new Product(1L, "Laptop", 999.99),
			new Product(2L, "Smartphone", 499.99), new Product(3L, "Tablet", 299.99));


	@GetMapping("/products")

	public List<Product> getAllProducts() {
		//System.out.println("username is "+username);@RequestHeader("loggedInUser") String username
		return productList;
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable("id") Long id) { 
		return productList.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
	}
}
