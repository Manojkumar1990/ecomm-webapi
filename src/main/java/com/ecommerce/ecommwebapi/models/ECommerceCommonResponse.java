package com.ecommerce.ecommwebapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ECommerceCommonResponse {
    private String returnCode;
    private String errorMessage;

}
