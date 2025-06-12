package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StaffRequestDTO {

    @NotBlank(message = "El ID del empleado es obligatorio")
    @Size(min = 4, max = 10, message = "El ID debe tener entre 4 y 10 caracteres")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "El ID solo debe contener letras mayúsculas y números")
    private String idStaff;

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(max = 15, message = "El primer nombre no puede exceder 15 caracteres")
    private String name1;

    @Size(max = 15, message = "El segundo nombre no puede exceder 15 caracteres")
    private String name2;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(max = 15, message = "El primer apellido no puede exceder 15 caracteres")
    private String lastName;

    @Size(max = 15, message = "El segundo apellido no puede exceder 15 caracteres")
    private String lastName2;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateBirth;

    @Email(message = "El email debe ser válido")
    @Size(max = 30, message = "El email no puede exceder 30 caracteres")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
    private String numberPhone;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser positivo")
    @Digits(integer = 10, fraction = 2, message = "El salario debe tener máximo 2 decimales")
    private Double salary;

    @NotBlank(message = "El tipo de empleado es obligatorio")
    private String typeStaff;

    @NotNull(message = "La fecha de contratacion es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    // @AssertTrue(message = "Debe aceptar los términos y condiciones")
    // private boolean acceptedTerms;
}