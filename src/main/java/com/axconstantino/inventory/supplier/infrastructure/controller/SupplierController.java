package com.axconstantino.inventory.supplier.infrastructure.controller;

import com.axconstantino.inventory.supplier.application.usecase.*;
import com.axconstantino.inventory.supplier.domain.model.Supplier;
import com.axconstantino.inventory.supplier.infrastructure.dto.SupplierDTO;
import com.axconstantino.inventory.supplier.infrastructure.dto.UpdateSupplierRequest;
import com.axconstantino.inventory.supplier.infrastructure.mapper.SupplierMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/api/v1/suppliers")
@RestController
@RequiredArgsConstructor
@Validated
public class SupplierController {
    private final CreateSupplier createSupplier;
    private final GetAllSuppliers getAllSuppliers;
    private final GetSupplierById getSupplierById;
    private final GetSupplierByName getSupplierByName;
    private final UpdateSupplier updateSupplier;
    private final DeleteSupplier deleteSupplier;
    private final SupplierMapper supplierMapper;

    @PostMapping
    public ResponseEntity<Void> createSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        var supplier = supplierMapper.toDomain(supplierDTO);
        createSupplier.createSupplier(supplier);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSuppliers(
            @PageableDefault(size = 10, page = 0, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        var suppliers = getAllSuppliers.getAllSuppliers(pageable);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long supplierId) {
        var supplier = getSupplierById.getSupplierById(supplierId);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getSupplierByName(@PathVariable String name) {
        var supplier = getSupplierByName.getSupplierByName(name);
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Void> updateSupplier(
            @PathVariable Long supplierId,
            @Valid @RequestBody UpdateSupplierRequest updateRequest
    ) {
        Supplier updatedSupplier = supplierMapper.toDomain(updateRequest);
        updateSupplier.updateSupplier(supplierId, updatedSupplier);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long supplierId) {
        deleteSupplier.deleteSupplier(supplierId);
        return ResponseEntity.ok().build();
    }
}
