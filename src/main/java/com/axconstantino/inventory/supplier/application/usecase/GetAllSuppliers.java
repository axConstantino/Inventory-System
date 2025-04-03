package com.axconstantino.inventory.supplier.application.usecase;

import com.axconstantino.inventory.supplier.domain.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllSuppliers {
    Page<Supplier> getAllSuppliers(Pageable pageable);
}
