package com.empresa.controller;

import com.empresa.dto.request.PaymentRequestDTO;
import com.empresa.dto.response.PaymentResponseDTO;
import com.empresa.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO paymentDTO) {
        PaymentResponseDTO createdPayment = paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Integer id) {
        PaymentResponseDTO payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/bill/{billId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByBill(@PathVariable Integer billId) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByBill(billId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/method/{method}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByMethod(
            @PathVariable String method) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByMethod(method);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByDateRange(startDate, endDate);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/bill/{billId}/total-paid")
    public ResponseEntity<BigDecimal> getTotalPaidByBill(@PathVariable Integer billId) {
        BigDecimal total = paymentService.getTotalPaidByBill(billId);
        return ResponseEntity.ok(total);
    }
}