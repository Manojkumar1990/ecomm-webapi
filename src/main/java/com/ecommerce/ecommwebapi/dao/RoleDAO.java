package com.ecommerce.ecommwebapi.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="roles")
public class RoleDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;
    @Column(nullable = false, unique = true,name="role_name")
    private String roleName;
}
