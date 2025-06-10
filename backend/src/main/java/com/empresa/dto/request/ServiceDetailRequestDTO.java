package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServiceDetailRequestDTO {
    @NotBlank(message = "El ID del servicio es obligatorio")
    private String serviceId;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "El precio del servicio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal priceService;
}