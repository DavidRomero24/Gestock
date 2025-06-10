package com.empresa.service;

import com.empresa.model.Supplier;
import com.empresa.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier findById(String id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public List<Supplier> findByCityId(String cityId) {
        return supplierRepository.findByCityId(cityId);
    }

    public List<Supplier> findByCompanyName(String companyName) {
        return supplierRepository.findByCompanyName(companyName);
    }

    public List<Supplier> findByNameOrLastNameContaining(String name) {
        return supplierRepository.findByNameOrLastNameContaining(name);
    }

    public void deleteSupplier(String id) {
        supplierRepository.deleteById(id);
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
}