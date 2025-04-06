package com.axconstantino.inventory.inventory.infrastructure.adapter;

import com.axconstantino.inventory.inventory.domain.model.StockLevel;
import com.axconstantino.inventory.inventory.domain.repository.StockLevelRepositoryPort;
import com.axconstantino.inventory.inventory.infrastructure.database.StockLevelEntity;
import com.axconstantino.inventory.inventory.infrastructure.database.StockLevelJpaRepository;
import com.axconstantino.inventory.inventory.infrastructure.mapper.StockLevelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StockLevelPersistenceAdapter implements StockLevelRepositoryPort {
    private final StockLevelJpaRepository jpaRepository;
    private final StockLevelMapper mapper;

    @Override
    public Optional<StockLevel> findByProductId(Long productId) {
        return jpaRepository.findByProductId(productId)
                .map(mapper::toDomain);
    }

    @Override
    public StockLevel save(StockLevel stockLevel) {
        StockLevelEntity entity = mapper.toEntity(stockLevel);
        StockLevelEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
