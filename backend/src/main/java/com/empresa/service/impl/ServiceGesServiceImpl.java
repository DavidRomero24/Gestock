package com.empresa.service.impl;

import com.empresa.dto.request.ServiceGesRequestDTO;
import com.empresa.dto.response.ServiceGesResponseDTO;
import com.empresa.dto.exception.DuplicateResourceException;
import com.empresa.dto.exception.IllegalOperationException;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.mapper.ServiceGesMapper;
import com.empresa.model.ServiceGes;
import com.empresa.model.ServiceType;
import com.empresa.repository.ServiceGesRepository;
import com.empresa.repository.ServiceTypeRepository;
import com.empresa.service.ServiceGesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceGesServiceImpl implements ServiceGesService {

    private final ServiceGesRepository serviceRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceGesMapper serviceMapper;

    @Autowired
    public ServiceGesServiceImpl(ServiceGesRepository serviceRepository,
                                  ServiceTypeRepository serviceTypeRepository,
                                  ServiceGesMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    @Transactional
    public ServiceGesResponseDTO createService(ServiceGesRequestDTO serviceDTO) {
        if (serviceRepository.existsById(serviceDTO.getIdService())) {
            throw new DuplicateResourceException("Ya existe un servicio con ID: " + serviceDTO.getIdService());
        }

        ServiceType serviceType = serviceTypeRepository.findById(serviceDTO.getIdSerType())
            .orElseThrow(() -> new ResourceNotFoundException("Tipo de servicio no encontrado con ID: " + serviceDTO.getIdSerType()));

        ServiceGes service = serviceMapper.toEntity(serviceDTO);
        service.setServiceType(serviceType);
        ServiceGes savedService = serviceRepository.save(service);
        return serviceMapper.toDto(savedService);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceGesResponseDTO getServiceById(String id) {
        ServiceGes service = serviceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + id));
        return serviceMapper.toDto(service);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceGesResponseDTO> getServicesByType(String serviceTypeId) {
        if (!serviceTypeRepository.existsById(serviceTypeId)) {
            throw new ResourceNotFoundException("Tipo de servicio no encontrado con ID: " + serviceTypeId);
        }

        return serviceRepository.findByServiceType_IdSerType(serviceTypeId).stream()
            .map(serviceMapper::toDto)
            .collect(Collectors.toList());
    }

    // @Override
    // @Transactional(readOnly = true)
    // public List<ServiceGesResponseDTO> getServicesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
    //     if (minPrice == null || maxPrice == null) {
    //         throw new IllegalArgumentException("Los precios no pueden ser nulos");
    //     }
    //     if (minPrice.compareTo(maxPrice) > 0) {
    //         throw new IllegalArgumentException("El precio mínimo no puede ser mayor que el precio máximo");
    //     }
    //     if (minPrice.compareTo(BigDecimal.ZERO) < 0) {
    //         throw new IllegalArgumentException("El precio mínimo no puede ser negativo");
    //     }

    //     return serviceRepository.findByPriceRange(minPrice, maxPrice).stream()
    //         .map(serviceMapper::toDto)
    //         .collect(Collectors.toList());
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public List<ServiceGesResponseDTO> searchServices(String keyword) {
    //     if (keyword == null || keyword.trim().isEmpty()) {
    //         throw new IllegalArgumentException("La palabra clave de búsqueda no puede estar vacía");
    //     }

    //     return serviceRepository.findByDescriptionContainingIgnoreCase(keyword).stream()
    //         .map(serviceMapper::toDto)
    //         .collect(Collectors.toList());
    // }

    @Override
    @Transactional
    public void deleteService(String id) {
        if (!serviceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Servicio no encontrado con ID: " + id);
        }

        if (isServiceUsedInBillDetails(id)) {
            throw new IllegalOperationException("No se puede eliminar el servicio porque está asociado a facturas");
        }

        serviceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceGesResponseDTO> getAllServices() {
        return serviceRepository.findAll().stream()
            .map(serviceMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ServiceGesResponseDTO updateService(String id, ServiceGesRequestDTO serviceDTO) {
        ServiceGes existingService = serviceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + id));

        ServiceType serviceType = serviceTypeRepository.findById(serviceDTO.getIdSerType())
            .orElseThrow(() -> new ResourceNotFoundException("Tipo de servicio no encontrado con ID: " + serviceDTO.getIdSerType()));

        existingService.setDescription(serviceDTO.getDescription());
        existingService.setPrice(serviceDTO.getPrice());
        existingService.setServiceType(serviceType);

        ServiceGes updatedService = serviceRepository.save(existingService);
        return serviceMapper.toDto(updatedService);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByServiceTypeId(String serviceTypeId) {
        return serviceRepository.existsByServiceType_IdSerType(serviceTypeId);
    }
    

    private boolean isServiceUsedInBillDetails(String serviceId) {
        return false; // Lógica pendiente de implementar
    }
}



