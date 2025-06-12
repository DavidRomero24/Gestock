package com.empresa.dto.response;

import lombok.Data;

@Data
public class CustomerResponseDTO {
    private String id;
    private String name1;
    private String name2;
    private String lastName1;
    private String lastName2;
    private String phone;
    private String email;
    private String address;
    private String fullName;  // concatenación opcional
}