package com.empresa.service.impl;

import com.empresa.dto.request.StaffRequestDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.dto.response.StaffResponseDTO;
import com.empresa.dto.exception.DuplicateResourceException;
import com.empresa.dto.exception.InvalidDataException;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.mapper.StaffMapper;
import com.empresa.model.Staff;
import com.empresa.repository.StaffRepository;
import com.empresa.service.StaffService;
import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
// import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public StaffServiceImpl(StaffRepository staffRepository, StaffMapper staffMapper) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    @Transactional
    public StaffResponseDTO createStaff(StaffRequestDTO staffDTO) {
        // Validar datos únicos
        validateUniqueStaff(staffDTO);
        
        // Mapear DTO a entidad
        Staff staff = staffMapper.toEntity(staffDTO);
        staff.setStatus("ACTIVO"); // Estado por defecto
        
        // Guardar en base de datos
        Staff savedStaff = staffRepository.save(staff);
        
        // Retornar DTO de respuesta
        return staffMapper.toDto(savedStaff);
    }

    @Override
    @Transactional(readOnly = true)
    public StaffResponseDTO getStaffById(String id) {
        return staffRepository.findById(id)
                .map(staffMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Staff no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<StaffResponseDTO> getAllStaff(Pageable pageable) {
        Page<Staff> staffPage = staffRepository.findAll(pageable);
        return buildPaginationResponse(staffPage);
    }

    @Override
    @Transactional
    public StaffResponseDTO updateStaff(String id, StaffRequestDTO staffDTO) {
        // Verificar existencia
        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff no encontrado con ID: " + id));
        
        // Validar restricciones de actualización
        validateUpdateConstraints(id, staffDTO);
        
        // Actualizar entidad
        staffMapper.updateEntity(staffDTO, existingStaff);
        
        // Guardar cambios
        Staff updatedStaff = staffRepository.save(existingStaff);
        
        return staffMapper.toDto(updatedStaff);
    }

    @Override
    @Transactional
    public void changeStaffStatus(String id, String status) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con ID: " + id));
        
        List<String> validStatuses = Arrays.asList("ACTIVO", "INACTIVO", "VACACIONES", "LICENCIA");
        String upperStatus = status.toUpperCase();
        
        if (!validStatuses.contains(upperStatus)) {
            throw new InvalidDataException("Estado no válido. Valores permitidos: " + validStatuses);
        }
        
        staff.setStatus(upperStatus);
        staffRepository.save(staff);
    }

    @Override
    @Transactional
    public void deleteStaff(String id) {
        if (!staffRepository.existsById(id)) {
            throw new ResourceNotFoundException("Staff no encontrado con ID: " + id);
        }
        staffRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaffResponseDTO> searchStaff(String searchTerm, String type) {
        List<Staff> staffList;
        
        if (type != null && !type.isEmpty()) {
            staffList = staffRepository.findByTypeAndNameOrLastName(type, searchTerm);
        } else {
            staffList = staffRepository.searchByNameOrLastName(searchTerm);
        }
        
        return staffList.stream()
                .map(staffMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<StaffResponseDTO> getStaffByType(String typeStaff, Pageable pageable) {
        Page<Staff> staffPage = staffRepository.findByTypeStaff(typeStaff, pageable);
        return buildPaginationResponse(staffPage);
    }

    // Métodos auxiliares privados
    private void validateUniqueStaff(StaffRequestDTO staffDTO) {
        // Validar número de teléfono único
        if (staffRepository.existsByNumberPhone(staffDTO.getNumberPhone())) {
            throw new DuplicateResourceException("El número de teléfono ya está registrado");
        }
        
        // Validar email único (si está presente)
        if (staffDTO.getEmail() != null && !staffDTO.getEmail().isEmpty() 
                && staffRepository.existsByEmail(staffDTO.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }
    }

    private void validateUpdateConstraints(String id, StaffRequestDTO staffDTO) {
        // Validar número de teléfono único para otros empleados
        staffRepository.findByNumberPhone(staffDTO.getNumberPhone())
            .ifPresent(staff -> {
                if (!staff.getIdStaff().equals(id)) {
                    throw new DuplicateResourceException("El número de teléfono ya está registrado por otro empleado");
                }
            });
        
        // Validar email único para otros empleados (si está presente)
        if (staffDTO.getEmail() != null && !staffDTO.getEmail().isEmpty()) {
            staffRepository.findByEmail(staffDTO.getEmail())
                .ifPresent(staff -> {
                    if (!staff.getIdStaff().equals(id)) {
                        throw new DuplicateResourceException("El email ya está registrado por otro empleado");
                    }
                });
        }
    }

    private PaginationResponseDTO<StaffResponseDTO> buildPaginationResponse(Page<Staff> staffPage) {
        List<StaffResponseDTO> content = staffPage.getContent()
                .stream()
                .map(staffMapper::toDto)
                .collect(Collectors.toList());
        
        return PaginationResponseDTO.<StaffResponseDTO>builder()
                .content(content)
                .currentPage(staffPage.getNumber())
                .totalItems(staffPage.getTotalElements())
                .totalPages(staffPage.getTotalPages())
                .build();
    }
}