package com.empresa.mapper;

import com.empresa.model.Payment;
import com.empresa.dto.request.TransferPaymentRequestDTO;
import com.empresa.dto.response.PaymentInfoDTO;
import com.empresa.dto.response.TransferPaymentResponseDTO;
import com.empresa.model.TransferPayment;
import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TransferPaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "payments", ignore = true)
    TransferPayment toEntity(TransferPaymentRequestDTO dto);

    @Mapping(target = "relatedPayments", source = "payments", qualifiedByName = "mapPayments")
    TransferPaymentResponseDTO toDto(TransferPayment entity);

    @Named("mapPayments")
    default List<PaymentInfoDTO> mapPayments(List<Payment> payments) {
        if (payments == null) return null;
        return payments.stream().map(payment -> {
            PaymentInfoDTO dto = new PaymentInfoDTO();
            dto.setPaymentId(payment.getId());
            dto.setPaymentDate(payment.getDate().atStartOfDay());
            dto.setAmount(payment.getAmountPaid());
            return dto;
        }).collect(Collectors.toList());
    }

    @AfterMapping
    default void generateTransferCode(@MappingTarget TransferPayment transferPayment) {
        if (transferPayment.getTransferCode() == null) {
            // Generar código de transferencia único
            String code = "TRF-" + System.currentTimeMillis();
            transferPayment.setTransferCode(code);
        }
    }
}