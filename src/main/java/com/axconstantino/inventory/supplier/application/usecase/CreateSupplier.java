package com.axconstantino.inventory.supplier.application.usecase;

import com.axconstantino.inventory.supplier.domain.model.Supplier;

public interface CreateSupplier {
    Supplier createSupplier(Supplier newSupplier);
}
