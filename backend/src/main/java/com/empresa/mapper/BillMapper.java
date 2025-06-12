package com.empresa.mapper;

import com.empresa.dto.request.BillRequestDTO;
import com.empresa.dto.response.BillResponseDTO;
import com.empresa.model.Bill;
import com.empresa.model.Customer;
import com.empresa.model.Staff;
import com.empresa.model.Payment;
import org.mapstruct.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {BillDetailMapper.class, ServiceDetailMapper.class, PaymentMapper.class})
public abstract class BillMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = "customerId", qualifiedByName = "mapCustomer")
    @Mapping(target = "staff", source = "staffId", qualifiedByName = "mapStaff")
    @Mapping(target = "billDetails", source = "billDetails")
    @Mapping(target = "serviceDetails", source = "serviceDetails")
    @Mapping(target = "payments", source = "payments")
    public abstract Bill toEntity(BillRequestDTO dto);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", expression = "java(getCustomerFullName(bill.getCustomer()))")
    @Mapping(target = "staffId", source = "staff.idStaff")
    @Mapping(target = "staffName", expression = "java(getStaffFullName(bill.getStaff()))")
    @Mapping(target = "paidAmount", expression = "java(calculatePaidAmount(bill))")
    @Mapping(target = "pendingAmount", expression = "java(calculatePendingAmount(bill))")
    @Mapping(target = "status", expression = "java(determineStatus(bill))")
    public abstract BillResponseDTO toDto(Bill bill);

    @Named("mapCustomer")
    protected Customer mapCustomer(String customerId) {
        if (customerId == null || customerId.isBlank()) return null;
        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
    }

    @Named("mapStaff")
    protected Staff mapStaff(String staffId) {
        if (staffId == null || staffId.isBlank()) return null;
        Staff staff = new Staff();
        staff.setIdStaff(staffId);
        return staff;
    }

    protected String getCustomerFullName(Customer customer) {
        if (customer == null) return "";
        return customer.getName1() + " " +
               (customer.getName2() != null ? customer.getName2() + " " : "") +
               customer.getLastName1() +
               (customer.getLastName2() != null ? " " + customer.getLastName2() : "");
    }

    protected String getStaffFullName(Staff staff) {
        if (staff == null) return "";
        return staff.getName1() + " " +
               (staff.getName2() != null ? staff.getName2() + " " : "") +
               staff.getLastName();
    }

    protected BigDecimal calculatePaidAmount(Bill bill) {
        return bill.getPayments() == null
                ? BigDecimal.ZERO
                : bill.getPayments().stream()
                    .map(Payment::getAmountPaid)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected BigDecimal calculatePendingAmount(Bill bill) {
        return bill.getTotal().subtract(calculatePaidAmount(bill));
    }

    protected String determineStatus(Bill bill) {
        if (bill.getStatus() != null) return bill.getStatus();
        BigDecimal pending = calculatePendingAmount(bill);
        if (pending.compareTo(BigDecimal.ZERO) <= 0) {
            return "PAGADA";
        } else if (pending.compareTo(bill.getTotal()) == 0) {
            return "PENDIENTE";
        } else {
            return "PARCIAL";
        }
    }

    @AfterMapping
    protected void setDefaults(@MappingTarget Bill bill) {
        if (bill.getDate() == null) bill.setDate(LocalDate.now());
        if (bill.getStatus() == null) bill.setStatus("PENDIENTE");
    }
}