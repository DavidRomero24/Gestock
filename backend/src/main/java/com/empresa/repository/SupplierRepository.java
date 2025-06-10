package com.empresa.repository;

import com.empresa.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    
    @Query("SELECT s FROM Supplier s WHERE s.city.id.cityId = :cityId")
    List<Supplier> findByCityId(String cityId);
    
    @Query("SELECT s FROM Supplier s WHERE s.company = :companyName")
    List<Supplier> findByCompanyName(String companyName);
    
    @Query("SELECT s FROM Supplier s WHERE s.name1 LIKE %:name% OR s.lastName1 LIKE %:name%")
    List<Supplier> findByNameOrLastNameContaining(String name);
}