package com.empresa.mapper;

import com.empresa.dto.request.StaffRequestDTO;
import com.empresa.dto.response.StaffResponseDTO;
import com.empresa.model.Staff;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T10:20:56-0500",
    comments = "version: 1.6.1, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public Staff toEntity(StaffRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setDateBirth( dto.getDateBirth() );
        staff.setEmail( dto.getEmail() );
        staff.setHireDate( dto.getHireDate() );
        staff.setIdStaff( dto.getIdStaff() );
        staff.setLastName( dto.getLastName() );
        staff.setLastName2( dto.getLastName2() );
        staff.setName1( dto.getName1() );
        staff.setName2( dto.getName2() );
        staff.setNumberPhone( dto.getNumberPhone() );
        if ( dto.getSalary() != null ) {
            staff.setSalary( BigDecimal.valueOf( dto.getSalary() ) );
        }
        staff.setTypeStaff( dto.getTypeStaff() );

        setTypeStaffUpperCase( staff );

        return staff;
    }

    @Override
    public StaffResponseDTO toDto(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        StaffResponseDTO.StaffResponseDTOBuilder staffResponseDTO = StaffResponseDTO.builder();

        staffResponseDTO.dateBirth( staff.getDateBirth() );
        staffResponseDTO.email( staff.getEmail() );
        staffResponseDTO.hireDate( staff.getHireDate() );
        staffResponseDTO.idStaff( staff.getIdStaff() );
        staffResponseDTO.lastName( staff.getLastName() );
        staffResponseDTO.lastName2( staff.getLastName2() );
        staffResponseDTO.name1( staff.getName1() );
        staffResponseDTO.name2( staff.getName2() );
        staffResponseDTO.numberPhone( staff.getNumberPhone() );
        if ( staff.getSalary() != null ) {
            staffResponseDTO.salary( staff.getSalary().doubleValue() );
        }
        staffResponseDTO.status( staff.getStatus() );
        staffResponseDTO.typeStaff( staff.getTypeStaff() );

        staffResponseDTO.fullName( generateFullName(staff) );

        return staffResponseDTO.build();
    }

    @Override
    public void updateEntity(StaffRequestDTO dto, Staff staff) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getDateBirth() != null ) {
            staff.setDateBirth( dto.getDateBirth() );
        }
        if ( dto.getEmail() != null ) {
            staff.setEmail( dto.getEmail() );
        }
        if ( dto.getHireDate() != null ) {
            staff.setHireDate( dto.getHireDate() );
        }
        if ( dto.getIdStaff() != null ) {
            staff.setIdStaff( dto.getIdStaff() );
        }
        if ( dto.getLastName() != null ) {
            staff.setLastName( dto.getLastName() );
        }
        if ( dto.getLastName2() != null ) {
            staff.setLastName2( dto.getLastName2() );
        }
        if ( dto.getName1() != null ) {
            staff.setName1( dto.getName1() );
        }
        if ( dto.getName2() != null ) {
            staff.setName2( dto.getName2() );
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

        setTypeStaffUpperCase( staff );
    }
}
