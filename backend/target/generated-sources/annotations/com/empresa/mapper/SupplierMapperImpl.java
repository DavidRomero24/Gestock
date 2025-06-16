package com.empresa.mapper;

import com.empresa.dto.request.SupplierRequestDTO;
import com.empresa.dto.response.SupplierResponseDTO;
import com.empresa.model.City;
import com.empresa.model.Department;
import com.empresa.model.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T18:15:38-0500",
    comments = "version: 1.6.1, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public Supplier toEntity(SupplierRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setId( dto.getId() );
        supplier.setName1( dto.getName1() );
        supplier.setName2( dto.getName2() );
        supplier.setLastName1( dto.getLastName1() );
        supplier.setLastName2( dto.getLastName2() );
        supplier.setPhone( dto.getPhone() );
        supplier.setEmail( dto.getEmail() );
        supplier.setCompany( dto.getCompany() );

        return supplier;
    }

    @Override
    public SupplierResponseDTO toDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();

        supplierResponseDTO.setCity( supplierCityNameCity( supplier ) );
        supplierResponseDTO.setDepartment( supplierCityDepartmentName( supplier ) );
        supplierResponseDTO.setFullName( generateFullName( supplier ) );
        supplierResponseDTO.setCompany( supplier.getCompany() );
        supplierResponseDTO.setEmail( supplier.getEmail() );
        supplierResponseDTO.setId( supplier.getId() );
        supplierResponseDTO.setLastName1( supplier.getLastName1() );
        supplierResponseDTO.setLastName2( supplier.getLastName2() );
        supplierResponseDTO.setName1( supplier.getName1() );
        supplierResponseDTO.setName2( supplier.getName2() );
        supplierResponseDTO.setPhone( supplier.getPhone() );

        return supplierResponseDTO;
    }

    private String supplierCityNameCity(Supplier supplier) {
        City city = supplier.getCity();
        if ( city == null ) {
            return null;
        }
        return city.getNameCity();
    }

    private String supplierCityDepartmentName(Supplier supplier) {
        City city = supplier.getCity();
        if ( city == null ) {
            return null;
        }
        Department department = city.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department.getName();
    }
}
