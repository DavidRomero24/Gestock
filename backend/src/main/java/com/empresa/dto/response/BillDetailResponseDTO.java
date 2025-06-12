package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BillDetailResponseDTO {
    private String id;
    private String productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subTotal;
}