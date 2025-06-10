package com.empresa.service;

import com.empresa.dto.request.StaffRequestDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.dto.response.StaffResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {
    StaffResponseDTO createStaff(StaffRequestDTO staffDTO);
    StaffResponseDTO getStaffById(String id);
    PaginationResponseDTO<StaffResponseDTO> getAllStaff(Pageable pageable);
    StaffResponseDTO updateStaff(String id, StaffRequestDTO staffDTO);
    void changeStaffStatus(String id, String status);
    void deleteStaff(String id);
    List<StaffResponseDTO> searchStaff(String searchTerm, String type);
    PaginationResponseDTO<StaffResponseDTO> getStaffByType(String typeStaff, Pageable pageable);
    
}