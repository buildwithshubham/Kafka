package com.shubham.order_service.dao.jpa.enity;

import com.shubham.core.types.OrderStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "order_history")
@Entity
public class OrderHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "order_id")
    private UUID order_id;
    @Column(name = "status")
    private OrderStatus status;
    @Column(name = "created_at")
    private Timestamp created_at;

    public UUID getOrder_id() {
        return order_id;
    }

    public void setOrder_id(UUID order_id) {
        this.order_id = order_id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
