package com.empresa.repository;

import com.empresa.model.BillDetail;
import com.empresa.model.BillDetail.BillDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, BillDetailId> {
    
    @Query("SELECT bd FROM BillDetail bd WHERE bd.product.idProduct = :productId")
    List<BillDetail> findByProductId(String productId);
    
    @Query("SELECT bd FROM BillDetail bd WHERE bd.bill.id = :billId")
    List<BillDetail> findByBillId(Integer billId);
}