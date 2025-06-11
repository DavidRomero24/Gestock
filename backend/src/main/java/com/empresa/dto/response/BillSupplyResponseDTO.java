package com.empresa.dto.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BillSupplyResponseDTO {
    private Integer idBillSupply;
    private String supplierId;
    private String supplierName;
    private String supplierCompany;
    private Date date;
    private BigDecimal total;
    private List<BillSupplyDetailResponseDTO> details;

    // Getters y Setters
    public Integer getIdBillSupply() {
        return idBillSupply;
    }

    public void setIdBillSupply(Integer idBillSupply) {
        this.idBillSupply = idBillSupply;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCompany() {
        return supplierCompany;
    }

    public void setSupplierCompany(String supplierCompany) {
        this.supplierCompany = supplierCompany;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<BillSupplyDetailResponseDTO> getDetails() {
        return details;
    }

    public void setDetails(List<BillSupplyDetailResponseDTO> details) {
        this.details = details;
    }

}