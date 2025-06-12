package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "PRODUCT", schema = "GESTOCK")
public class Product implements Serializable {

    @Id
    @Column(name = "ID_Product", length = 15, nullable = false)
    private String idProduct;

    @Column(name = "Name", length = 50, nullable = false)
    private String name;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name = "Category", length = 20, nullable = false)
    private String category;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    // @Version
    // @Column(name = "Version")
    // private Long version; // Para control de concurrencia

    // Relación con BILL_DETAIL (un producto puede estar en muchos detalles de factura)
    @OneToMany(mappedBy = "product")
    private List<BillDetail> billDetails;

    // Constructor
    public Product() {
    }

    // Getters y Setters
    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    // Métodos de negocio
    public void decreaseStock(Integer quantity) {
        if (this.stock < quantity) {
            throw new IllegalStateException("Stock insuficiente");
        }
        this.stock -= quantity;
    }

    public void increaseStock(Integer quantity) {
        this.stock += quantity;
    }

    // Método toString() opcional
    @Override
    public String toString() {
        return "Product{" +
                "idProduct='" + idProduct + '\'' +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}