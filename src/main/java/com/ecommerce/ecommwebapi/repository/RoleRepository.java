package com.ecommerce.ecommwebapi.repository;

import com.ecommerce.ecommwebapi.dao.RoleDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleDAO,Long> {
    RoleDAO findByRoleNameIgnoreCase(String roleName);
}
