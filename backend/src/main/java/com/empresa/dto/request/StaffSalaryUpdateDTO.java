package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StaffSalaryUpdateDTO {

    @NotNull(message = "El nuevo salario es obligatorio")
    @Positive(message = "El salario debe ser positivo")
    @Digits(integer = 10, fraction = 2, message = "El salario debe tener máximo 2 decimales")
    private Double newSalary;

    @NotBlank(message = "La razón del ajuste es obligatoria")
    private String adjustmentReason;

    @NotNull(message = "La fecha efectiva es obligatoria")
    private String effectiveDate;
}