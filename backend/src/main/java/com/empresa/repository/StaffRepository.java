package com.empresa.repository;

import com.empresa.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

    // Consulta paginada por tipo de staff
    @Query("SELECT s FROM Staff s WHERE s.typeStaff = :typeStaff")
    Page<Staff> findByTypeStaff(String typeStaff, Pageable pageable);

    // Búsqueda por nombre o apellido (case insensitive)
    @Query("SELECT s FROM Staff s WHERE " +
           "LOWER(s.name1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Staff> searchByNameOrLastName(String searchTerm);

    // Consulta paginada por estado
    @Query("SELECT s FROM Staff s WHERE s.status = :status")
    Page<Staff> findByStatus(String status, Pageable pageable);

    // Verificación de existencia por número de teléfono
    boolean existsByNumberPhone(String numberPhone);

    // Verificación de existencia por email
    boolean existsByEmail(String email);

    // Búsqueda por número de teléfono (retorna Optional)
    Optional<Staff> findByNumberPhone(String numberPhone);

    // Búsqueda por email (retorna Optional)
    Optional<Staff> findByEmail(String email);

    // Búsqueda paginada combinando tipo y estado
    @Query("SELECT s FROM Staff s WHERE s.typeStaff = :typeStaff AND s.status = :status")
    Page<Staff> findByTypeStaffAndStatus(String typeStaff, String status, Pageable pageable);

    // Búsqueda completa con todos los filtros
    @Query("SELECT s FROM Staff s WHERE " +
           "(:typeStaff IS NULL OR s.typeStaff = :typeStaff) AND " +
           "(:status IS NULL OR s.status = :status) AND " +
           "(LOWER(s.name1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<Staff> findByFilters(String typeStaff, String status, String searchTerm, Pageable pageable);
    
    // Nueva consulta para búsqueda por tipo y nombre/apellido
    @Query("SELECT s FROM Staff s WHERE s.typeStaff = :type AND " +
           "(LOWER(s.name1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Staff> findByTypeAndNameOrLastName(String type, String searchTerm);
}