package com.ecommerce.ecommwebapi.dao;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @NotNull
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @NotNull
    @Column(name="address",nullable = false)
    private String address;

    //@NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="create_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createTime;

    @NotNull
    @Column(name="update_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleDAO role;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
        updateTime = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }

}
