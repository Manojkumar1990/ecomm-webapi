package com.ecommerce.ecommwebapi.repository;

import com.ecommerce.ecommwebapi.dao.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<OrderDAO, Long> {
    public List<OrderDAO> findByUser(UserDAO userDAO);
}
