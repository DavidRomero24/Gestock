package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SERVICE_TYPE", schema = "GESTOCK")
public class ServiceType implements Serializable {

    @Id
    @Column(name = "ID_SerType", length = 15, nullable = false)
    private String idSerType;

    @Column(name = "Service_Category", length = 50, nullable = false)
    private String serviceCategory;

    // Relación con Service (uno a muchos)
    @OneToMany(mappedBy = "serviceType", fetch = FetchType.LAZY)
    private List<ServiceGes> services;

    // Constructor
    public ServiceType() {
    }

    // Getters y Setters
    public String getIdSerType() {
        return idSerType;
    }

    public void setIdSerType(String idSerType) {
        this.idSerType = idSerType;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public List<ServiceGes> getServices() {
        return services;
    }

    public void setServices(List<ServiceGes> services) {
        this.services = services;
    }
}