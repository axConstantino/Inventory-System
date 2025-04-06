package com.axconstantino.inventory.inventory.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockLevelJpaRepository extends JpaRepository<StockLevelEntity, Long> {
    Optional<StockLevelEntity> findByProductId(Long productId);

    // Optional: Query to get quantity directly if needed elsewhere, avoids loading full entity sometimes
    @Query("SELECT sl.quantityOnHand FROM StockLevelEntity sl WHERE sl.product.id = :productId")
    Optional<Integer> findQuantityByProductId(@Param("productId") Long productId);
}
