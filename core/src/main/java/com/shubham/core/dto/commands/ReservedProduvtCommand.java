package com.shubham.core.dto.commands;

import java.util.UUID;

public class ReservedProduvtCommand
{
    private UUID prodctId;
    private Integer productQuantity;
    private UUID orderId;

    public ReservedProduvtCommand(UUID prodctId, Integer productQuantity) {
        this.prodctId = prodctId;
        this.productQuantity = productQuantity;
    }

    public ReservedProduvtCommand(UUID prodctId, Integer productQuantity, UUID orderId) {
        this.prodctId = prodctId;
        this.productQuantity = productQuantity;
        this.orderId = orderId;
    }

    public UUID getProdctId() {
        return prodctId;
    }

    public void setProdctId(UUID prodctId) {
        this.prodctId = prodctId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}
