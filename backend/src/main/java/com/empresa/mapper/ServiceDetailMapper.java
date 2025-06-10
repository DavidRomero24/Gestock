package com.empresa.mapper;

import com.empresa.dto.request.ServiceDetailRequestDTO;
import com.empresa.dto.response.ServiceDetailResponseDTO;
import com.empresa.model.ServiceDetail;
import com.empresa.model.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ServiceDetailMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "bill", ignore = true),
        @Mapping(target = "service", source = "serviceId", qualifiedByName = "mapService")
    })
    ServiceDetail toEntity(ServiceDetailRequestDTO dto);

    @Mappings({
        @Mapping(target = "serviceId", source = "service.idService"),
        @Mapping(target = "serviceName", source = "service.description")
    })
    ServiceDetailResponseDTO toDto(ServiceDetail entity);

    @Named("mapService")
    default Service mapService(String serviceId) {
        if (serviceId == null) return null;
        Service service = new Service();
        service.setIdService(serviceId);
        return service;
    }
}
