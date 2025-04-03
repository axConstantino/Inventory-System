package com.axconstantino.inventory.supplier.infrastructure.mapper;

import com.axconstantino.inventory.supplier.domain.model.Supplier;
import com.axconstantino.inventory.supplier.infrastructure.database.SupplierEntity;
import com.axconstantino.inventory.supplier.infrastructure.dto.SupplierDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toDomain(SupplierEntity supplierEntity);
    Supplier toDomain(SupplierDTO supplierDTO);
    SupplierEntity toEntity(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Supplier supplier, @MappingTarget SupplierEntity supplierEntity);
}
