package com.empresa.mapper;

import com.empresa.dto.request.ServiceDetailRequestDTO;
import com.empresa.dto.response.ServiceDetailResponseDTO;
import com.empresa.model.Service;
import com.empresa.model.ServiceDetail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T01:00:58-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ServiceDetailMapperImpl implements ServiceDetailMapper {

    @Override
    public ServiceDetail toEntity(ServiceDetailRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ServiceDetail serviceDetail = new ServiceDetail();

        serviceDetail.setService( mapService( dto.getServiceId() ) );
        serviceDetail.setDescription( dto.getDescription() );
        serviceDetail.setPriceService( dto.getPriceService() );

        return serviceDetail;
    }

    @Override
    public ServiceDetailResponseDTO toDto(ServiceDetail entity) {
        if ( entity == null ) {
            return null;
        }

        ServiceDetailResponseDTO serviceDetailResponseDTO = new ServiceDetailResponseDTO();

        serviceDetailResponseDTO.setServiceId( entityServiceIdService( entity ) );
        serviceDetailResponseDTO.setServiceName( entityServiceDescription( entity ) );
        serviceDetailResponseDTO.setDescription( entity.getDescription() );
        serviceDetailResponseDTO.setPriceService( entity.getPriceService() );

        return serviceDetailResponseDTO;
    }

    private String entityServiceIdService(ServiceDetail serviceDetail) {
        if ( serviceDetail == null ) {
            return null;
        }
        Service service = serviceDetail.getService();
        if ( service == null ) {
            return null;
        }
        String idService = service.getIdService();
        if ( idService == null ) {
            return null;
        }
        return idService;
    }

    private String entityServiceDescription(ServiceDetail serviceDetail) {
        if ( serviceDetail == null ) {
            return null;
        }
        Service service = serviceDetail.getService();
        if ( service == null ) {
            return null;
        }
        String description = service.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }
}
