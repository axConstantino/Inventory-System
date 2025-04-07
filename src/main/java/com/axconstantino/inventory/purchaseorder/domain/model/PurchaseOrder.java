package com.axconstantino.inventory.purchaseorder.domain.model;

import com.axconstantino.inventory.purchaseorder.domain.model.enums.POStatus;
import com.axconstantino.inventory.supplier.domain.model.Supplier;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrder {
    private Long id;
    private String poNumber;
    private Supplier supplier;
    private LocalDateTime orderDate;
    private LocalDateTime expectedDeliveryDate;
    private POStatus status;
    private String notes;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<PurchaseOrderItem> items = new ArrayList<>();

    public void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantityOrdered())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(PurchaseOrderItem item) {
        items.add(item);
        item.setPurchaseOrder(this);
        calculateTotalAmount();
    }

    public void removeItem(PurchaseOrderItem item) {
        items.remove(item);
        item.setPurchaseOrder(null);
        calculateTotalAmount();
    }
}
