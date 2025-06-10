package com.empresa.service;

import com.empresa.dto.request.CustomerRequestDTO;
import com.empresa.dto.response.CustomerResponseDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO customerDTO);
    CustomerResponseDTO getCustomerById(String id);
    PaginationResponseDTO<CustomerResponseDTO> getAllCustomers(Pageable pageable);
    CustomerResponseDTO updateCustomer(String id, CustomerRequestDTO customerDTO);
    void deleteCustomer(String id);
    List<CustomerResponseDTO> searchCustomers(String searchTerm);
    PaginationResponseDTO<CustomerResponseDTO> searchCustomersPaginated(String searchTerm, Pageable pageable);
}