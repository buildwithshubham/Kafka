package com.shubham.core.dto.event;

import java.util.UUID;

public class PaymentProcessedEvents {
    private UUID orderId;
    private UUID paymentID;


    public PaymentProcessedEvents() {
    }

    public PaymentProcessedEvents(UUID orderId, UUID paymentID) {
        this.orderId = orderId;
        this.paymentID = paymentID;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(UUID paymentID) {
        this.paymentID = paymentID;
    }
}
