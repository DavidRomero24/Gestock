package com.empresa.repository;

import com.empresa.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, String> {
    @Query("SELECT COUNT(s) > 0 FROM ServiceGes s WHERE s.serviceType.idSerType = :serviceTypeId")
boolean existsByServiceTypeId(@Param("serviceTypeId") String serviceTypeId);
}
