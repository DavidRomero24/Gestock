package com.empresa.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StaffStatusDTO {

    @NotBlank(message = "El nuevo estado es obligatorio")
    private String status;

    private String reason;
}