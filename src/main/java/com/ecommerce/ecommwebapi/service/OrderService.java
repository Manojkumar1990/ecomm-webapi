package com.ecommerce.ecommwebapi.service;

import com.ecommerce.ecommwebapi.dao.OrderDAO;
import com.ecommerce.ecommwebapi.dao.OrderItemDAO;
import com.ecommerce.ecommwebapi.models.ECommerceCommonResponse;
import com.ecommerce.ecommwebapi.models.OrderItemDTO;
import com.ecommerce.ecommwebapi.models.OrderRequestDTO;
import com.ecommerce.ecommwebapi.repository.OrderItemRepository;
import com.ecommerce.ecommwebapi.repository.OrderRepository;
import com.ecommerce.ecommwebapi.repository.ProductRepository;
import com.ecommerce.ecommwebapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public ECommerceCommonResponse createOrder(OrderRequestDTO orderRequestDTO) {
        ECommerceCommonResponse response = new ECommerceCommonResponse();
        try {
            var user = userRepository.findByEmailId(orderRequestDTO.getUserId());
            if (user != null) {
                OrderDAO order = new OrderDAO();
                order.setUser(user);
                double totalAmount = 0.0;
                ArrayList<OrderItemDAO> orderItems = new ArrayList<>();
                for (OrderItemDTO orderItemDTO : orderRequestDTO.getOrderItems()) {
                    var product = productRepository.findById(orderItemDTO.getProductId()).orElse(null);
                    if (product != null) {
                        if (product.getStock() >= orderItemDTO.getQuantity()) {
                            product.setStock(product.getStock() - orderItemDTO.getQuantity());
                            productRepository.save(product);
                            OrderItemDAO orderItem = new OrderItemDAO();
                            orderItem.setOrder(order);
                            orderItem.setProduct(product);
                            orderItem.setQuantity(orderItemDTO.getQuantity());
                            orderItem.setPrice(product.getPrice());
                            orderItemRepository.save(orderItem);
                            orderItems.add(orderItem);
                            totalAmount+= product.getPrice() * orderItemDTO.getQuantity();
                        }else{
                            throw new RuntimeException("Product with id "+orderItemDTO.getProductId()+" is out of stock");
                        }
                        order.setTotalAmount(totalAmount);
                        order.setOrderItems(orderItems);
                        orderRepository.save(order);
                    }else{
                        throw new RuntimeException("Product with id "+orderItemDTO.getProductId()+" does not exist");
                    }
                }

            }
            else {
                throw new RuntimeException("User does not exist");
            }
        } catch (RuntimeException e) {
            response.setReturnCode(1);
            response.setErrorMessage(e.getMessage());
            return response;
        }catch (Exception e) {
            response.setReturnCode(1);
            response.setErrorMessage("Failed to create order");
            return response;
        }
        response.setReturnCode(0);
        response.setErrorMessage("Success");
        return  response;
    }
}
