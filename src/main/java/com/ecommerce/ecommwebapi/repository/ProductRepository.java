package com.ecommerce.ecommwebapi.repository;

import com.ecommerce.ecommwebapi.dao.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<ProductDAO,Long> {
}
