package com.axconstantino.inventory.products.application.service;

import com.axconstantino.inventory.products.application.usecase.*;
import com.axconstantino.inventory.products.domain.model.Product;
import com.axconstantino.inventory.products.domain.model.ProductRepositoryPort;
import com.axconstantino.inventory.products.infrastructure.dto.UpdateProductDTO;
import com.axconstantino.inventory.products.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class ProductService implements GetAllProductsByCategory, GetProductById, GetProductBySku, CreateProduct, UpdateProduct, DeleteProduct {
    private final ProductRepositoryPort productRepositoryPort;
    private final ProductMapper productMapper;

    @Override
    public Page<Product> getAllProductsByCategory(String category, Pageable pageable) {
        return productRepositoryPort.findByCategory(category, pageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product getProductBySku(String sku) {
        return productRepositoryPort.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void createProduct(Product newProduct) {
        productRepositoryPort.save(newProduct);
    }

    @Override
    public void updateProduct(Long productId, UpdateProductDTO updateRequest) {
        productRepositoryPort.update(productId, updateRequest);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepositoryPort.delete(id);
    }

}
