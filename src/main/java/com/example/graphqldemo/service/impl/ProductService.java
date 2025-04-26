package com.example.graphqldemo.service.impl;

import com.example.graphqldemo.dao.IProductDao;
import com.example.graphqldemo.models.Product;
import com.example.graphqldemo.models.request.ProductRequest;
import com.example.graphqldemo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductDao productDao;

    public Flux<Product> fetchProductList(){
        return this.productDao.getProductList();
    }

    public Mono<String> createProductFromRequest(ProductRequest productRequest) {

        // Generate a unique ID for the product
        String productId = generateId();
        return productDao.createProduct(Product.builder()
                .product_id(productId)
                .title(productRequest.getTitle())
                .details(productRequest.getDetails())
                .product_group(productRequest.getProduct_group())
                .price(productRequest.getPrice())
                .create_id("admin")
                .update_id("admin")
                .build());
    }

    private String  generateId() {
        // Generate a unique ID for the product
        return UUID.randomUUID().toString().substring(0,8);
    }
}
