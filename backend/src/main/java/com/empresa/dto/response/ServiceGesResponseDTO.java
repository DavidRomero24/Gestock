package com.empresa.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ServiceGesResponseDTO {
    private String idService;
    private String serviceTypeName;
    private String description;
    private BigDecimal price;
    private String serviceTypeId;

}