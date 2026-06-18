package com.shubham.order_service.dto;

import com.shubham.core.types.OrderStatus;

import java.sql.Timestamp;
import java.util.UUID;

public record OrderHistory(UUID id,
                           UUID orderId,
                           OrderStatus status,
                           Timestamp createdAt) {
}
