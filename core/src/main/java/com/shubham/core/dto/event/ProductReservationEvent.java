package com.shubham.core.dto.event;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductReservationEvent(UUID orderId, UUID productId, BigDecimal productPrice, Integer productQunatity) {
}
