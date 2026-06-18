package com.shubham.order_service.saga;


import com.shubham.core.dto.commands.ApproveOrderCommand;
import com.shubham.core.dto.commands.ProcessPaymentCommand;
import com.shubham.core.dto.commands.ReservedProduvtCommand;
import com.shubham.core.dto.event.OrderCreatedEvent;
import com.shubham.core.dto.event.PaymentProcessedEvents;
import com.shubham.core.dto.event.ProductReservationEvent;
import com.shubham.core.types.OrderStatus;
import com.shubham.order_service.dto.OrderHistory;
import com.shubham.order_service.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {
        "${order.events.topic.name}",
        "${product.events.topic.name}",
        "${payments.events.topic.name}"
})
public class OrderSaga {
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final String productsCommandsTopicName;
    private final OrderHistoryService orderHistoryService;
    private final String paymentsCommandsTopicName;
    private final String ordersCommandsTopicName;

    public OrderSaga(KafkaTemplate<String,Object> kafkaTemplate,
                     @Value("$product.commands.topic.name")
                     String productsCommandsTopicName,
                     OrderHistoryService orderHistoryService,
                     @Value("${payments.commands.topic.name}")
                     String paymentsCommandsTopicName,
                     @Value("${orders.commands.topic.name}")
                     String ordersCommandsTopicName){
        this.kafkaTemplate = kafkaTemplate;
        this.productsCommandsTopicName= productsCommandsTopicName;
        this.orderHistoryService= orderHistoryService;
        this.paymentsCommandsTopicName = paymentsCommandsTopicName;
        this.ordersCommandsTopicName = ordersCommandsTopicName;
    }

    @KafkaHandler
    public void handleEvent(@Payload OrderCreatedEvent event){
        ReservedProduvtCommand command = new ReservedProduvtCommand(
                event.productId(),
                event.productQuantity(),
                event.orderId());
        kafkaTemplate.send(productsCommandsTopicName,command);
        orderHistoryService.add(event.orderId(), OrderStatus.CREATED);
    }

    @KafkaHandler
    public void handleEvent(@Payload ProductReservationEvent event){
        ProcessPaymentCommand processPaymentCommand = new ProcessPaymentCommand(
                event.getOrderId(),
                event.getProductId(),
                event.getProductPrice(),
                event.getProductQunatity());

    kafkaTemplate.send(paymentsCommandsTopicName,processPaymentCommand);
}

    public void handleEvent(@Payload PaymentProcessedEvents events){
        ApproveOrderCommand approvedOrderCommand = new ApproveOrderCommand(events.getOrderId());
    }
}
