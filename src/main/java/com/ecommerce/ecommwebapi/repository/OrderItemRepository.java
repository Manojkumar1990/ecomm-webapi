package com.ecommerce.ecommwebapi.repository;

import com.ecommerce.ecommwebapi.dao.OrderItemDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemDAO,Long> {
}
