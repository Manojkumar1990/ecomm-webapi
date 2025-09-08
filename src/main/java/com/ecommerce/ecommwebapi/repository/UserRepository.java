package com.ecommerce.ecommwebapi.repository;

import com.ecommerce.ecommwebapi.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserDAO,Long> {
    UserDAO findByEmailId(String emailId);
}
