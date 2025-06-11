package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "BILL_SUPPLY_DETAIL", schema = "GESTOCK")
public class BillSupplyDetail implements Serializable {

    @EmbeddedId
    private BillSupplyDetailId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "ID_Product", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("billSupplyId")
    @JoinColumn(name = "ID_Bill_Supply", nullable = false)
    private BillSupply billSupply;

    @Column(name = "Quantity_Product", nullable = false)
    private Integer quantityProduct;

    @Column(name = "Unit_Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "Sub_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    // Constructor
    public BillSupplyDetail() {
    }

    // Getters y Setters
    public BillSupplyDetailId getId() {
        return id;
    }

    public void setId(BillSupplyDetailId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BillSupply getBillSupply() {
        return billSupply;
    }

    public void setBillSupply(BillSupply billSupply) {
        this.billSupply = billSupply;
    }

    public Integer getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    // Clase para la clave primaria compuesta
    @Embeddable
    public static class BillSupplyDetailId implements Serializable {

        @Column(name = "ID_Bill_Supply")
        private Integer billSupplyId;

        @Column(name = "ID_Product", length = 15)
        private String productId;

        // Constructores
        public BillSupplyDetailId() {
        }

        // 👇 Constructor corregido (Integer primero, luego String)
        public BillSupplyDetailId(Integer billSupplyId, String productId) {
            this.billSupplyId = billSupplyId;
            this.productId = productId;
        }

        // Getters y Setters
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Integer getBillSupplyId() {
            return billSupplyId;
        }

        public void setBillSupplyId(Integer billSupplyId) {
            this.billSupplyId = billSupplyId;
        }

        // equals y hashCode
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BillSupplyDetailId)) return false;
            BillSupplyDetailId that = (BillSupplyDetailId) o;
            return Objects.equals(billSupplyId, that.billSupplyId) &&
                   Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(billSupplyId, productId);
        }
    }
}
