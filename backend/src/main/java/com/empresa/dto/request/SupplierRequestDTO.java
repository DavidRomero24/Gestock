package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SupplierRequestDTO {

    @NotBlank(message = "El ID del proveedor es obligatorio")
    @Size(min = 5, max = 15, message = "El ID debe tener entre 5 y 15 caracteres")
    private String id;

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(max = 50, message = "El primer nombre no puede exceder 50 caracteres")
    private String name1;

    @Size(max = 50, message = "El segundo nombre no puede exceder 50 caracteres")
    private String name2;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(max = 15, message = "El primer apellido no puede exceder 15 caracteres")
    private String lastName1;

    @Size(max = 15, message = "El segundo apellido no puede exceder 15 caracteres")
    private String lastName2;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe tener 10 dígitos")
    private String phone;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Size(max = 50, message = "El email no puede exceder 50 caracteres")
    private String email;

    @NotBlank(message = "La compañía es obligatoria")
    @Size(max = 20, message = "La compañía no puede exceder 20 caracteres")
    private String company;

    @NotBlank(message = "El ID de la ciudad es obligatorio")
    private String cityId;

    @NotBlank(message = "El ID del departamento es obligatorio")
    private String departmentId;
}
