package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductResponseDTO {
    private String idProduct;
    private String name;
    private Integer stock;
    private String category;
    private BigDecimal price;
    private String description;
    private String stockStatus; // "EN_STOCK", "BAJO_STOCK", "SIN_STOCK"
}   