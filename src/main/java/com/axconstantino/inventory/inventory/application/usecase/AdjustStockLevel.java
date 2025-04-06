package com.axconstantino.inventory.inventory.application.usecase;

import com.axconstantino.inventory.inventory.domain.model.StockLevel;
import com.axconstantino.inventory.exception.*;
public interface AdjustStockLevel {
    /**
     * Adjusts the stock level for a given product by a specified quantity change.
     * Handles both increments and decrements. Throws exception if adjustment results in negative stock
     * unless negative stock is explicitly allowed by business rules.
     * Creates initial stock record if none exists for the product (starting from 0 + adjustment).
     *
     * @param productId The ID of the product to adjust stock for.
     * @param quantityChange The amount to change the quantity by (positive for increase, negative for decrease).
     * @param adjustmentReason A description or reason for the adjustment (for auditing).
     * @return The updated StockLevel.
     * @throws ResourceNotFoundException if the product ID does not exist.
     * @throws InsufficientStockException if a decrement results in quantity below zero (and negative stock is disallowed).
     */
    StockLevel adjustStock(Long productId, int quantityChange, String adjustmentReason) throws Exception; // Specify concrete exceptions later
}
