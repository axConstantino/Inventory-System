package com.axconstantino.inventory.inventory.application.usecase;

import com.axconstantino.inventory.inventory.domain.model.StockLevel;

import java.util.Optional;

public interface GetStockLevelByProductId {
    /**
     * Retrieves the current stock for a specific product.
     *
     * @param productId the ID of the product
     * @return An Optional containing the StockLevel if found, otherwise empty.
     */
    Optional<StockLevel> getStockLevelByProductId(Long productId);
}
