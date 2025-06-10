package com.empresa.service.impl;

import com.empresa.dto.request.BillDetailRequestDTO;
import com.empresa.dto.request.ProductRequestDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.dto.response.ProductResponseDTO;
import com.empresa.dto.exception.ResourceNotFoundException;
import com.empresa.dto.exception.InvalidDataException;
import com.empresa.mapper.ProductMapper;
import com.empresa.model.Product;
import com.empresa.repository.ProductRepository;
import com.empresa.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    @Transactional
    public void validateProductsStock(List<BillDetailRequestDTO> details) {
        details.forEach(detail -> {
            int availableStock = getProductStock(detail.getProductId());
            if (availableStock < detail.getQuantity()) {
                throw new InvalidDataException(String.format(
                    "Stock insuficiente para el producto %s. Disponible: %d, Requerido: %d",
                    detail.getProductId(), availableStock, detail.getQuantity()));
            }
        });
    }

    @Override
    @Transactional
    public void decreaseProductStock(String productId, int quantity) {
        if (quantity <= 0) {
            throw new InvalidDataException("La cantidad a disminuir debe ser positiva");
        }
        productRepository.updateStock(productId, -quantity);
    }

    @Override
    @Transactional
    public void increaseProductStock(String productId, int quantity) {
        if (quantity <= 0) {
            throw new InvalidDataException("La cantidad a aumentar debe ser positiva");
        }
        productRepository.updateStock(productId, quantity);
    }

    @Override
    @Transactional(readOnly = true)
    public int getProductStock(String productId) {
        return productRepository.findStockById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<ProductResponseDTO> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return buildPaginationResponse(productPage);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(String id, ProductRequestDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        
        productMapper.updateEntity(productDTO, existingProduct);
        Product updatedProduct = productRepository.save(existingProduct);
        
        return productMapper.toDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> searchProducts(String searchTerm) {
        return productRepository.searchByNameOrCategory(searchTerm).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getLowStockProducts(Integer minStock) {
        return productRepository.findLowStockProducts(minStock != null ? minStock : 10).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseDTO<ProductResponseDTO> searchProductsPaginated(String searchTerm, String category, Pageable pageable) {
        Page<Product> productPage = productRepository.findByFilters(searchTerm, category, pageable);
        return buildPaginationResponse(productPage);
    }

    private PaginationResponseDTO<ProductResponseDTO> buildPaginationResponse(Page<Product> productPage) {
        List<ProductResponseDTO> content = productPage.getContent()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        
        return PaginationResponseDTO.<ProductResponseDTO>builder()
                .content(content)
                .currentPage(productPage.getNumber())
                .totalItems(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .build();
    }
}