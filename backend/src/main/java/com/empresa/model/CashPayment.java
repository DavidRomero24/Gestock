package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "CASH_PAYMENTS", schema = "GESTOCK")
public class CashPayment implements Serializable {

    @Id
    @Column(name = "ID_Cash_Payment", length = 15)
    private String id;

    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;

    // Constructor vacío obligatorio para JPA
    public CashPayment() {}

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
