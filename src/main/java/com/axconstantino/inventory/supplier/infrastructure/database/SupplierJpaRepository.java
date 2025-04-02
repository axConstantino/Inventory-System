package com.axconstantino.inventory.supplier.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierJpaRepository extends JpaRepository<SupplierEntity, Long> {
    Optional<SupplierEntity> findByName(String name);
}
