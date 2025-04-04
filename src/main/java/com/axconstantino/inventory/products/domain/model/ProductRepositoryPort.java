package com.axconstantino.inventory.products.domain.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepositoryPort {
    Optional<Product> findBySku(String sku);
    Page<Product> findByCategory(String category, Pageable pageable);
    Optional<Product> findById(Long id);
    void save(Product product);
    void update(Long productId, Product updateRequest);
    void delete(Long id);
}
