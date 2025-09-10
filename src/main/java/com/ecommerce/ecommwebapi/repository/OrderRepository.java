package com.ecommerce.ecommwebapi.repository;

import com.ecommerce.ecommwebapi.dao.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDAO, Long> {
}
