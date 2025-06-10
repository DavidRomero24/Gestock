package com.empresa.repository;

import com.empresa.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("SELECT b FROM Bill b WHERE " +
           "(:customerId IS NULL OR b.customer.id = :customerId) AND " +
           "(:staffId IS NULL OR b.staff.id = :staffId) AND " +
           "(:startDate IS NULL OR b.date >= :startDate) AND " +
           "(:endDate IS NULL OR b.date <= :endDate) AND " +
           "(:status IS NULL OR b.status = :status)")
    Page<Bill> findByFilters(
            @Param("customerId") String customerId,
            @Param("staffId") String staffId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("status") String status,
            Pageable pageable);

    @Query("SELECT b FROM Bill b WHERE b.date BETWEEN :start AND :end")
    List<Bill> findByDateRange(@Param("start") LocalDate start, 
                             @Param("end") LocalDate end);

    @Query("SELECT SUM(b.total) FROM Bill b WHERE b.date BETWEEN :start AND :end")
    BigDecimal getTotalSalesByDateRange(@Param("start") LocalDate start, 
                                      @Param("end") LocalDate end);

    @Query("SELECT b FROM Bill b WHERE b.customer.id = :customerId ORDER BY b.date DESC")
    Page<Bill> findByCustomerId(@Param("customerId") String customerId, 
                               Pageable pageable);

    @Query("SELECT b FROM Bill b WHERE b.status = :status")
    Page<Bill> findByStatus(@Param("status") String status, 
                           Pageable pageable);

    List<Bill> findByDateBetween(LocalDate startDate, LocalDate endDate);

    

}