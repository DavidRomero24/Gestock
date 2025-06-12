package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentResponseDTO {
    private Integer id;
    private LocalDate date;
    private BigDecimal amountPaid;
    private String paymentMethod;
    private Integer billId;
    private String billReference;
    private PaymentDetailDTO paymentDetails;
}
