package com.empresa.service.impl;

import com.empresa.dto.request.SupplierRequestDTO;
import com.empresa.dto.response.SupplierResponseDTO;
import com.empresa.dto.exception.DuplicateResourceException;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.mapper.SupplierMapper;
import com.empresa.model.City;
import com.empresa.model.Supplier;
import com.empresa.repository.CityRepository;
import com.empresa.repository.SupplierRepository;
import com.empresa.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final CityRepository cityRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, 
                             CityRepository cityRepository,
                             SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.cityRepository = cityRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    @Transactional
    public SupplierResponseDTO createSupplier(SupplierRequestDTO supplierDTO) {
        // Validar unicidad
        if (supplierRepository.existsById(supplierDTO.getId())) {
            throw new DuplicateResourceException("El ID del proveedor ya existe");
        }
        
        validateUniqueFields(supplierDTO);

        // Obtener ciudad con ID compuesto
        City.CityId cityId = new City.CityId(supplierDTO.getDepartmentId(), supplierDTO.getCityId());
        City city = cityRepository.findById(cityId)
            .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada"));

        // Mapear y guardar
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        supplier.setCity(city);
        
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(savedSupplier);
    }

    @Override
    public SupplierResponseDTO getSupplierById(String id) {
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));
        return supplierMapper.toDto(supplier);
    }

    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
            .map(supplierMapper::toDto)
            .toList();
    }

    @Override
    @Transactional
    public SupplierResponseDTO updateSupplier(String id, SupplierRequestDTO supplierDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        validateUniqueFields(supplierDTO, id);

        // Actualizar campos básicos
        existingSupplier.setName1(supplierDTO.getName1());
        existingSupplier.setName2(supplierDTO.getName2());
        existingSupplier.setLastName1(supplierDTO.getLastName1());
        existingSupplier.setLastName2(supplierDTO.getLastName2());
        existingSupplier.setPhone(supplierDTO.getPhone());
        existingSupplier.setEmail(supplierDTO.getEmail());
        existingSupplier.setCompany(supplierDTO.getCompany());

        // Actualizar ciudad si cambió (usando ID compuesto)
        City.CityId newCityId = new City.CityId(supplierDTO.getDepartmentId(), supplierDTO.getCityId());
        if (!existingSupplier.getCity().getId().equals(newCityId)) {
            City city = cityRepository.findById(newCityId)
                .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada"));
            existingSupplier.setCity(city);
        }

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return supplierMapper.toDto(updatedSupplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(String id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proveedor no encontrado");
        }
        supplierRepository.deleteById(id);
    }

    private void validateUniqueFields(SupplierRequestDTO supplierDTO) {
        if (supplierRepository.existsByEmail(supplierDTO.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }
        if (supplierRepository.existsByPhone(supplierDTO.getPhone())) {
            throw new DuplicateResourceException("El teléfono ya está registrado");
        }
    }

    private void validateUniqueFields(SupplierRequestDTO supplierDTO, String excludedId) {
        supplierRepository.findByEmail(supplierDTO.getEmail())
            .ifPresent(s -> {
                if (!s.getId().equals(excludedId)) {
                    throw new DuplicateResourceException("El email ya está registrado");
                }
            });
        supplierRepository.findByPhone(supplierDTO.getPhone())
            .ifPresent(s -> {
                if (!s.getId().equals(excludedId)) {
                    throw new DuplicateResourceException("El teléfono ya está registrado");
                }
            });
    }
}