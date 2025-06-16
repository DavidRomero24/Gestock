package com.empresa.controller;

import com.empresa.dto.request.ServiceGesRequestDTO;
import com.empresa.dto.response.ServiceGesResponseDTO;
import com.empresa.service.ServiceGesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceGesController {

    private final ServiceGesService serviceService;

    public ServiceGesController(ServiceGesService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<ServiceGesResponseDTO> createService(@Valid @RequestBody ServiceGesRequestDTO serviceDTO) {
        ServiceGesResponseDTO response = serviceService.createService(serviceDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceGesResponseDTO> getServiceById(@PathVariable String id) {
        ServiceGesResponseDTO response = serviceService.getServiceById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-type/{serviceTypeId}")
    public ResponseEntity<List<ServiceGesResponseDTO>> getServicesByType(
            @PathVariable String serviceTypeId) {
        List<ServiceGesResponseDTO> response = serviceService.getServicesByType(serviceTypeId);
        return ResponseEntity.ok(response);
    }

    // @GetMapping("/by-price")
    // public ResponseEntity<List<ServiceGesResponseDTO>> getServicesByPriceRange(
    //         @RequestParam BigDecimal minPrice,
    //         @RequestParam BigDecimal maxPrice) {
    //     List<ServiceGesResponseDTO> response = serviceService.getServicesByPriceRange(minPrice, maxPrice);
    //     return ResponseEntity.ok(response);
    // }

    // @GetMapping("/search")
    // public ResponseEntity<List<ServiceGesResponseDTO>> searchServices(
    //         @RequestParam String keyword) {
    //     List<ServiceGesResponseDTO> response = serviceService.searchServices(keyword);
    //     return ResponseEntity.ok(response);
    // }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceGesResponseDTO> updateService(
            @PathVariable String id,
            @Valid @RequestBody ServiceGesRequestDTO serviceDTO) {
        ServiceGesResponseDTO response = serviceService.updateService(id, serviceDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable String id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ServiceGesResponseDTO>> getAllServices() {
        List<ServiceGesResponseDTO> response = serviceService.getAllServices();
        return ResponseEntity.ok(response);
    }
}