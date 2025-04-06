package com.axconstantino.inventory.inventory.domain.model;

import com.axconstantino.inventory.products.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class StockLevel {
    private Long id;
    private Product product;
    private Integer quantityOnHand;
    private LocalDateTime lastUpdatedAt;
}
