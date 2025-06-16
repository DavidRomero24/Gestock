package com.empresa.mapper;

import com.empresa.dto.request.ServiceGesRequestDTO;
import com.empresa.dto.response.ServiceGesResponseDTO;
import com.empresa.model.ServiceGes;
import com.empresa.model.ServiceType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T18:15:38-0500",
    comments = "version: 1.6.1, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ServiceGesMapperImpl implements ServiceGesMapper {

    @Override
    public ServiceGes toEntity(ServiceGesRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ServiceGes serviceGes = new ServiceGes();

        serviceGes.setServiceType( mapServiceType( dto.getIdSerType() ) );
        serviceGes.setIdService( dto.getIdService() );
        serviceGes.setDescription( dto.getDescription() );
        serviceGes.setPrice( dto.getPrice() );

        return serviceGes;
    }

    @Override
    public ServiceGesResponseDTO toDto(ServiceGes service) {
        if ( service == null ) {
            return null;
        }

        ServiceGesResponseDTO serviceGesResponseDTO = new ServiceGesResponseDTO();

        serviceGesResponseDTO.setServiceTypeName( serviceServiceTypeServiceCategory( service ) );
        serviceGesResponseDTO.setServiceTypeId( serviceServiceTypeIdSerType( service ) );
        serviceGesResponseDTO.setDescription( service.getDescription() );
        serviceGesResponseDTO.setIdService( service.getIdService() );
        serviceGesResponseDTO.setPrice( service.getPrice() );

        return serviceGesResponseDTO;
    }

    private String serviceServiceTypeServiceCategory(ServiceGes serviceGes) {
        ServiceType serviceType = serviceGes.getServiceType();
        if ( serviceType == null ) {
            return null;
        }
        return serviceType.getServiceCategory();
    }

    private String serviceServiceTypeIdSerType(ServiceGes serviceGes) {
        ServiceType serviceType = serviceGes.getServiceType();
        if ( serviceType == null ) {
            return null;
        }
        return serviceType.getIdSerType();
    }
}
