package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "TRANSFER_PAYMENT", schema = "GESTOCK")
public class TransferPayment implements Serializable {

    @Id
    @Column(name = "ID_Transfer", length = 15)
    private String id;

    @Column(name = "Origin_bank", length = 30, nullable = false)
    private String originBank;

    @Column(name = "Account_Holder_Name", length = 50, nullable = false)
    private String accountHolderName;

    @Column(name = "Account_Number", length = 20, nullable = false)
    private String accountNumber;

    @Column(name = "Transfer_Code", length = 20, nullable = false)
    private String transferCode;

    @Column(name = "Amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    // Relación inversa con Payment
    @OneToMany(mappedBy = "transferPayment")
    private List<Payment> payments;

    // Constructor vacío requerido por JPA
    public TransferPayment() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getOriginBank() { return originBank; }
    public void setOriginBank(String originBank) { this.originBank = originBank; }

    public String getAccountHolderName() { return accountHolderName; }
    public void setAccountHolderName(String accountHolderName) { 
        this.accountHolderName = accountHolderName; 
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getTransferCode() { return transferCode; }
    public void setTransferCode(String transferCode) { this.transferCode = transferCode; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public List<Payment> getPayments() { return payments; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }
}