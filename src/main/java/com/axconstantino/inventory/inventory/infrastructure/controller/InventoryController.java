package com.axconstantino.inventory.inventory.infrastructure.controller;

import com.axconstantino.inventory.inventory.application.usecase.AdjustStockLevel;
import com.axconstantino.inventory.inventory.application.usecase.GetStockLevelByProductId;
import com.axconstantino.inventory.inventory.domain.model.StockLevel;
import com.axconstantino.inventory.inventory.infrastructure.dto.StockAdjustmentRequestDTO;
import com.axconstantino.inventory.inventory.infrastructure.dto.StockLevelDTO;
import com.axconstantino.inventory.inventory.infrastructure.mapper.StockLevelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/inventory")
@RestController
@RequiredArgsConstructor
@Validated
public class InventoryController {

    private final GetStockLevelByProductId getStockLevelByProduct;
    private final AdjustStockLevel adjustStockLevel;
    private final StockLevelMapper stockLevelMapper;

    @GetMapping("/stock/{productId}")
    public ResponseEntity<StockLevelDTO> getStockLevel(@PathVariable Long productId) {
        return getStockLevelByProduct.getStockLevelByProductId(productId)
                .map(stockLevelMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/adjustments")
    public ResponseEntity<StockLevelDTO> adjustStock(
            @Valid @RequestBody StockAdjustmentRequestDTO adjustmentRequest) {
        try {
            StockLevel updatedStockLevel = adjustStockLevel.adjustStock(
                    adjustmentRequest.getProductId(),
                    adjustmentRequest.getQuantityChange(),
                    adjustmentRequest.getAdjustmentReason()
            );
            StockLevelDTO responseDTO = stockLevelMapper.toDTO(updatedStockLevel);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
