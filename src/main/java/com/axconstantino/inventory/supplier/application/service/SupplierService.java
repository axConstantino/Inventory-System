package com.axconstantino.inventory.supplier.application.service;

import com.axconstantino.inventory.exception.ResourceNotFoundException;
import com.axconstantino.inventory.supplier.application.usecase.*;
import com.axconstantino.inventory.supplier.domain.model.Supplier;
import com.axconstantino.inventory.supplier.domain.model.SupplierRepositoryPort;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SupplierService implements GetSupplierById, GetSupplierByName, GetAllSuppliers, CreateSupplier, UpdateSupplier, DeleteSupplier {
    private final SupplierRepositoryPort supplierRepositoryPort;

    @Override
    public Supplier createSupplier(Supplier newSupplier) {
        return supplierRepositoryPort.save(newSupplier);
    }

    @Override
    public Page<Supplier> getAllSuppliers(Pageable pageable) {
        return supplierRepositoryPort.findAll(pageable);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with id " + id + " does not exist"));
    }

    @Override
    public Supplier getSupplierByName(String name) {
        return supplierRepositoryPort.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with name " + name + " does not exist"));
    }

    @Override
    public void updateSupplier(Long id, Supplier updateRequest) {
        if (supplierRepositoryPort.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Supplier with id " + id + " does not exist");
        }
        supplierRepositoryPort.update(id, updateRequest);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepositoryPort.delete(id);
    }
}
