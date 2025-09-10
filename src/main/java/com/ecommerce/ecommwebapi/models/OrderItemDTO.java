package com.ecommerce.ecommwebapi.models;

import lombok.*;

@Getter
@Setter
public class OrderItemDTO {
    private Long productId;
    private Integer quantity;
}
