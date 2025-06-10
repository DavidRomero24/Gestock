package com.empresa.service.impl;

import com.empresa.dto.request.BillRequestDTO;
import com.empresa.dto.request.BillDetailRequestDTO;
import com.empresa.dto.response.BillResponseDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.dto.exception.InvalidDataException;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.mapper.BillMapper;
import com.empresa.model.*;
import com.empresa.repository.BillRepository;
import com.empresa.repository.CustomerRepository;
import com.empresa.repository.StaffRepository;
import com.empresa.service.BillService;
import com.empresa.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final ProductService productService;
    private final BillMapper billMapper;

    public BillServiceImpl(BillRepository billRepository,
                           CustomerRepository customerRepository,
                           StaffRepository staffRepository,
                           ProductService productService,
                           BillMapper billMapper) {
        this.billRepository = billRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.productService = productService;
        this.billMapper = billMapper;
    }

    @Override
    @Transactional
    public BillResponseDTO createBill(BillRequestDTO billDTO) {
        Customer customer = customerRepository.findById(billDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + billDTO.getCustomerId()));

        Staff staff = staffRepository.findById(billDTO.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con ID: " + billDTO.getStaffId()));

        validateBillProducts(billDTO.getBillDetails());

        Bill bill = billMapper.toEntity(billDTO);
        bill.setCustomer(customer);
        bill.setStaff(staff);

        associateBillDetails(bill);

        if (bill.getTotal() == null) {
            calculateBillTotal(bill);
        }

        validateBillPayments(bill);

        Bill savedBill = billRepository.save(bill);
        return billMapper.toDto(savedBill);
    }

    @Override
    @Transactional(readOnly = true)
    public BillResponseDTO getBillById(Integer id) {
        return billRepository.findById(id)
                .map(billMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<BillResponseDTO> getAllBills(Pageable pageable) {
        Page<Bill> billPage = billRepository.findAll(pageable);
        return buildPaginationResponse(billPage);
    }

    @Override
    @Transactional
    public BillResponseDTO updateBill(Integer id, BillRequestDTO billDTO) {
        Bill existingBill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con ID: " + id));

        if (!"PENDIENTE".equals(existingBill.getStatus())) {
            throw new InvalidDataException("Solo se pueden modificar facturas con estado PENDIENTE");
        }

        if (billDTO.getBillDetails() != null && !billDTO.getBillDetails().isEmpty()) {
            validateBillProducts(billDTO.getBillDetails());
        }

        Bill updatedBill = billMapper.toEntity(billDTO);
        updatedBill.setId(id);
        updatedBill.setCustomer(existingBill.getCustomer());
        updatedBill.setStaff(existingBill.getStaff());

        associateBillDetails(updatedBill);

        Bill savedBill = billRepository.save(updatedBill);
        return billMapper.toDto(savedBill);
    }

    @Override
    @Transactional
    public void cancelBill(Integer id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con ID: " + id));

        if ("CANCELADA".equals(bill.getStatus())) {
            throw new InvalidDataException("La factura ya está cancelada");
        }

        revertProductStock(bill);
        bill.setStatus("CANCELADA");
        billRepository.save(bill);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<BillResponseDTO> findBillsByFilters(String customerName, String customerId, LocalDate startDate, LocalDate endDate, String status, Pageable pageable) {
        Page<Bill> billPage = billRepository.findByFilters(customerName, customerId, startDate, endDate, status, pageable);

        List<BillResponseDTO> content = billPage.getContent()
                .stream()
                .map(billMapper::toDto)
                .collect(Collectors.toList());

        return PaginationResponseDTO.<BillResponseDTO>builder()
                .content(content)
                .currentPage(billPage.getNumber())
                .totalItems(billPage.getTotalElements())
                .totalPages(billPage.getTotalPages())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<BillResponseDTO> findBillsByCustomer(String customerId, Pageable pageable) {
        Page<Bill> billPage = billRepository.findByCustomerId(customerId, pageable);

        List<BillResponseDTO> content = billPage.getContent()
                .stream()
                .map(billMapper::toDto)
                .collect(Collectors.toList());

        return PaginationResponseDTO.<BillResponseDTO>builder()
                .content(content)
                .currentPage(billPage.getNumber())
                .totalItems(billPage.getTotalElements())
                .totalPages(billPage.getTotalPages())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<BillResponseDTO> findBillsByStatus(String status, Pageable pageable) {
        Page<Bill> billPage = billRepository.findByStatus(status, pageable);

        List<BillResponseDTO> content = billPage.getContent()
                .stream()
                .map(billMapper::toDto)
                .collect(Collectors.toList());

        return PaginationResponseDTO.<BillResponseDTO>builder()
            .content(content)
            .currentPage(billPage.getNumber())
            .totalItems(billPage.getTotalElements())
            .totalPages(billPage.getTotalPages())
            .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BillResponseDTO> findBillsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Bill> bills = billRepository.findByDateBetween(startDate, endDate);

        return bills.stream()
                .map(billMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalSalesByDateRange(LocalDate startDate, LocalDate endDate) {
        BigDecimal total = billRepository.getTotalSalesByDateRange(startDate, endDate);
        return total != null ? total : BigDecimal.ZERO;
    }



    private void validateBillProducts(List<BillDetailRequestDTO> details) {
        if (details == null || details.isEmpty()) {
            throw new InvalidDataException("La factura debe contener al menos un producto");
        }

        details.forEach(detail -> {
            if (detail.getQuantity() <= 0) {
                throw new InvalidDataException("La cantidad debe ser mayor a cero");
            }
            if (detail.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidDataException("El precio unitario debe ser mayor a cero");
            }
        });

        productService.validateProductsStock(details);
    }

    private void associateBillDetails(Bill bill) {
        if (bill.getBillDetails() != null) {
            bill.getBillDetails().forEach(detail -> {
                detail.setBill(bill);
                productService.decreaseProductStock(
                        detail.getProduct().getIdProduct(),
                        detail.getQuantity());
            });
        }

        if (bill.getServiceDetails() != null) {
            bill.getServiceDetails().forEach(detail -> detail.setBill(bill));
        }

        if (bill.getPayments() != null) {
            bill.getPayments().forEach(payment -> payment.setBill(bill));
        }
    }

    private void calculateBillTotal(Bill bill) {
        BigDecimal productsTotal = BigDecimal.ZERO;
        if (bill.getBillDetails() != null) {
            productsTotal = bill.getBillDetails().stream()
                    .map(BillDetail::getSubTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        BigDecimal servicesTotal = BigDecimal.ZERO;
        if (bill.getServiceDetails() != null) {
            servicesTotal = bill.getServiceDetails().stream()
                    .map(ServiceDetail::getPriceService)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        bill.setTotal(productsTotal.add(servicesTotal));
    }

    private void validateBillPayments(Bill bill) {
        BigDecimal totalPaid = BigDecimal.ZERO;
        if (bill.getPayments() != null) {
            totalPaid = bill.getPayments().stream()
                    .map(Payment::getAmountPaid)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        if (totalPaid.compareTo(bill.getTotal()) > 0) {
            throw new InvalidDataException("El total de pagos no puede exceder el total de la factura");
        }
    }

    private void revertProductStock(Bill bill) {
        if (bill.getBillDetails() != null) {
            bill.getBillDetails().forEach(detail -> {
                productService.increaseProductStock(
                        detail.getProduct().getIdProduct(),
                        detail.getQuantity());
            });
        }
    }

    private PaginationResponseDTO<BillResponseDTO> buildPaginationResponse(Page<Bill> billPage) {
        List<BillResponseDTO> content = billPage.getContent()
                .stream()
                .map(billMapper::toDto)
                .collect(Collectors.toList());

        return PaginationResponseDTO.<BillResponseDTO>builder()
                .content(content)
                .currentPage(billPage.getNumber())
                .totalItems(billPage.getTotalElements())
                .totalPages(billPage.getTotalPages())
                .build();
    }
}
