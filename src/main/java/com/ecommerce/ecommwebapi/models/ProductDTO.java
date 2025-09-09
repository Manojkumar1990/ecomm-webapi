package com.ecommerce.ecommwebapi.models;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private  String name;
    private String description;
    private int stock;
    private double price;
    private Date lastUpdatedTime;

}
