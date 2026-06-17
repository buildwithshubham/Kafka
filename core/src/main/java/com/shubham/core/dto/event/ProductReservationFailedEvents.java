package com.shubham.core.dto.event;

import java.util.UUID;

public record ProductReservationFailedEvents(UUID productId,
                                             UUID orderId,
                                             Integer productQuantity) {}
