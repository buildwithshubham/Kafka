package com.shubham.core.dto.event;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductReservationEvent
{
    private UUID orderId;
    private UUID productId;
    private BigDecimal productPrice;
    private Integer productQunatity;


    public ProductReservationEvent() {
    }

    public ProductReservationEvent(UUID orderId, UUID productId, BigDecimal productPrice, Integer productQunatity) {
        this.orderId = orderId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productQunatity = productQunatity;
    }


    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQunatity() {
        return productQunatity;
    }

    public void setProductQunatity(Integer productQunatity) {
        this.productQunatity = productQunatity;
    }
}
