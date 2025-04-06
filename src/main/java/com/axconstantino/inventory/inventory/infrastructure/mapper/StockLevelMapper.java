package com.axconstantino.inventory.inventory.infrastructure.mapper;

import com.axconstantino.inventory.inventory.domain.model.StockLevel;
import com.axconstantino.inventory.inventory.infrastructure.database.StockLevelEntity;
import com.axconstantino.inventory.inventory.infrastructure.dto.StockLevelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Assuming you have product mapper injectable if needed
public interface StockLevelMapper {

    // Map Entity to Domain
    // Note: Mapping ProductEntity to Product domain model requires ProductMapper or specific mapping here
    @Mapping(source = "product", target = "product") // Assumes ProductMapper handles ProductEntity -> Product
    StockLevel toDomain(StockLevelEntity entity);

    // Map Domain to Entity
    @Mapping(source = "product", target = "product") // Assumes ProductMapper handles Product -> ProductEntity
    StockLevelEntity toEntity(StockLevel domain);

    // Map Domain to DTO
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.sku", target = "productSku")
    @Mapping(source = "product.name", target = "productName")
    StockLevelDTO toDTO(StockLevel domain);

    // We might not need direct DTO -> Domain mapping if requests are handled differently (like adjustment)
}
