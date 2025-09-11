package com.ecommerce.ecommwebapi.controller;

import com.ecommerce.ecommwebapi.models.ECommerceCommonResponse;
import com.ecommerce.ecommwebapi.models.OrderRequestDTO;
import com.ecommerce.ecommwebapi.models.OrderSummaryDTO;
import com.ecommerce.ecommwebapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("order")
    public ECommerceCommonResponse createOrder(@RequestBody OrderRequestDTO orderRequest){
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("orders")
    public List<OrderSummaryDTO> getAllOrders(@RequestParam(name ="userId",required = false)String userId) {
        return orderService.getAllOrders(userId);
    }
}
