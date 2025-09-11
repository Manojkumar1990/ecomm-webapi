package com.ecommerce.ecommwebapi.models;

import lombok.*;

@Getter
@Setter
public class OrderItemRequestDTO {
    private Long productId;
    private Integer quantity;
}
