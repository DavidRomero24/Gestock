package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentRequestDTO {
    @NotBlank(message = "El ID de la factura es obligatorio")
    private Integer billId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El monto debe tener máximo 10 enteros y 2 decimales")
    private BigDecimal amountPaid;

    @NotBlank(message = "El método de pago es obligatorio")
    @Pattern(regexp = "EFECTIVO|TRANSFERENCIA", message = "Método de pago no válido")
    private String paymentMethod;

    // Campos específicos para transferencia
    private String originBank;
    private String accountHolderName;
    private String accountNumber;
    private String transferCode;

    // Campos específicos para efectivo
    private String cashReference;
}