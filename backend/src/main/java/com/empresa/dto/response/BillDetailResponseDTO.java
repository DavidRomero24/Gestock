package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BillDetailResponseDTO {
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotal;
}