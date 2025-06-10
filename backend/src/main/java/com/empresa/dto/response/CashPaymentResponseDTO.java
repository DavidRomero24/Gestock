package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CashPaymentResponseDTO {
    private String id;
    private BigDecimal amount;
}
