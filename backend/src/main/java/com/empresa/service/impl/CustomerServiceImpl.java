package com.empresa.service.impl;

import com.empresa.dto.request.CustomerRequestDTO;
import com.empresa.dto.response.CustomerResponseDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.dto.exception.DuplicateResourceException;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.mapper.CustomerMapper;
import com.empresa.model.Customer;
import com.empresa.repository.CustomerRepository;
import com.empresa.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
// import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerDTO) {
        validateUniqueCustomer(customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return buildPaginationResponse(customerPage);
    }

    @Override
    @Transactional
    public CustomerResponseDTO updateCustomer(String id, CustomerRequestDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        
        validateUpdateConstraints(id, customerDTO);
        customerMapper.updateEntity(customerDTO, existingCustomer);
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        
        return customerMapper.toDto(updatedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> searchCustomers(String searchTerm) {
        return customerRepository.searchByNameOrLastName(searchTerm).stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<CustomerResponseDTO> searchCustomersPaginated(String searchTerm, Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findBySearchTerm(searchTerm, pageable);
        return buildPaginationResponse(customerPage);
    }

    // Métodos auxiliares privados
    private void validateUniqueCustomer(CustomerRequestDTO customerDTO) {
        if (customerRepository.existsByPhone(customerDTO.getPhone())) {
            throw new DuplicateResourceException("El número de teléfono ya está registrado");
        }
        
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }
    }

    private void validateUpdateConstraints(String id, CustomerRequestDTO customerDTO) {
        customerRepository.findByPhone(customerDTO.getPhone())
            .ifPresent(customer -> {
                if (!customer.getId().equals(id)) {
                    throw new DuplicateResourceException("El número de teléfono ya está registrado por otro cliente");
                }
            });
        
        customerRepository.findByEmail(customerDTO.getEmail())
            .ifPresent(customer -> {
                if (!customer.getId().equals(id)) {
                    throw new DuplicateResourceException("El email ya está registrado por otro cliente");
                }
            });
    }

    private PaginationResponseDTO<CustomerResponseDTO> buildPaginationResponse(Page<Customer> customerPage) {
        List<CustomerResponseDTO> content = customerPage.getContent()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
        
        return PaginationResponseDTO.<CustomerResponseDTO>builder()
                .content(content)
                .currentPage(customerPage.getNumber())
                .totalItems(customerPage.getTotalElements())
                .totalPages(customerPage.getTotalPages())
                .build();
    }
}