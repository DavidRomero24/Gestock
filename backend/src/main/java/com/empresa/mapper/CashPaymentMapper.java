package com.empresa.mapper;

import com.empresa.dto.request.CashPaymentRequestDTO;
import com.empresa.dto.response.CashPaymentResponseDTO;
import com.empresa.model.CashPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CashPaymentMapper {

    @Mapping(target = "id", ignore = true) // El ID lo generas manualmente o desde el servicio
    CashPayment toEntity(CashPaymentRequestDTO dto);

    CashPaymentResponseDTO toDto(CashPayment entity);
}
