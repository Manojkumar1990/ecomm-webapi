package com.ecommerce.ecommwebapi.models;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UserCreateDTO {
    private String firstName;
    private String lastName;
    private String emailId;
    private String address;
    private Date createTime;
    private Date updateTime;
    private String password;

}
