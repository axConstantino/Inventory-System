package com.axconstantino.inventory.purchaseorder.domain.repository;

import com.axconstantino.inventory.purchaseorder.domain.model.PurchaseOrder;
import com.axconstantino.inventory.purchaseorder.domain.model.enums.POStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PurchaseOrderRepositoryPort {
    PurchaseOrder save(PurchaseOrder purchaseOrder);

    Optional<PurchaseOrder> findById(Long id);

    Optional<PurchaseOrder> findByPoNumber(String poNumber);

    Page<PurchaseOrder> findAll(Pageable pageable);

    Page<PurchaseOrder> findBySupplierId(Long supplierId, Pageable pageable);

    Page<PurchaseOrder> findByStatus(POStatus status, Pageable pageable);

    void deleteById(Long id);
}
