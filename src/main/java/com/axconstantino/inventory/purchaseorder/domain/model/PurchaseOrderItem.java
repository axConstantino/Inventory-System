package com.axconstantino.inventory.purchaseorder.domain.model;

import com.axconstantino.inventory.products.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderItem {
    private Long id;
    private PurchaseOrder purchaseOrder;
    private Product product;
    private Integer quantityOrdered;
    private BigDecimal unitPrice;
    private Integer quantityReceived;
}
