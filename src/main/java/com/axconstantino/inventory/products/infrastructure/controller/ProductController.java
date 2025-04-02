package com.axconstantino.inventory.products.infrastructure.controller;

import com.axconstantino.inventory.products.application.service.ProductService;
import com.axconstantino.inventory.products.domain.model.Product;
import com.axconstantino.inventory.products.infrastructure.dto.ProductDTO;
import com.axconstantino.inventory.products.infrastructure.dto.UpdateProductDTO;
import com.axconstantino.inventory.products.infrastructure.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductDTO createRequest) {
        Product newProduct = mapper.toDomainFromDTO(createRequest);
        productService.createProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = mapper.toDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable String sku) {
        Product product = productService.getProductBySku(sku);
        ProductDTO productDTO = mapper.toDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductDTO>> getAllProductsByCategory(
            @PathVariable String category,
            @PageableDefault(size = 10, page = 0, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> products = productService.getAllProductsByCategory(category, pageable);
        Page<ProductDTO> productDTOs = products.map(mapper::toDTO);
        return ResponseEntity.ok(productDTOs);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateProductDTO updateRequest) {
        productService.updateProduct(productId, updateRequest);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
