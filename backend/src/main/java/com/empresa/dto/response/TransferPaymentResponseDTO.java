package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;;

@Data
public class TransferPaymentResponseDTO {
    private String id;
    private String originBank;
    private String accountHolderName;
    private String accountNumber;
    private String transferCode;
    private BigDecimal amount;
    private List<PaymentInfoDTO> relatedPayments;
}
