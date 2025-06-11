package com.empresa.mapper;

import com.empresa.dto.request.CashPaymentRequestDTO;
import com.empresa.dto.response.CashPaymentResponseDTO;
import com.empresa.model.CashPayment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T01:00:58-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
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

        cashPaymentResponseDTO.setAmount( entity.getAmount() );
        cashPaymentResponseDTO.setId( entity.getId() );

        return cashPaymentResponseDTO;
    }
}
