package com.axconstantino.inventory.products.application.service;

import com.axconstantino.inventory.products.application.usecase.*;
import com.axconstantino.inventory.products.domain.model.Product;
import com.axconstantino.inventory.products.domain.model.ProductRepositoryPort;
import com.axconstantino.inventory.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public Product getProductBySku(String sku) {
        return productRepositoryPort.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product with SKU " + sku + " not found"));
    }

    @Override
    public void createProduct(Product newProduct) {
        productRepositoryPort.save(newProduct);
    }

    @Override
    public void updateProduct(Long productId, Product updateRequest) {
        productRepositoryPort.update(productId, updateRequest);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepositoryPort.delete(id);
    }

}
