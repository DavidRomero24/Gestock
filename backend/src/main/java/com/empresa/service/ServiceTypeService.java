package com.empresa.service;

import com.empresa.model.ServiceType;
import com.empresa.repository.ServiceTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceTypeService {
    
    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceGesService serviceService;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository, ServiceGesService serviceService) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.serviceService = serviceService;
    }

    public ServiceType saveServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    public ServiceType findById(String id) {
        return serviceTypeRepository.findById(id).orElse(null);
    }

    public List<ServiceType> findAll() {
        return serviceTypeRepository.findAll();
    }

    @Transactional
    public void deleteServiceType(String id) throws IllegalStateException {
        // Validar que no tenga servicios asociados
        if (!serviceService.getServicesByType(id).isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el tipo de servicio porque tiene servicios asociados");
        }
        serviceTypeRepository.deleteById(id);
    }


}