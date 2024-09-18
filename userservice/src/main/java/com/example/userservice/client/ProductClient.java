package com.example.userservice.client;

import com.example.userservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// url = "http://localhost:8081/products" - because productservice will run over 2 ports
//fallback = ProductServiceFallback.class

@FeignClient(name = "productservice",fallback = ProductServiceFallback.class)
public interface ProductClient { 

    @GetMapping("/products")
  //  @GetMapping
    List<Product> getAllProducts();
 
    @GetMapping("/products/{id}")
    //@GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
