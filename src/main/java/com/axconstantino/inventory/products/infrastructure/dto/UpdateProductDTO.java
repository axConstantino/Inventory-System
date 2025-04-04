package com.axconstantino.inventory.products.infrastructure.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UpdateProductDTO {
    @PositiveOrZero(message = "Price must be zero or positive")
    private Double price;

    @Size(max = 30, message = "SKU must not exceed 30 characters")
    private String sku;

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;

    @Size(max = 20, message = "Unit of measure must not exceed 20 characters")
    private String unitOfMeasure;
}
