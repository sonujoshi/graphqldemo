package com.example.graphqldemo.dao;

import com.example.graphqldemo.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductDao {
     Flux<Product> getProductList();
     Mono<String> createProduct(Product product);
}
