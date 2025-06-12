package com.empresa.repository;

import com.empresa.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, String> {

    // Buscar por email (para validación)
    Optional<Supplier> findByEmail(String email);

    // Buscar por teléfono (para validación)
    Optional<Supplier> findByPhone(String phone);

    // Verificar existencia por email
    boolean existsByEmail(String email);

    // Verificar existencia por teléfono
    boolean existsByPhone(String phone);

    // Buscar proveedores por ciudad
    @Query("SELECT s FROM Supplier s WHERE s.city.id = :cityId")
    List<Supplier> findByCityId(@Param("cityId") String cityId);

    // Buscar proveedores por compañía
    List<Supplier> findByCompanyContainingIgnoreCase(String companyName);

    // Verificar existencia por campos únicos (excluyendo el actual en updates)
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
           "FROM Supplier s WHERE s.email = :email AND s.id != :excludeId")
    boolean existsByEmailExcludingId(@Param("email") String email, 
                                     @Param("excludeId") String excludeId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
           "FROM Supplier s WHERE s.phone = :phone AND s.id != :excludeId")
    boolean existsByPhoneExcludingId(@Param("phone") String phone, 
                                     @Param("excludeId") String excludeId);
}
