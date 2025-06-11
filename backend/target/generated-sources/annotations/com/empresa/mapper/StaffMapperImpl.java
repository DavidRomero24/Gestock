package com.empresa.mapper;

import com.empresa.dto.request.StaffRequestDTO;
import com.empresa.dto.response.StaffResponseDTO;
import com.empresa.model.Staff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T01:00:58-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public Staff toEntity(StaffRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setIdStaff( dto.getIdStaff() );
        staff.setName1( dto.getName1() );
        staff.setName2( dto.getName2() );
        staff.setLastName( dto.getLastName() );
        staff.setLastName2( dto.getLastName2() );
        staff.setDateBirth( dto.getDateBirth() );
        staff.setEmail( dto.getEmail() );
        staff.setAddress( dto.getAddress() );
        staff.setNumberPhone( dto.getNumberPhone() );
        staff.setSalary( dto.getSalary() );
        staff.setTypeStaff( dto.getTypeStaff() );

        setTypeStaffUpperCase( staff );

        return staff;
    }

    @Override
    public StaffResponseDTO toDto(Staff entity) {
        if ( entity == null ) {
            return null;
        }

        StaffResponseDTO.StaffResponseDTOBuilder staffResponseDTO = StaffResponseDTO.builder();

        staffResponseDTO.address( entity.getAddress() );
        staffResponseDTO.dateBirth( entity.getDateBirth() );
        staffResponseDTO.email( entity.getEmail() );
        staffResponseDTO.idStaff( entity.getIdStaff() );
        staffResponseDTO.lastName( entity.getLastName() );
        staffResponseDTO.lastName2( entity.getLastName2() );
        staffResponseDTO.name1( entity.getName1() );
        staffResponseDTO.name2( entity.getName2() );
        staffResponseDTO.numberPhone( entity.getNumberPhone() );
        staffResponseDTO.salary( entity.getSalary() );
        staffResponseDTO.status( entity.getStatus() );
        staffResponseDTO.typeStaff( entity.getTypeStaff() );

        staffResponseDTO.fullName( generateFullName(entity) );
        staffResponseDTO.age( calculateAge(entity.getDateBirth()) );

        return staffResponseDTO.build();
    }

    @Override
    public void updateEntity(StaffRequestDTO dto, Staff entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getIdStaff() != null ) {
            entity.setIdStaff( dto.getIdStaff() );
        }
        if ( dto.getName1() != null ) {
            entity.setName1( dto.getName1() );
        }
        if ( dto.getName2() != null ) {
            entity.setName2( dto.getName2() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
        }
        if ( dto.getLastName2() != null ) {
            entity.setLastName2( dto.getLastName2() );
        }
        if ( dto.getDateBirth() != null ) {
            entity.setDateBirth( dto.getDateBirth() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getAddress() != null ) {
            entity.setAddress( dto.getAddress() );
        }
        if ( dto.getNumberPhone() != null ) {
            entity.setNumberPhone( dto.getNumberPhone() );
        }
        if ( dto.getSalary() != null ) {
            entity.setSalary( dto.getSalary() );
        }
        if ( dto.getTypeStaff() != null ) {
            entity.setTypeStaff( dto.getTypeStaff() );
        }

        setTypeStaffUpperCase( entity );
    }
}
