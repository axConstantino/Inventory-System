package com.axconstantino.inventory.products.infrastructure.adapter;

import com.axconstantino.inventory.products.domain.model.Product;
import com.axconstantino.inventory.products.domain.model.ProductRepositoryPort;
import com.axconstantino.inventory.products.infrastructure.database.ProductEntity;
import com.axconstantino.inventory.products.infrastructure.database.ProductJpaRepository;
import com.axconstantino.inventory.products.infrastructure.dto.UpdateProductDTO;
import com.axconstantino.inventory.products.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductPersistenceAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper mapper;

    @Override
    public Optional<Product> findBySku(String sku) {
        ProductEntity product = productJpaRepository.findBySku(sku).orElse(null);
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDomain(product));
    }

    @Override
    public Page<Product> findByCategory(String category, Pageable pageable) {
        return productJpaRepository.findByCategory(category, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Product> findById(Long id) {
        ProductEntity product = productJpaRepository.findById(id).orElse(null);
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDomain(product));
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = mapper.toEntity(product);
        productEntity = productJpaRepository.save(productEntity);
        return mapper.toDomain(productEntity);
    }

    @Override
    public void update(Long productId, UpdateProductDTO updateRequest) {
        ProductEntity product = productJpaRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        mapper.updateFromDTO(product, updateRequest);
        product = productJpaRepository.save(product);
        return mapper.toDomain(product);
    }

    @Override
    public void delete(Long id) {
        if (productJpaRepository.existsById(id)) {
            productJpaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
