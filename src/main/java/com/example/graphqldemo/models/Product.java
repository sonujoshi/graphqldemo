package com.example.graphqldemo.models;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Product {
private String product_id;
private String title;
private String details;
private String product_group;
private int price;
    private String create_id;
    private LocalDateTime create_date;
    private String update_id;
    private LocalDateTime update_date;
}
