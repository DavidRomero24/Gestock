package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PAYMENTS", schema = "GESTOCK")
public class Payment implements Serializable {

    @Id
    @Column(name = "ID_Payment")
    private Integer id;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "Amount_paid", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @Column(name = "Payment_Method", length = 30, nullable = false)
    private String paymentMethod;

    // Relación con BILL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Bill", referencedColumnName = "ID_Bill", nullable = false)
    private Bill bill;

    // Relación con TRANSFER_PAYMENTS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Transfer", referencedColumnName = "ID_Transfer")
    private TransferPayment transferPayment;

    // Relación con CASH_PAYMENTS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Cash_Payment", referencedColumnName = "ID_Cash_Payment")
    private CashPayment cashPayment;

    // Constructor vacío requerido por JPA
    public Payment() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getAmountPaid() { return amountPaid; }
    public void setAmountPaid(BigDecimal amountPaid) { this.amountPaid = amountPaid; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Bill getBill() { return bill; }
    public void setBill(Bill bill) { this.bill = bill; }

    public TransferPayment getTransferPayment() { return transferPayment; }
    public void setTransferPayment(TransferPayment transferPayment) { this.transferPayment = transferPayment; }

    public CashPayment getCashPayment() { return cashPayment; }
    public void setCashPayment(CashPayment cashPayment) { this.cashPayment = cashPayment; }
}