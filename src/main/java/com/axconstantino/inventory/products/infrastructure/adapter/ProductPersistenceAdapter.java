package com.axconstantino.inventory.products.infrastructure.adapter;

import com.axconstantino.inventory.products.domain.model.Product;
import com.axconstantino.inventory.products.domain.repository.ProductRepositoryPort;
import com.axconstantino.inventory.exception.ResourceNotFoundException;
import com.axconstantino.inventory.products.infrastructure.database.ProductEntity;
import com.axconstantino.inventory.products.infrastructure.database.ProductJpaRepository;
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
    public void save(Product product) {
        ProductEntity productEntity = mapper.toEntity(product);
        productJpaRepository.save(productEntity);
    }

    @Override
    public void update(Long productId, Product updateRequest) {
        ProductEntity product = productJpaRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));
        mapper.update(product, updateRequest);
        productJpaRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        if (!productJpaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        }
        productJpaRepository.deleteById(id);
    }

}
