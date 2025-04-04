package com.axconstantino.inventory.supplier.infrastructure.adapter;

import com.axconstantino.inventory.supplier.domain.model.Supplier;
import com.axconstantino.inventory.supplier.domain.model.SupplierRepositoryPort;
import com.axconstantino.inventory.supplier.infrastructure.database.SupplierEntity;
import com.axconstantino.inventory.supplier.infrastructure.database.SupplierJpaRepository;
import com.axconstantino.inventory.supplier.infrastructure.dto.UpdateSupplierRequest;
import com.axconstantino.inventory.supplier.infrastructure.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SupplierPersistenceAdapter implements SupplierRepositoryPort {
    private final SupplierJpaRepository supplierJpaRepository;
    private final SupplierMapper mapper;

    @Override
    public Optional<Supplier> findById(Long id) {
        return supplierJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Supplier> findByName(String name) {
        return supplierJpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        return supplierJpaRepository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Supplier save(Supplier supplier) {
        SupplierEntity entity = mapper.toEntity(supplier);
        return mapper.toDomain(supplierJpaRepository.save(entity));
    }

    @Override
    public void update(Long id, UpdateSupplierRequest updateRequest) {
        SupplierEntity existingEntity = supplierJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier with id " + id + " does not exist"));

        mapper.updateSupplierFromDTO(updateRequest, existingEntity);
        supplierJpaRepository.save(existingEntity);
    }

    @Override
    public void delete(Long id) {
        if (supplierJpaRepository.existsById(id)) {
            supplierJpaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Supplier with id " + id + " does not exist");
        }
    }
}
