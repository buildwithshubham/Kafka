package com.shubham.core.dto.event;

import java.util.UUID;

public record PaymentProcessedEvents (UUID orderId, UUID paymentID){
}
