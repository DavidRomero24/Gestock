package com.empresa.mapper;

import com.empresa.dto.request.ServiceGesRequestDTO;
import com.empresa.dto.response.ServiceGesResponseDTO;
import com.empresa.model.ServiceGes;
import com.empresa.model.ServiceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ServiceGesMapper {

    @Mapping(target = "serviceType", source = "idSerType", qualifiedByName = "mapServiceType")
    ServiceGes toEntity(ServiceGesRequestDTO dto);

    @Mapping(target = "serviceTypeName", source = "service.serviceType.serviceCategory")
    @Mapping(target = "serviceTypeId", source = "service.serviceType.idSerType")
    ServiceGesResponseDTO toDto(ServiceGes service);

    @Named("mapServiceType")
    default ServiceType mapServiceType(String idSerType) {
        if (idSerType == null) return null;
        ServiceType serviceType = new ServiceType();
        serviceType.setIdSerType(idSerType);
        return serviceType;
    }
}