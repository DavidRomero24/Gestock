package com.empresa.repository;

import com.empresa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.category) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Product> searchByNameOrCategory(String searchTerm);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(String category);

    @Query("SELECT p FROM Product p WHERE p.stock < :minStock")
    List<Product> findLowStockProducts(Integer minStock);

    @Query("SELECT p FROM Product p WHERE " +
           "(:category IS NULL OR p.category = :category) AND " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<Product> findByFilters(String searchTerm, String category, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p SET p.stock = p.stock + :quantity WHERE p.idProduct = :productId")
    @Transactional
    void updateStock(@Param("productId") String productId, @Param("quantity") int quantity);

    @Query("SELECT p.stock FROM Product p WHERE p.idProduct = :productId")
    Integer findStockById(@Param("productId") String productId);
}