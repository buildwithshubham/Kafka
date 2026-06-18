package com.shubham.core.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment{
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private BigDecimal productPrice;
    private Integer productQuantity;

    public Payment() {
    }

    public Payment(UUID id, UUID productId, UUID orderId, Integer productQuantity, BigDecimal productPrice) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public Payment(UUID orderId, BigDecimal productPrice, UUID productId, Integer productQuantity) {
        this.orderId = orderId;
        this.productPrice = productPrice;
        this.productId = productId;
        this.productQuantity = productQuantity;
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

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
