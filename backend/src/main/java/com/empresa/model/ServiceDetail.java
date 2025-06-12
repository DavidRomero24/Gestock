package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "SERVICE_DETAIL", schema = "GESTOCK")
public class ServiceDetail implements Serializable {

    @EmbeddedId
    private ServiceDetailId id;

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "ID_Service", referencedColumnName = "ID_Service", nullable = false)
    private Service service;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "ID_Bill", nullable = false)
    private Bill bill;

    @Column(name = "Description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "Price_service", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceService;

    // Constructor
    public ServiceDetail() {
    }

    // Getters y Setters
    public ServiceDetailId getId() {
        return id;
    }

    public void setId(ServiceDetailId id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceService() {
        return priceService;
    }

    public void setPriceService(BigDecimal priceService) {
        this.priceService = priceService;
    }

    // Clase para la clave primaria compuesta
    @Embeddable
    public static class ServiceDetailId implements Serializable {
        
        @Column(name = "ID_Service", length = 15)
        private String serviceId;

        @Column(name = "ID_Bill")
        private Integer billId;

        // Constructores
        public ServiceDetailId() {
        }

        public ServiceDetailId(String serviceId, Integer billId) {
            this.serviceId = serviceId;
            this.billId = billId;
        }

        // Getters y Setters
        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public Integer getBillId() {
            return billId;
        }

        public void setBillId(Integer billId) {
            this.billId = billId;
        }

        // equals y hashCode
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ServiceDetailId that = (ServiceDetailId) o;
            return serviceId.equals(that.serviceId) && billId.equals(that.billId);
        }

        @Override
        public int hashCode() {
            int result = serviceId.hashCode();
            result = 31 * result + billId.hashCode();
            return result;
        }
    }
}