package com.ecommerce.ecomm_api.repository;

import com.ecommerce.ecomm_api.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {
}
