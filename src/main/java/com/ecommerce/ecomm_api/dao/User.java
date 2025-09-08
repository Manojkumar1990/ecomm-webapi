package com.ecommerce.ecomm_api.dao;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
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

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Date createTime;

}
