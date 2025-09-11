package com.ecommerce.ecommwebapi.dao;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Date orderDate;

    @Column(name = "order_updt_time", nullable = false)
    private Date orderUpdatedDate;

    @Column(nullable = false)
    private String status;


    @Column(nullable = false)
    private Double totalAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDAO user;

    @OneToMany(mappedBy = "orderItemId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemDAO> orderItems = new ArrayList<>();
    @PrePersist
    public void onCreate() {
        this.orderDate = new Date();
        this.orderUpdatedDate = new Date();
        if (this.status == null) {
            this.status = "PENDING";
        }
        if(this.totalAmount == null) {
            this.totalAmount = 0.0;
        }
    }
    @PreUpdate
    public void OnUpdate() {
        this.orderUpdatedDate = new Date();
    }
}

