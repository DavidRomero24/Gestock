package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ServiceGesRequestDTO {
    
    @NotBlank(message = "El ID del servicio es obligatorio")
    @Size(min = 5, max = 15, message = "El ID debe tener entre 5 y 15 caracteres")
    private String idService;

    @NotBlank(message = "El ID del tipo de servicio es obligatorio")
    private String idSerType;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "Precio inválido. Máximo 8 enteros y 2 decimales")
    private BigDecimal price;

    // Getters y Setters
    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdSerType() {
        return idSerType;
    }

    public void setIdSerType(String idSerType) {
        this.idSerType = idSerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}