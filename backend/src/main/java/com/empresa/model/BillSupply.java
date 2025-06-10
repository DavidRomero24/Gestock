package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BILL_SUPPLY", schema = "GESTOCK")
public class BillSupply implements Serializable {

    @Id
    @Column(name = "ID_Bill_Supply", nullable = false)
    private Integer idBillSupply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Supplier", referencedColumnName = "ID_Supplier", nullable = false)
    private Supplier supplier;

    @Column(name = "Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "Total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    // Constructor
    public BillSupply() {
    }

    // Getters y Setters
    public Integer getIdBillSupply() {
        return idBillSupply;
    }

    public void setIdBillSupply(Integer idBillSupply) {
        this.idBillSupply = idBillSupply;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    @Override
    public String toString() {
        return "BillSupply{" +
                "idBillSupply=" + idBillSupply +
                ", supplier=" + (supplier != null ? supplier.getId() : "null") +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
