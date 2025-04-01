package com.axconstantino.inventory.products.infrastructure.mapper;

import com.axconstantino.inventory.products.domain.model.Product;
import com.axconstantino.inventory.products.infrastructure.database.ProductEntity;
import com.axconstantino.inventory.products.infrastructure.dto.ProductDTO;
import com.axconstantino.inventory.products.infrastructure.dto.UpdateProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toDomain(ProductEntity entity);
    Product toDomainFromDTO(ProductDTO request);
    ProductEntity toEntity(Product product);
    ProductDTO toDTO(Product product);
    void updateFromDTO(@MappingTarget ProductEntity product, UpdateProductDTO updateRequest);
}
