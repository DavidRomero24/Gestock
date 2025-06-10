package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
@Table(name = "BILL_DETAIL", schema = "GESTOCK")
public class BillDetail implements Serializable {

    @EmbeddedId
    private BillDetailId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "ID_Product", referencedColumnName = "ID_Product", nullable = false)
    private Product product;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "ID_Bill", referencedColumnName = "ID_Bill", nullable = false)
    private Bill bill;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Sub_Total", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    // Constructor
    public BillDetail() {
    }

    // Constructor completo
    public BillDetail(Product product, Bill bill, Integer quantity, BigDecimal subTotal) {
        this.product = product;
        this.bill = bill;
        this.quantity = quantity;
        this.subTotal = subTotal;
        // Crear el ID compuesto automáticamente
        if (product != null && bill != null) {
            this.id = new BillDetailId(product.getIdProduct(), bill.getId());
        }
    }

    // Getters y Setters
    public BillDetailId getId() {
        return id;
    }

    public void setId(BillDetailId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (product != null && this.bill != null) {
            this.id = new BillDetailId(product.getIdProduct(), this.bill.getId());
        }
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
        if (bill != null && this.product != null) {
            this.id = new BillDetailId(this.product.getIdProduct(), bill.getId());
        }
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    // MÉTODOS ADICIONALES ÚTILES

    /**
     * Calcula el precio unitario basado en el subtotal y la cantidad
     */
    public BigDecimal getUnitPrice() {
        if (quantity == null || quantity == 0 || subTotal == null) {
            return BigDecimal.ZERO;
        }
        return subTotal.divide(BigDecimal.valueOf(quantity), 2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula el subtotal basado en precio unitario y cantidad
     */
    public void calculateSubTotal(BigDecimal unitPrice) {
        if (unitPrice != null && quantity != null) {
            this.subTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    /**
     * Método de conveniencia para obtener el ID del producto
     */
    public String getProductId() {
        return product != null ? product.getIdProduct() : null;
    }

    /**
     * Método de conveniencia para obtener el ID de la factura
     */
    public Integer getBillId() {
        return bill != null ? bill.getId() : null;
    }

    // equals y hashCode usando el ID compuesto
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillDetail that = (BillDetail) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BillDetail{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }

    // Clase interna para la clave primaria compuesta
    @Embeddable
    public static class BillDetailId implements Serializable {
        @Column(name = "ID_Product")
        private String productId;

        @Column(name = "ID_Bill")
        private Integer billId;

        public BillDetailId() {
        }

        public BillDetailId(String productId, Integer billId) {
            this.productId = productId;
            this.billId = billId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Integer getBillId() {
            return billId;
        }

        public void setBillId(Integer billId) {
            this.billId = billId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BillDetailId that = (BillDetailId) o;
            return Objects.equals(productId, that.productId) &&
                    Objects.equals(billId, that.billId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(productId, billId);
        }

        @Override
        public String toString() {
            return "BillDetailId{" +
                    "productId='" + productId + '\'' +
                    ", billId=" + billId +
                    '}';
        }
    }
}
