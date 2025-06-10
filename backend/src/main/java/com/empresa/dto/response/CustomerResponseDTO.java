package com.empresa.dto.response;

import lombok.Data;

@Data
public class CustomerResponseDTO {
    private String id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
}