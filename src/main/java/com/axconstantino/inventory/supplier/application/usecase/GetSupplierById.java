package com.axconstantino.inventory.supplier.application.usecase;

import com.axconstantino.inventory.supplier.domain.model.Supplier;

public interface GetSupplierById {
    Supplier getSupplierById(Long id);
}
