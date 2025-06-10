package com.empresa.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServiceDetailResponseDTO {
    private String serviceId;
    private String serviceName;
    private String description;
    private BigDecimal priceService;
}