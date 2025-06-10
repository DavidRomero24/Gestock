package com.empresa.repository;

import com.empresa.model.BillSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillSupplyRepository extends JpaRepository<BillSupply, Integer> {
    
    @Query("SELECT bs FROM BillSupply bs WHERE bs.supplier.id = :supplierId")
    List<BillSupply> findBySupplierId(String supplierId);
    
    @Query("SELECT bs FROM BillSupply bs WHERE bs.date BETWEEN :startDate AND :endDate")
    List<BillSupply> findByDateRange(java.util.Date startDate, java.util.Date endDate);
}