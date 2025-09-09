package com.ecommerce.ecommwebapi.repository;

import com.ecommerce.ecommwebapi.dao.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository  extends JpaRepository<ProductDAO,Long> {
    public List<ProductDAO> findByNameOrDescriptionContainingIgnoreCase(String name, String description);
}
