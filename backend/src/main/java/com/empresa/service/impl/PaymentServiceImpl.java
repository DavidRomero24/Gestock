package com.empresa.service.impl;

import com.empresa.dto.request.PaymentRequestDTO;
import com.empresa.dto.response.PaymentResponseDTO;
import com.empresa.dto.exception.InvalidDataException;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.mapper.PaymentMapper;
import com.empresa.model.*;
import com.empresa.repository.BillRepository;
import com.empresa.repository.PaymentRepository;
import com.empresa.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BillRepository billRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                            BillRepository billRepository,
                            PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.billRepository = billRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentDTO) {
        // Validar factura
        Bill bill = billRepository.findById(paymentDTO.getBillId())
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con ID: " + paymentDTO.getBillId()));

        // Validar que el pago no exceda el total pendiente
        BigDecimal totalPaid = paymentRepository.getTotalPaidByBillId(bill.getId());
        BigDecimal pendingAmount = bill.getTotal().subtract(totalPaid);
        
        if (paymentDTO.getAmountPaid().compareTo(pendingAmount) > 0) {
            throw new InvalidDataException("El monto del pago excede el saldo pendiente de la factura");
        }

        Payment payment = paymentMapper.toEntity(paymentDTO);
        payment.setBill(bill);
        
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toDto(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDTO getPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getPaymentsByBill(Integer billId) {
        return paymentRepository.findByBillId(billId).stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getPaymentsByMethod(String method) {
        if (!"EFECTIVO".equalsIgnoreCase(method) && !"TRANSFERENCIA".equalsIgnoreCase(method)) {
            throw new InvalidDataException("Método de pago no válido. Use EFECTIVO o TRANSFERENCIA");
        }
        return paymentRepository.findByPaymentMethod(method.toUpperCase()).stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getPaymentsByDateRange(LocalDate start, LocalDate end) {
        return paymentRepository.findByDateRange(start, end).stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalPaidByBill(Integer billId) {
        return paymentRepository.getTotalPaidByBillId(billId);
    }
}