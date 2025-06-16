package com.empresa.mapper;

import com.empresa.dto.request.StaffRequestDTO;
import com.empresa.dto.response.StaffResponseDTO;
import com.empresa.model.Staff;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-16T16:56:23-0500",
    comments = "version: 1.6.1, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
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
        staff.setNumberPhone( dto.getNumberPhone() );
        if ( dto.getSalary() != null ) {
            staff.setSalary( BigDecimal.valueOf( dto.getSalary() ) );
        }
        staff.setTypeStaff( dto.getTypeStaff() );
        staff.setHireDate( dto.getHireDate() );

        setTypeStaffUpperCase( staff );

        return staff;
    }

    @Override
    public StaffResponseDTO toDto(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        StaffResponseDTO.StaffResponseDTOBuilder staffResponseDTO = StaffResponseDTO.builder();

        staffResponseDTO.idStaff( staff.getIdStaff() );
        staffResponseDTO.name1( staff.getName1() );
        staffResponseDTO.name2( staff.getName2() );
        staffResponseDTO.lastName( staff.getLastName() );
        staffResponseDTO.lastName2( staff.getLastName2() );
        staffResponseDTO.dateBirth( staff.getDateBirth() );
        staffResponseDTO.email( staff.getEmail() );
        staffResponseDTO.numberPhone( staff.getNumberPhone() );
        if ( staff.getSalary() != null ) {
            staffResponseDTO.salary( staff.getSalary().doubleValue() );
        }
        staffResponseDTO.typeStaff( staff.getTypeStaff() );
        staffResponseDTO.status( staff.getStatus() );
        staffResponseDTO.hireDate( staff.getHireDate() );

        staffResponseDTO.fullName( generateFullName(staff) );

        return staffResponseDTO.build();
    }

    @Override
    public void updateEntity(StaffRequestDTO dto, Staff staff) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getIdStaff() != null ) {
            staff.setIdStaff( dto.getIdStaff() );
        }
        if ( dto.getName1() != null ) {
            staff.setName1( dto.getName1() );
        }
        if ( dto.getName2() != null ) {
            staff.setName2( dto.getName2() );
        }
        if ( dto.getLastName() != null ) {
            staff.setLastName( dto.getLastName() );
        }
        if ( dto.getLastName2() != null ) {
            staff.setLastName2( dto.getLastName2() );
        }
        if ( dto.getDateBirth() != null ) {
            staff.setDateBirth( dto.getDateBirth() );
        }
        if ( dto.getEmail() != null ) {
            staff.setEmail( dto.getEmail() );
        }
        if ( dto.getNumberPhone() != null ) {
            staff.setNumberPhone( dto.getNumberPhone() );
        }
        if ( dto.getSalary() != null ) {
            staff.setSalary( BigDecimal.valueOf( dto.getSalary() ) );
        }
        if ( dto.getTypeStaff() != null ) {
            staff.setTypeStaff( dto.getTypeStaff() );
        }
        if ( dto.getHireDate() != null ) {
            staff.setHireDate( dto.getHireDate() );
        }

        setTypeStaffUpperCase( staff );
    }
}
