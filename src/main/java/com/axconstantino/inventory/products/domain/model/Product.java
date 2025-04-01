package com.axconstantino.inventory.products.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class Product {
    private Long id;
    private String sku; // Stock Keeping Unit
    private String name;
    private String description;
    private String category;
    private String unitOfMeasure;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;
}
