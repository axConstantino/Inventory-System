package com.axconstantino.inventory.inventory.domain.repository;

import com.axconstantino.inventory.inventory.domain.model.StockLevel;

import java.util.Optional;

public interface StockLevelRepositoryPort {
    /**
     * Finds a stock level by product ID.
     *
     * @param productId the ID of the product
     * @return an Optional containing the stock level if found, or empty if not found
     */
    Optional<StockLevel> findByProductId(Long productId);

    /**
     * Saves or Updates a StockLevel record. If the StockLevel is new (ID is null
     * or doesn't exist), it will be created. If it exists, it will be updated.
     *
     * @param stockLevel The StockLevel object to save or update.
     * @return The saved or updated StockLevel object, potentially with generated ID or updated timestamp.
     *
     */
    StockLevel save(StockLevel stockLevel);
}
