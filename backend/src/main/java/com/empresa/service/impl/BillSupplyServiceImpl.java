package com.empresa.service.impl;

import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.dto.request.*;
import com.empresa.dto.response.*;
import com.empresa.mapper.BillSupplyMapper;
import com.empresa.model.*;
import com.empresa.repository.*;
import com.empresa.service.BillSupplyService;
import com.empresa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillSupplyServiceImpl implements BillSupplyService {

    private final BillSupplyRepository billSupplyRepository;
    private final BillSupplyDetailRepository detailRepository;
    private final SupplierRepository supplierRepository;
    private final ProductService productService;
    private final BillSupplyMapper mapper;

    @Override
    @Transactional
    public BillSupplyResponseDTO create(BillSupplyRequestDTO billSupplyDTO) {
        // Validar proveedor
        Supplier supplier = supplierRepository.findById(billSupplyDTO.getSupplierId())
            .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        // Crear factura
        BillSupply billSupply = mapper.toEntity(billSupplyDTO);
        billSupply.setSupplier(supplier);
        billSupply = billSupplyRepository.save(billSupply);
        
        // Procesar detalles
        BigDecimal total = BigDecimal.ZERO;
        for (BillSupplyDetailRequestDTO detailDTO : billSupplyDTO.getDetails()) {
            BillSupplyDetail detail = new BillSupplyDetail();
            BillSupplyDetail.BillSupplyDetailId id = new BillSupplyDetail.BillSupplyDetailId(
                billSupply.getIdBillSupply(),
                detailDTO.getProductId()
            );
            
            Product product = productService.getProductEntityById(detailDTO.getProductId());
            
            detail.setId(id);
            detail.setProduct(product);
            detail.setBillSupply(billSupply);
            detail.setQuantityProduct(detailDTO.getQuantityProduct());
            detail.setSubTotal(detailDTO.getUnitPrice().multiply(
                BigDecimal.valueOf(detailDTO.getQuantityProduct())));
            
            detailRepository.save(detail);
            total = total.add(detail.getSubTotal());
            
            // Aumentar stock del producto
            productService.increaseProductStock(detailDTO.getProductId(), detailDTO.getQuantityProduct());
        }
        
        // Actualizar total
        billSupply.setTotal(total);
        billSupplyRepository.save(billSupply);
        
        return mapper.toDto(billSupply);
    }

    @Override
    @Transactional(readOnly = true)
    public BillSupplyResponseDTO getById(Integer id) {
        BillSupply billSupply = billSupplyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Factura de proveedor no encontrada"));
        return mapper.toDto(billSupply);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BillSupplyResponseDTO> getAll() {
        return billSupplyRepository.findAll().stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        BillSupply billSupply = billSupplyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Factura de proveedor no encontrada"));
        
        // Revertir stock
        billSupply.getDetails().forEach(detail -> {
            productService.decreaseProductStock(
                detail.getProduct().getIdProduct(), 
                detail.getQuantityProduct()
            );
        });
        
        billSupplyRepository.delete(billSupply);
    }
}