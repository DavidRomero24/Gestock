package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferPaymentRequestDTO {
    @NotBlank(message = "El banco de origen es obligatorio")
    @Size(max = 30, message = "El nombre del banco no puede exceder 30 caracteres")
    private String originBank;

    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(max = 50, message = "El nombre del titular no puede exceder 50 caracteres")
    private String accountHolderName;

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(max = 20, message = "El número de cuenta no puede exceder 20 caracteres")
    @Pattern(regexp = "[0-9]+", message = "El número de cuenta solo debe contener dígitos")
    private String accountNumber;

    @NotBlank(message = "El código de transferencia es obligatorio")
    @Size(max = 20, message = "El código de transferencia no puede exceder 20 caracteres")
    private String transferCode;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El monto debe tener máximo 10 enteros y 2 decimales")
    private BigDecimal amount;
}