package com.shubham.order_service.service;

import com.shubham.core.dto.Order;
import com.shubham.core.dto.event.OrderApprovedEvent;
import com.shubham.core.dto.event.OrderCreatedEvent;
import com.shubham.core.types.OrderStatus;
import com.shubham.order_service.dao.jpa.enity.OrderEntity;
import com.shubham.order_service.dao.jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final String orderEventTopicName;

    public OrderServiceImpl(OrderRepository orderRepository,
                            KafkaTemplate<String,Object> kafkaTemplate,
                            @Value("${orders.events.topic.name}") String orderEventTopicName){
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.orderEventTopicName = orderEventTopicName;
    }

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(order.getCustomerId());
        entity.setProductId(order.getProductId());
        entity.setProductQuantity(order.getProductQuantity());
        entity.setStatus(OrderStatus.CREATED);
        orderRepository.save(entity);

        OrderCreatedEvent placeOrder = new OrderCreatedEvent(
                entity.getId(),
                entity.getCustomerId(),
                order.getProductId(),
                order.getProductQuantity()
        );
        kafkaTemplate.send(orderEventTopicName, placeOrder);
        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getProductId(),
                entity.getProductQuantity(),
                entity.getStatus());
    }

    @Override
    public void approveOrder(UUID orderID) {
        OrderEntity orderEntity = orderRepository.findById(orderID).orElse(null);
        Assert.notNull(orderEntity,"No orderis found with id "+orderID+" in the databse table");
        orderEntity.setStatus(OrderStatus.APPROVED);
        orderRepository.save(orderEntity);
        OrderApprovedEvent orderApprovedEvent = new OrderApprovedEvent(orderID);
        kafkaTemplate.send(orderEventTopicName,orderApprovedEvent);

    }
}
