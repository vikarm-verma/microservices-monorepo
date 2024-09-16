
package com.example.userservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userservice.client.ProductClient;
import com.example.userservice.model.Product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private ProductClient productServiceClient;

    // Circuit Breaker and Retry for getting a single product
    @CircuitBreaker(name = "productservice", fallbackMethod = "fallbackGetProductById")
    @Retry(name = "productservice")
    @TimeLimiter(name = "productservice")
    public CompletableFuture<Product> getProductById(Long productId) {
    	
    	System.out.println("in userservice "+productId);
    
    	System.out.println("in userservice "+productId+"\t"+ productServiceClient.getProductById(productId));
     
    	return CompletableFuture.supplyAsync(() -> productServiceClient.getProductById(productId));
    }

    // Circuit Breaker and Retry for getting all products
    @CircuitBreaker(name = "productservice", fallbackMethod = "fallbackGetAllProducts")
    @Retry(name = "productservice")
    @TimeLimiter(name = "productservice")
    public CompletableFuture<List<Product>> getAllProducts() {
        return CompletableFuture.supplyAsync(productServiceClient::getAllProducts);
    }

    // Fallback for getProductById
	public CompletableFuture<Product> fallbackGetProductById(Long productId, Throwable throwable) {
             // Return a default product as a fallback
        Product defaultProduct = new Product(productId, "Default Product", 0.0);
        return CompletableFuture.completedFuture(defaultProduct);
    }

    // Fallback for getAllProducts
    public CompletableFuture<List<Product>> fallbackGetAllProducts(Throwable throwable) {
    	
        // Return an empty list as a fallback
        return CompletableFuture.completedFuture(List.of());
    }
}
