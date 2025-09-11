package com.ecommerce.ecommwebapi.service;

import com.ecommerce.ecommwebapi.dao.*;
import com.ecommerce.ecommwebapi.models.*;
import com.ecommerce.ecommwebapi.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ECommerceCommonResponse createOrder(OrderRequestDTO orderRequestDTO) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        try {
            var user = userRepository.findByEmailId(orderRequestDTO.getUserId());
            if (user != null) {
                OrderDAO order = new OrderDAO();
                order.setUser(user);
                orderRepository.save(order);
                double totalAmount = 0.0;
                ArrayList<OrderItemDAO> orderItems = new ArrayList<>();
                for (OrderItemRequestDTO orderItemRequestDTO : orderRequestDTO.getOrderItems()) {
                    var product = productRepository.findById(orderItemRequestDTO.getProductId()).orElse(null);
                    if (product != null) {
                        if (product.getStock() >= orderItemRequestDTO.getQuantity()) {
                            product.setStock(product.getStock() - orderItemRequestDTO.getQuantity());
                            productRepository.save(product);
                            OrderItemDAO orderItem = new OrderItemDAO();
                            orderItem.setOrder(order);
                            orderItem.setProduct(product);
                            orderItem.setQuantity(orderItemRequestDTO.getQuantity());
                            orderItem.setPrice(product.getPrice());
                            orderItemRepository.save(orderItem);
                            orderItems.add(orderItem);
                            totalAmount+= product.getPrice() * orderItemRequestDTO.getQuantity();
                        }else{
                            throw new RuntimeException("Product with id "+ orderItemRequestDTO.getProductId()+" is out of stock");
                        }
                        order.setTotalAmount(totalAmount);
                        order.getOrderItems().clear();
                        order.getOrderItems().addAll(orderItems);
                        orderRepository.save(order);
                    }else{
                        throw new RuntimeException("Product with id "+ orderItemRequestDTO.getProductId()+" does not exist");
                    }
                }
            }
            else {
                throw new RuntimeException("User does not exist");
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            response.setReturnCode(1);
            response.setErrorMessage(e.getMessage());
            return response;
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            response.setReturnCode(1);
            response.setErrorMessage("Failed to create order");
            return response;
        }
        response.setReturnCode(0);
        response.setErrorMessage("Success");
        return  response;
    }

    public List<OrderSummaryDTO> getAllOrders(String userId) {
        if(userId != null && !userId.isEmpty()){
            var user = userRepository.findByEmailId(userId);
            if(user != null){
                var orders = orderRepository.findByUser(user);
                return orders.stream().map(orderDAO -> modelMapper.map(orderDAO, OrderSummaryDTO.class)).toList();
            }
        }else{
            var orders = orderRepository.findAll();
            return orders.stream().map(orderDAO -> modelMapper.map(orderDAO, OrderSummaryDTO.class)).toList();
        }
        return null;
    }
}
