package com.example.graphqldemo.service;

import com.example.graphqldemo.models.Product;
import com.example.graphqldemo.models.request.ProductRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    Flux<Product> fetchProductList();
    Mono<String> createProductFromRequest(ProductRequest productRequest);

}
