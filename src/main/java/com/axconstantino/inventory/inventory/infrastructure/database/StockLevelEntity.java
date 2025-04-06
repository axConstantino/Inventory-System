package com.axconstantino.inventory.inventory.infrastructure.database;

import com.axconstantino.inventory.products.infrastructure.database.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_levels", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id"})
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockLevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_stocklevel_product"))
    private ProductEntity product;

    @Column(name = "quantity_on_hand", nullable = false)
    private Integer quantityOnHand;

    @Column(name = "last_updated_at", nullable = false)
    private LocalDateTime lastUpdatedAt;

    @Version
    private Long version;

    @PrePersist
    public void OnCreate() {
        lastUpdatedAt = LocalDateTime.now();
        if (this.quantityOnHand == null) {
            this.quantityOnHand = 0;
        }
    }

    @PreUpdate
    public void OnUpdate() {
        lastUpdatedAt = LocalDateTime.now();
    }
}
