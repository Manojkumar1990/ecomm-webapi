package com.ecommerce.ecomm_api.repository;

import com.ecommerce.ecomm_api.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
}
