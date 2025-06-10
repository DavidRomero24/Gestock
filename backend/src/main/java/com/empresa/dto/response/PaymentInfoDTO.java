package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO que representa información resumida de un pago asociado a una transferencia
 */
@Data
public class PaymentInfoDTO {
    private Integer paymentId;
    private LocalDateTime paymentDate;
    private BigDecimal amount;
}