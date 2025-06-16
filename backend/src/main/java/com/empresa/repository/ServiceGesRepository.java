package com.empresa.repository;

import com.empresa.model.ServiceGes;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ServiceGesRepository extends JpaRepository<ServiceGes, String> {
    
    // List<ServiceGes> findByServiceTypeId(@Param("serviceTypeId") String serviceTypeId);
    
    // Buscar servicios por rango de precio
    // @Query("SELECT s FROM Service s WHERE s.price BETWEEN :minPrice AND :maxPrice")
    // List<ServiceGes> findByPriceRange(@Param("minPrice") BigDecimal minPrice, 
    //                               @Param("maxPrice") BigDecimal maxPrice);
    
    // Buscar servicios por descripción (búsqueda insensible a mayúsculas)
    // @Query("SELECT s FROM Service s WHERE LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    // List<ServiceGes> findByDescriptionContainingIgnoreCase(@Param("keyword") String keyword);

    //  @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
    //        "FROM Service s WHERE s.serviceType.idSerType = :serviceTypeId")
    // List<ServiceGes> findByServiceType_IdSerType(String serviceTypeId);
    List<ServiceGes> findByServiceType_IdSerType(String serviceTypeId);


           boolean existsByServiceType_IdSerType(@Param("serviceTypeId") String serviceTypeId);
}