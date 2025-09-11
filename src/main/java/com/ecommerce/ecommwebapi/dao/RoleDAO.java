package com.ecommerce.ecommwebapi.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class RoleDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
