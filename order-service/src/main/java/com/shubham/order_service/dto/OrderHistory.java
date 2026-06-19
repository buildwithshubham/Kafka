package com.shubham.order_service.dto;

import com.shubham.core.types.OrderStatus;

import java.sql.Timestamp;
import java.util.UUID;

public class OrderHistory {
    private UUID id;
    private UUID orderId;
    private OrderStatus status;
    private Timestamp createdAt;

    public OrderHistory() {
    }

    public OrderHistory(Timestamp createdAt, UUID orderId, UUID id, OrderStatus status) {
        this.createdAt = createdAt;
        this.orderId = orderId;
        this.id = id;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
