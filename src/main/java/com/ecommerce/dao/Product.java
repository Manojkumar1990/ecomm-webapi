package com.ecommerce.dao;

import jakarta.persistence.*;
import jakarta.annotation.Nonnull;

import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    @Nonnull
    private  String name;
    @Nonnull
    private String description;

    private int stock;

    private double price;

    private Date last_updt_time;
    
}
