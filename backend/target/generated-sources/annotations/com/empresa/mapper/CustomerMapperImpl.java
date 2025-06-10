package com.empresa.mapper;

import com.empresa.dto.request.CustomerRequestDTO;
import com.empresa.dto.response.CustomerResponseDTO;
import com.empresa.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-10T08:36:25-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName1( dto.getName1() );
        customer.setName2( dto.getName2() );
        customer.setLastName1( dto.getLastName1() );
        customer.setLastName2( dto.getLastName2() );
        customer.setPhone( dto.getPhone() );
        customer.setEmail( dto.getEmail() );
        customer.setAddress( dto.getAddress() );

        return customer;
    }

    @Override
    public CustomerResponseDTO toDto(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();

        customerResponseDTO.setAddress( entity.getAddress() );
        customerResponseDTO.setEmail( entity.getEmail() );
        customerResponseDTO.setId( entity.getId() );
        customerResponseDTO.setPhone( entity.getPhone() );

        customerResponseDTO.setFullName( generateFullName(entity) );

        return customerResponseDTO;
    }

    @Override
    public void updateEntity(CustomerRequestDTO dto, Customer entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName1() != null ) {
            entity.setName1( dto.getName1() );
        }
        if ( dto.getName2() != null ) {
            entity.setName2( dto.getName2() );
        }
        if ( dto.getLastName1() != null ) {
            entity.setLastName1( dto.getLastName1() );
        }
        if ( dto.getLastName2() != null ) {
            entity.setLastName2( dto.getLastName2() );
        }
        if ( dto.getPhone() != null ) {
            entity.setPhone( dto.getPhone() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getAddress() != null ) {
            entity.setAddress( dto.getAddress() );
        }
    }
}
