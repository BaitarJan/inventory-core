package com.inventory.model;

import java.time.LocalDateTime;

public class StockMovement {

    private Integer id;
    private Integer productId;
    private double quantity;
    private MovementType type;
    private LocalDateTime createdAt;

    public StockMovement(Integer productId,
                         double quantity,
                         MovementType type,
                         LocalDateTime createdAt) {

        this.productId = productId;
        this.quantity = quantity;
        this.type = type;
        this.createdAt = createdAt;
    }

    public Integer getProductId() {
        return productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public MovementType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}