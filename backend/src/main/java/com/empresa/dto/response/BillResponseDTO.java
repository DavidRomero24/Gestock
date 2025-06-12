package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class BillResponseDTO {
    private String id;
    private LocalDate date;
    private String customerId;
    private String customerName;
    private String staffId;
    private String staffName;
    private BigDecimal total;
    private BigDecimal paidAmount;
    private BigDecimal pendingAmount;
    private String status;
    private List<BillDetailResponseDTO> billDetails;
    private List<ServiceDetailResponseDTO> serviceDetails;
    private List<PaymentResponseDTO> payments;
}