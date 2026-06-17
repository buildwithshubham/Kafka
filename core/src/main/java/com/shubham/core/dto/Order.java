package com.shubham.core.dto;

import com.shubham.core.types.OrderStatus;

import java.util.UUID;

public record Order(UUID orderId,
                    UUID customerId,
                    UUID productId,
                    Integer productQuantity,
                    OrderStatus status) {
}
