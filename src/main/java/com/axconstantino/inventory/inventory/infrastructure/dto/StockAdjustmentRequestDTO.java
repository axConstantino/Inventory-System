package com.axconstantino.inventory.inventory.infrastructure.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockAdjustmentRequestDTO {
    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @NotNull(message = "Quantity change cannot be null")
    private Integer quantityChange;

    @NotEmpty(message = "Adjustment reason cannot be empty")
    private String adjustmentReason;
}
