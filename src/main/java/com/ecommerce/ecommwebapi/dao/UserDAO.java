package com.ecommerce.ecommwebapi.dao;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Nonnull
    @Column(name = "first_name")
    private String firstName;

    @Nonnull
    @Column(name = "last_name")
    private String lastName;

    @Nonnull
    @Column(name = "email_id", unique = true)
    private String emailId;

    @Nonnull
    @Column(name="address")
    private String address;

    @Nonnull
    @Column(name="password")
    private String password;

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Date createTime;

}
