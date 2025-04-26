package com.example.graphqldemo.controller;

import com.example.graphqldemo.models.Product;
import com.example.graphqldemo.models.request.ProductRequest;
import com.example.graphqldemo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("list")
    public Flux<Product> getProductList() {
          return this.productService.fetchProductList();
    }

    @PostMapping("details/{product_id}")
    public String createUpdate(@RequestBody String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("createProduct")
    public Mono<String> createProduct(@RequestBody ProductRequest productRequest) {
        return this.productService.createProductFromRequest(productRequest)
                .map(result -> String.format("Product created with ID: %s", result))
                .onErrorResume(error -> Mono.just("Failed to create product: " + error.getMessage()));
    }
}
