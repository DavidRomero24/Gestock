package com.empresa.service;

import com.empresa.dto.request.ProductRequestDTO;
import com.empresa.dto.request.BillDetailRequestDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.dto.response.ProductResponseDTO;
import com.empresa.model.Product;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    // Métodos existentes...
    ProductResponseDTO createProduct(ProductRequestDTO productDTO);
    ProductResponseDTO getProductById(String id);
    PaginationResponseDTO<ProductResponseDTO> getAllProducts(Pageable pageable);
    ProductResponseDTO updateProduct(String id, ProductRequestDTO productDTO);
    void deleteProduct(String id);
    List<ProductResponseDTO> searchProducts(String searchTerm);
    List<ProductResponseDTO> getProductsByCategory(String category);
    List<ProductResponseDTO> getLowStockProducts(Integer minStock);
    PaginationResponseDTO<ProductResponseDTO> searchProductsPaginated(String searchTerm, String category, Pageable pageable);
    
    // Nuevos métodos para gestión de stock
    void validateProductsStock(List<BillDetailRequestDTO> details);
    void decreaseProductStock(String productId, int quantity);
    void increaseProductStock(String productId, int quantity);
    int getProductStock(String productId);
    Product getProductEntityById(String productId);
}