package com.empresa.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class BillSupplyRequestDTO {
    @NotNull(message = "El proveedor es obligatorio")
    private String supplierId;
    
    @NotNull(message = "Los detalles son obligatorios")
    private List<BillSupplyDetailRequestDTO> details;

    // Getters y Setters
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public List<BillSupplyDetailRequestDTO> getDetails() {
        return details;
    }

    public void setDetails(List<BillSupplyDetailRequestDTO> details) {
        this.details = details;
    }

}