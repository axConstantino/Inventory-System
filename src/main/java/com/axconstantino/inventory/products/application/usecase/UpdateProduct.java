package com.axconstantino.inventory.products.application.usecase;

import com.axconstantino.inventory.products.infrastructure.dto.UpdateProductDTO;

public interface UpdateProduct {
    void updateProduct(Long productId, UpdateProductDTO updateRequest);
}
