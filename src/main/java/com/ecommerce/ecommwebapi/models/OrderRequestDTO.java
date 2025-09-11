package com.ecommerce.ecommwebapi.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {
    private String userId;
    private String address;
    private List<OrderItemRequestDTO> orderItems;

}
