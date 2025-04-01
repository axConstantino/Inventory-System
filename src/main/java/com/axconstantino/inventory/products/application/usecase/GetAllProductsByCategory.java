package com.axconstantino.inventory.products.application.usecase;

import com.axconstantino.inventory.products.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GetAllProductsByCategory {
    Page<Product> getAllProductsByCategory(String category, Pageable pageable);
}
