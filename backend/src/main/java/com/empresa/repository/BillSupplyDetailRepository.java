package com.empresa.repository;

import com.empresa.model.BillSupplyDetail;
import com.empresa.model.BillSupplyDetail.BillSupplyDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillSupplyDetailRepository extends JpaRepository<BillSupplyDetail, BillSupplyDetailId> {
}
