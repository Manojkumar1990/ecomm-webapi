package com.ecommerce.ecommwebapi.models;
import lombok.*;
import jakarta.persistence.*;
@Data
@Setter
@Getter
public class OrderItemDTO {
    private Long orderItemId;
    private Integer quantity;
    private Double price;
    private String status;
    private ProductDTO product;
}
