package com.ecommerce.ecommwebapi.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
public class OrderSummaryDTO {
    private Long orderId;
    private Date orderDate;
    private Date orderUpdatedDate;
    private String status;
    private Double totalAmount;
    private List<OrderItemDTO> orderItems = new ArrayList<>();

}
