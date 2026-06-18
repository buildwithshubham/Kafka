package com.shubham.order_service.service;

import com.shubham.core.dto.Order;

import java.util.UUID;

public interface OrderService {
    Order placeOrder(Order order);
    void approveOrder(UUID orderID);
}
