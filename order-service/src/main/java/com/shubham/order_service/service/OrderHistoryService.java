package com.shubham.order_service.service;

import com.shubham.core.types.OrderStatus;
import com.shubham.order_service.dto.OrderHistory;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {
    void add(UUID orderId, OrderStatus orderStatus);

    List<OrderHistory> findByOrderId(UUID orderId);

}
