package com.empresa.repository;

import com.empresa.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;


@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {
    
    @Query("SELECT s FROM Service s WHERE s.serviceType.idSerType = :serviceTypeId")
    List<Service> findByServiceTypeId(String serviceTypeId);
    
    @Query("SELECT s FROM Service s WHERE s.price BETWEEN :minPrice AND :maxPrice")
    List<Service> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}