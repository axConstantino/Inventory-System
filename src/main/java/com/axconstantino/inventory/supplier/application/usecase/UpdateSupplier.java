package com.axconstantino.inventory.supplier.application.usecase;


import com.axconstantino.inventory.supplier.infrastructure.dto.UpdateSupplierRequest;

public interface UpdateSupplier {
    void updateSupplier(Long id, UpdateSupplierRequest updateRequest);
}
