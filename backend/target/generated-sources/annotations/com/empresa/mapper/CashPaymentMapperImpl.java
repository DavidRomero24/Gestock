package com.empresa.mapper;

import com.empresa.dto.request.CashPaymentRequestDTO;
import com.empresa.dto.response.CashPaymentResponseDTO;
import com.empresa.model.CashPayment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-16T03:30:50-0500",
    comments = "version: 1.6.1, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
)
@Component
public class CashPaymentMapperImpl implements CashPaymentMapper {

    @Override
    public CashPayment toEntity(CashPaymentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CashPayment cashPayment = new CashPayment();

        cashPayment.setAmount( dto.getAmount() );

        return cashPayment;
    }

    @Override
    public CashPaymentResponseDTO toDto(CashPayment entity) {
        if ( entity == null ) {
            return null;
        }

        CashPaymentResponseDTO cashPaymentResponseDTO = new CashPaymentResponseDTO();

        cashPaymentResponseDTO.setId( entity.getId() );
        cashPaymentResponseDTO.setAmount( entity.getAmount() );

        return cashPaymentResponseDTO;
    }
}
