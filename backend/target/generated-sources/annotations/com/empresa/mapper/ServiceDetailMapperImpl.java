package com.empresa.mapper;

import com.empresa.dto.request.ServiceDetailRequestDTO;
import com.empresa.dto.response.ServiceDetailResponseDTO;
import com.empresa.model.ServiceDetail;
import com.empresa.model.ServiceGes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-16T03:30:50-0500",
    comments = "version: 1.6.1, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
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
        serviceDetailResponseDTO.setPriceService( entity.getPriceService() );
        serviceDetailResponseDTO.setDescription( entity.getDescription() );

        return serviceDetailResponseDTO;
    }

    private String entityServiceIdService(ServiceDetail serviceDetail) {
        ServiceGes service = serviceDetail.getService();
        if ( service == null ) {
            return null;
        }
        return service.getIdService();
    }

    private String entityServiceDescription(ServiceDetail serviceDetail) {
        ServiceGes service = serviceDetail.getService();
        if ( service == null ) {
            return null;
        }
        return service.getDescription();
    }
}
