package com.empresa.controller;

import com.empresa.dto.request.ProductRequestDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.dto.response.ProductResponseDTO;
import com.empresa.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) {
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<PaginationResponseDTO<ProductResponseDTO>> getAllProducts(Pageable pageable) {
        PaginationResponseDTO<ProductResponseDTO> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductRequestDTO productDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(@RequestParam String searchTerm) {
        List<ProductResponseDTO> products = productService.searchProducts(searchTerm);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable String category) {
        List<ProductResponseDTO> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseDTO>> getLowStockProducts(
            @RequestParam(required = false, defaultValue = "10") Integer minStock) {
        List<ProductResponseDTO> products = productService.getLowStockProducts(minStock);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/paginated")
    public ResponseEntity<PaginationResponseDTO<ProductResponseDTO>> searchProductsPaginated(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String category,
            Pageable pageable) {
        PaginationResponseDTO<ProductResponseDTO> products = 
            productService.searchProductsPaginated(searchTerm, category, pageable);
        return ResponseEntity.ok(products);
    }
}