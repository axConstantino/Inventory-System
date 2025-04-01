package com.axconstantino.inventory.products.application.usecase;

import com.axconstantino.inventory.products.domain.model.Product;

public interface CreateProduct {
    void createProduct(Product newProduct);
}
