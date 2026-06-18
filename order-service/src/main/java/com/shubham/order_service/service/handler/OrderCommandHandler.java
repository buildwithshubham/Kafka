package com.shubham.order_service.service.handler;

import com.shubham.core.dto.commands.ApproveOrderCommand;
import com.shubham.order_service.service.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${order.commands.topic.name}")
public class OrderCommandHandler {
    private final OrderService orderService;

    public OrderCommandHandler(OrderService orderService){
        this.orderService=orderService;
    }

    public void handleCommand(@Payload ApproveOrderCommand approveOrderCommand){
        orderService.approveOrder(approveOrderCommand.orderId());
    }
}
