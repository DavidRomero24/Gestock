package com.empresa.service;

import com.empresa.dto.request.BillRequestDTO;
import com.empresa.dto.response.BillResponseDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;

public interface BillService {
    BillResponseDTO createBill(BillRequestDTO billDTO);
    BillResponseDTO getBillById(Integer id);
    PaginationResponseDTO<BillResponseDTO> getAllBills(Pageable pageable);
    BillResponseDTO updateBill(Integer id, BillRequestDTO billDTO);
    void cancelBill(Integer id);
    PaginationResponseDTO<BillResponseDTO> findBillsByFilters(
            String customerId, String staffId, 
            LocalDate startDate, LocalDate endDate,
            String status, Pageable pageable);
    List<BillResponseDTO> findBillsByDateRange(LocalDate startDate, LocalDate endDate);
    BigDecimal getTotalSalesByDateRange(LocalDate startDate, LocalDate endDate);
    PaginationResponseDTO<BillResponseDTO> findBillsByCustomer(String customerId, Pageable pageable);
    PaginationResponseDTO<BillResponseDTO> findBillsByStatus(String status, Pageable pageable);
}