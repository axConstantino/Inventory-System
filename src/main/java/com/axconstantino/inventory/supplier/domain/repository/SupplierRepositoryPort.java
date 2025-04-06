package com.axconstantino.inventory.supplier.domain.repository;

import com.axconstantino.inventory.supplier.domain.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SupplierRepositoryPort {
    Optional<Supplier> findById(Long id);
    Optional<Supplier> findByName(String name);
    Page<Supplier> findAll(Pageable pageable);
    Supplier save(Supplier supplier);
    void update(Long id, Supplier updateRequest);
    void delete(Long id);
}
