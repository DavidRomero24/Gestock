package com.empresa.service;

import com.empresa.dto.request.ServiceGesRequestDTO;
import com.empresa.dto.response.ServiceGesResponseDTO;
// import java.math.BigDecimal;
import java.util.List;

public interface ServiceGesService {
    ServiceGesResponseDTO createService(ServiceGesRequestDTO serviceDTO);
    ServiceGesResponseDTO getServiceById(String id);
    List<ServiceGesResponseDTO> getServicesByType(String serviceTypeId);
    // List<ServiceGesResponseDTO> getServicesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    // List<ServiceGesResponseDTO> searchServices(String keyword);
    void deleteService(String id);
    List<ServiceGesResponseDTO> getAllServices();
    ServiceGesResponseDTO updateService(String id, ServiceGesRequestDTO serviceDTO);
    
    // Método para verificar existencia
    boolean existsByServiceTypeId(String serviceTypeId);
}