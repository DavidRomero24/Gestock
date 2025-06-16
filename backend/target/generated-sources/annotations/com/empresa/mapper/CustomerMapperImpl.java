package com.empresa.mapper;

import com.empresa.dto.request.CustomerRequestDTO;
import com.empresa.dto.response.CustomerResponseDTO;
import com.empresa.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T18:15:37-0500",
    comments = "version: 1.6.1, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
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
    public CustomerResponseDTO toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();

        customerResponseDTO.setFullName( generateFullName( customer ) );
        customerResponseDTO.setAddress( customer.getAddress() );
        customerResponseDTO.setEmail( customer.getEmail() );
        customerResponseDTO.setId( customer.getId() );
        customerResponseDTO.setLastName1( customer.getLastName1() );
        customerResponseDTO.setLastName2( customer.getLastName2() );
        customerResponseDTO.setName1( customer.getName1() );
        customerResponseDTO.setName2( customer.getName2() );
        customerResponseDTO.setPhone( customer.getPhone() );

        return customerResponseDTO;
    }

    @Override
    public void updateEntity(CustomerRequestDTO dto, Customer customer) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName1() != null ) {
            customer.setName1( dto.getName1() );
        }
        if ( dto.getName2() != null ) {
            customer.setName2( dto.getName2() );
        }
        if ( dto.getLastName1() != null ) {
            customer.setLastName1( dto.getLastName1() );
        }
        if ( dto.getLastName2() != null ) {
            customer.setLastName2( dto.getLastName2() );
        }
        if ( dto.getPhone() != null ) {
            customer.setPhone( dto.getPhone() );
        }
        if ( dto.getEmail() != null ) {
            customer.setEmail( dto.getEmail() );
        }
        if ( dto.getAddress() != null ) {
            customer.setAddress( dto.getAddress() );
        }
    }
}
