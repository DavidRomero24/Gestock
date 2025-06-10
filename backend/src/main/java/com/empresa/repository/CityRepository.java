package com.empresa.repository;

import com.empresa.model.City;
import com.empresa.model.City.CityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, CityId> {
    
    @Query("SELECT c FROM City c WHERE c.id.departmentId = :departmentId")
    List<City> findByDepartmentId(String departmentId);
    
    @Query("SELECT c FROM City c WHERE c.nameCity LIKE %:name%")
    List<City> findByNameContaining(String name);
}