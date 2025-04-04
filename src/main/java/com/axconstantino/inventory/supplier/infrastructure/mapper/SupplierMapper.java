package com.axconstantino.inventory.supplier.infrastructure.mapper;

import com.axconstantino.inventory.supplier.domain.model.Supplier;
import com.axconstantino.inventory.supplier.infrastructure.database.SupplierEntity;
import com.axconstantino.inventory.supplier.infrastructure.dto.SupplierDTO;
import com.axconstantino.inventory.supplier.infrastructure.dto.UpdateSupplierRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toDomain(SupplierEntity supplierEntity);
    Supplier toDomain(SupplierDTO supplierDTO);
    Supplier toDomain(UpdateSupplierRequest updateSupplierRequest);
    SupplierEntity toEntity(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget SupplierEntity supplier, Supplier updateRequest);
}
