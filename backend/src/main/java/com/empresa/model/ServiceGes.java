package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "SERVICEGES", schema = "GESTOCK")
public class ServiceGes implements Serializable {

    @Id
    @Column(name = "ID_Service", length = 15, nullable = false)
    private String idService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SerType", nullable = false)
    private ServiceType serviceType;

    @Column(name = "Description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // Relación con ServiceDetail
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServiceDetail> serviceDetails;

    // Constructor
    public ServiceGes() {
    }

    // Getters y Setters
    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<ServiceDetail> getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(List<ServiceDetail> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }
}