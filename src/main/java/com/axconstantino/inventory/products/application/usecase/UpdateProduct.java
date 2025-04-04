package com.axconstantino.inventory.products.application.usecase;

import com.axconstantino.inventory.products.domain.model.Product;

public interface UpdateProduct {
    void updateProduct(Long productId, Product updateRequest);
}
