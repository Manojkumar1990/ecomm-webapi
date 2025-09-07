package com.ecommerce.dao;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.NonNull;

import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Nonnull
    private String first_name;

    @Nonnull
    private String last_name;

    @Nonnull
    private String email_id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createTime;

}
