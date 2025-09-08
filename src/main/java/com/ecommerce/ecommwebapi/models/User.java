package com.ecommerce.ecommwebapi.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String emailId;
    private String address;

}
