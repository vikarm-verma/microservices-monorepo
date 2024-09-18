package com.example.userservice.controller;


import com.example.userservice.model.Product;
import com.example.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Endpoint to get all products
	@GetMapping("/products")
	public CompletableFuture<ResponseEntity<List<Product>>> getAllProducts() {
		return userService.getAllProducts().thenApply(ResponseEntity::ok);
	}

	// Endpoint to get a single product by ID
	@GetMapping("/products/{id}")
	public CompletableFuture<ResponseEntity<Product>> getProductById(@PathVariable Long id) {
		System.out.println("in usercontroller");
		return userService.getProductById(id).thenApply(ResponseEntity::ok);
	}

	// private final ProductClient productClient;

//    public UserController(ProductClient productClient) {
//        this.productClient = productClient;
//    }
//
//    @GetMapping("/products")
//    public List<Product> getAllProducts() {
//        return productClient.getAllProducts();
//    }

//    @GetMapping("/products/{id}")
//    public Product getProductById(@PathVariable("id") Long id) {
//        return productClient.getProductById(id);
//    }
}
