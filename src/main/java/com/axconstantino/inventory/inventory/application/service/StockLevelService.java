package com.axconstantino.inventory.inventory.application.service;

import com.axconstantino.inventory.exception.ResourceNotFoundException;
import com.axconstantino.inventory.inventory.application.usecase.AdjustStockLevel;
import com.axconstantino.inventory.inventory.application.usecase.GetStockLevelByProductId;
import com.axconstantino.inventory.inventory.domain.model.StockLevel;
import com.axconstantino.inventory.inventory.domain.repository.StockLevelRepositoryPort;
import com.axconstantino.inventory.products.domain.model.Product;
import com.axconstantino.inventory.products.domain.repository.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockLevelService implements GetStockLevelByProductId, AdjustStockLevel {
    private final StockLevelRepositoryPort stockLevelRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    @Transactional
    public StockLevel adjustStock(Long productId, int quantityChange, String adjustmentReason) throws Exception {
        Product product = productRepositoryPort.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        StockLevel stockLevel = stockLevelRepositoryPort.findByProductId(productId)
                .orElseGet(() -> StockLevel.builder()
                        .product(product)
                        .quantityOnHand(0)
                        .build()
                );

        int currentQuantity = stockLevel.getQuantityOnHand();
        int newQuantity = currentQuantity + quantityChange;

        if (newQuantity < 0) {
            throw new RuntimeException("Insufficient stock for product ID: " + productId + ". Required: " + Math.abs(quantityChange) + ", available: " + currentQuantity);
        }

        stockLevel.setQuantityOnHand(newQuantity);
        return stockLevelRepositoryPort.save(stockLevel);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockLevel> getStockLevelByProductId(Long productId) {
        return stockLevelRepositoryPort.findByProductId(productId);
    }
}
