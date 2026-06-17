package com.shubham.core.dto.event;

import java.util.UUID;

public record PaymentFailedEvent (UUID orderId, UUID productId, Integer productQuantity){
}
