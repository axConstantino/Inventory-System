package com.axconstantino.inventory.products.application.usecase;

import com.axconstantino.inventory.products.domain.model.Product;

public interface GetProductById {
    Product getProductById(Long id);
}
