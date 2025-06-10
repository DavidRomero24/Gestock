package com.empresa.service;

import com.empresa.model.Service;
import com.empresa.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service saveService(Service service) {
        if (service == null) {
            throw new RuntimeException("El servicio no puede ser nulo");
        }
        return serviceRepository.save(service);
    }

    public Optional<Service> findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return Optional.empty();
        }
        return serviceRepository.findById(id);
    }

    public List<Service> findByServiceTypeId(String serviceTypeId) {
        if (serviceTypeId == null || serviceTypeId.trim().isEmpty()) {
            throw new RuntimeException("El ID de tipo de servicio no puede estar vacío");
        }
        return serviceRepository.findByServiceTypeId(serviceTypeId);
    }

    public List<Service> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new RuntimeException("Los precios no pueden ser nulos");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new RuntimeException("El precio mínimo no puede ser mayor al máximo");
        }
        return serviceRepository.findByPriceRange(minPrice, maxPrice);
    }

    public void deleteService(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new RuntimeException("El ID no puede estar vacío");
        }
        serviceRepository.deleteById(id);
    }

    public List<Service> findAll() {
        return serviceRepository.findAll();
    }
}