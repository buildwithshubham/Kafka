package com.shubham.core.exception;

import java.util.UUID;

public class ProductInsufficientQuantityException extends RuntimeException {
    private final UUID productId;
    private final UUID orderId;


    public ProductInsufficientQuantityException(UUID productId, UUID orderId){
        super("Product "+productId+" has insufficent qunatity in the stock for order "+orderId);
        this.productId=productId;
        this.orderId=orderId;


    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
