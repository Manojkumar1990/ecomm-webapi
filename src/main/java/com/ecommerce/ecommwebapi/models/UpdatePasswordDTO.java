package com.ecommerce.ecommwebapi.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UpdatePasswordDTO {
    private String emailId;
    private String oldPassword;
    private String newPassword;
}
