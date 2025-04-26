package com.example.graphqldemo.models.request;

import lombok.Data;

@Data
public class ProductRequest {
    private  String title;
    private  String details;
    private  String product_group;
    private  int price;
    private  String create_id;
    private  String update_id;
}
