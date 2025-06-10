package com.empresa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class BillRequestDTO {
    @NotBlank(message = "El ID del cliente es obligatorio")
    private String customerId;

    @NotBlank(message = "El ID del empleado es obligatorio")
    private String staffId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El total debe tener máximo 10 enteros y 2 decimales")
    private BigDecimal total;

    @NotNull(message = "Los detalles de productos son obligatorios")
    @Size(min = 1, message = "Debe haber al menos un producto")
    private List<BillDetailRequestDTO> billDetails;

    @NotNull(message = "Los detalles de servicio son obligatorios")
    private List<ServiceDetailRequestDTO> serviceDetails;

    @NotNull(message = "Los pagos son obligatorios")
    @Size(min = 1, message = "Debe haber al menos un pago")
    private List<PaymentRequestDTO> payments;

    private String status; // PENDIENTE, PAGADA, CANCELADA
}