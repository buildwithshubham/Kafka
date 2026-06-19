package com.shubham.order_service.service;

import com.shubham.core.types.OrderStatus;
import com.shubham.order_service.dao.jpa.enity.OrderHistoryEntity;
import com.shubham.order_service.dao.jpa.repository.OrderHistoryRepository;
import com.shubham.order_service.dto.OrderHistory;
import com.shubham.order_service.service.OrderHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    public OrderHistoryServiceImpl(OrderHistoryRepository orderHistoryRepository){
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public void add(UUID orderId, OrderStatus orderStatus) {
        OrderHistoryEntity entity = new OrderHistoryEntity();
        entity.setOrderId(orderId);
        entity.setStatus(orderStatus);
        entity.setCreatedAt(new Timestamp(new Date().getTime()));

    }

    @Override
    public List<OrderHistory> findByOrderId(UUID orderId) {
        var entities = orderHistoryRepository.findByOrderId(orderId);
        return entities.stream()
                .map(entity ->{
                    OrderHistory orderHistory = new OrderHistory();
                    BeanUtils.copyProperties(entity,orderHistory);
                    return orderHistory;
                }).toList();
    }

}
