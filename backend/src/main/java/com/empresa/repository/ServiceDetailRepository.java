package com.empresa.repository;

import com.empresa.model.ServiceDetail;
import com.empresa.model.ServiceDetail.ServiceDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, ServiceDetailId> {
}
