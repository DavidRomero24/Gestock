package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerRequestDTO {
    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(max = 15, message = "El primer nombre no puede exceder 15 caracteres")
    private String name1;

    @Size(max = 15, message = "El segundo nombre no puede exceder 15 caracteres")
    private String name2;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(max = 15, message = "El primer apellido no puede exceder 15 caracteres")
    private String lastName1;

    @Size(max = 15, message = "El segundo apellido no puede exceder 15 caracteres")
    private String lastName2;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 10, message = "El teléfono debe tener 10 dígitos")
    @Pattern(regexp = "[0-9]+", message = "El teléfono solo debe contener números")
    private String phone;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 50, message = "El email no puede exceder 50 caracteres")
    private String email;

    @Size(max = 70, message = "La dirección no puede exceder 70 caracteres")
    private String address;
}