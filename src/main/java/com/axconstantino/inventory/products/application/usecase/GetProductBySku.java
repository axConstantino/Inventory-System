package com.axconstantino.inventory.products.application.usecase;

import com.axconstantino.inventory.products.domain.model.Product;

public interface GetProductBySku {
    Product getProductBySku(String sku);
}
