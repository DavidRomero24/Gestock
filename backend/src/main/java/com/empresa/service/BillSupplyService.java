package com.empresa.service;

import com.empresa.dto.request.BillSupplyRequestDTO;
import com.empresa.dto.response.BillSupplyResponseDTO;
import java.util.List;

public interface BillSupplyService {
    BillSupplyResponseDTO create(BillSupplyRequestDTO billSupplyDTO);
    BillSupplyResponseDTO getById(Integer id);
    List<BillSupplyResponseDTO> getAll();
    void delete(Integer id);
}