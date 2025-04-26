package com.example.graphqldemo.dao;

import com.example.graphqldemo.models.Product;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Repository
public class ProductDaoImpl implements  IProductDao {

    private final DatabaseClient databaseClient;


    public ProductDaoImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Flux<Product> getProductList(){

        /*this.databaseClient.sql("select count(*) as total_count from productinfovc")
                .fetch()
                .first()
                .subscribe(
                        row -> System.out.println("✅ Updated rows: " + row.get("total_count")),
                        error -> System.err.println("❌ DB error: " + error),
                        () -> System.out.println("🎉 Query complete")
                );*/
   return this.databaseClient.sql("select * from productinfovc")
           .map((row, metadata) -> Product.builder().product_id(row.get("product_id",String.class))
                   .product_group(row.get("product_group",String.class))
                   .title(row.get("title", String.class)).
                   details(row.get("details", String.class))
                   .price(row.get("price", Integer.class))
                   .create_id(row.get("create_id", String.class))
                   .create_date(row.get("create_date", LocalDateTime.class))
                   .update_id(row.get("update_id", String.class))
                   .update_date(row.get("update_date", LocalDateTime.class)).build())
           .all();
    }

    @Override
    public Mono<String> createProduct(Product product) {
        return this.databaseClient.sql("insert into productinfovc (product_id, title, details, product_group, price, create_id, create_date, update_id, update_date) " +
                        "values (:product_id, :title, :details, :product_group, :price, :create_id, :create_date, :update_id, :update_date)")
                .bind("product_id", product.getProduct_id())
                .bind("title", product.getTitle())
                .bind("details", product.getDetails())
                .bind("product_group", product.getProduct_group())
                .bind("price", product.getPrice())
                .bind("create_id", product.getCreate_id())
                .bind("create_date", LocalDateTime.now())
                .bind("update_id", product.getUpdate_id())
                .bind("update_date", LocalDateTime.now())
                .fetch()
                .rowsUpdated()
                .map(rows -> rows > 0 ? "Product created successfully" : "Failed to create product")
                .onErrorReturn("Error occurred while creating the product");

    }
}
