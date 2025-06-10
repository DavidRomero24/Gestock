package com.empresa.controller;

import com.empresa.dto.request.BillRequestDTO;
import com.empresa.dto.response.BillResponseDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.service.BillService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<BillResponseDTO> createBill(@Valid @RequestBody BillRequestDTO billDTO) {
        BillResponseDTO createdBill = billService.createBill(billDTO);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponseDTO> getBillById(@PathVariable Integer id) {
        BillResponseDTO bill = billService.getBillById(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping
    public ResponseEntity<PaginationResponseDTO<BillResponseDTO>> getAllBills(Pageable pageable) {
        PaginationResponseDTO<BillResponseDTO> bills = billService.getAllBills(pageable);
        return ResponseEntity.ok(bills);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillResponseDTO> updateBill(
            @PathVariable Integer id,
            @Valid @RequestBody BillRequestDTO billDTO) {
        BillResponseDTO updatedBill = billService.updateBill(id, billDTO);
        return ResponseEntity.ok(updatedBill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBill(@PathVariable Integer id) {
        billService.cancelBill(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<PaginationResponseDTO<BillResponseDTO>> findBillsByFilters(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String staffId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String status,
            Pageable pageable) {
        PaginationResponseDTO<BillResponseDTO> bills = billService.findBillsByFilters(
                customerId, staffId, startDate, endDate, status, pageable);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<BillResponseDTO>> findBillsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<BillResponseDTO> bills = billService.findBillsByDateRange(startDate, endDate);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/total-sales")
    public ResponseEntity<BigDecimal> getTotalSalesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        BigDecimal total = billService.getTotalSalesByDateRange(startDate, endDate);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<PaginationResponseDTO<BillResponseDTO>> findBillsByCustomer(
            @PathVariable String customerId,
            Pageable pageable) {
        PaginationResponseDTO<BillResponseDTO> bills = billService.findBillsByCustomer(customerId, pageable);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<PaginationResponseDTO<BillResponseDTO>> findBillsByStatus(
            @PathVariable String status,
            Pageable pageable) {
        PaginationResponseDTO<BillResponseDTO> bills = billService.findBillsByStatus(status, pageable);
        return ResponseEntity.ok(bills);
    }
}