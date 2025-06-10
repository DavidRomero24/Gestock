package com.empresa.repository;

import com.empresa.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    // Búsqueda por nombre o apellido (case insensitive)
    @Query("SELECT c FROM Customer c WHERE " +
           "LOWER(c.name1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.lastName1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.lastName2) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Customer> searchByNameOrLastName(String searchTerm);

    // Verificación de existencia por número de teléfono
    boolean existsByPhone(String phone);

    // Verificación de existencia por email
    boolean existsByEmail(String email);

    // Búsqueda por número de teléfono (retorna Optional)
    Optional<Customer> findByPhone(String phone);

    // Búsqueda por email (retorna Optional)
    Optional<Customer> findByEmail(String email);

    // Búsqueda paginada con filtros
    @Query("SELECT c FROM Customer c WHERE " +
           "(LOWER(c.name1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.lastName1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.lastName2) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<Customer> findBySearchTerm(String searchTerm, Pageable pageable);
}