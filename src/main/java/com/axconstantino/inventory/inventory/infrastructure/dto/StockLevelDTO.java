package com.axconstantino.inventory.inventory.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockLevelDTO {
    private Long productId;
    private String productSku; // Include identifying product info
    private String productName; // Include identifying product info
    private Integer quantityOnHand;
}
