package com.shubham.product_service.service.handle;

import com.shubham.core.dto.Product;
import com.shubham.core.dto.commands.ReservedProduvtCommand;
import com.shubham.core.dto.event.ProductReservationEvent;
import com.shubham.core.dto.event.ProductReservationFailedEvents;
import com.shubham.product_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${products.commands.topic.name}")
public class ProductCommandsHandler {
    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductCommandsHandler.class);

    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final String productEventTopicName;

    public ProductCommandsHandler(ProductService productService,
                                  KafkaTemplate<String, Object> kafkaTemplate,
                                  @Value("${product.events.topic.name}") String productEventTopicName) {
        this.productService = productService;
        this.kafkaTemplate = kafkaTemplate;
        this.productEventTopicName = productEventTopicName;
    }

    @KafkaHandler
    public void handleCommand(@Payload ReservedProduvtCommand command){
        try{
            Product desiredProduct = new Product(command.getProdctId(),command.getProductQuantity());
            Product reservedProduct = productService.reserve(desiredProduct,command.getOrderId());
            ProductReservationEvent productReservationEvent = new ProductReservationEvent(command.getOrderId(),
                    command.getProdctId(),
                    reservedProduct.getPrice(),
                    command.getProductQuantity());
            kafkaTemplate.send(productEventTopicName,productEventTopicName);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            ProductReservationFailedEvents productReservationFailedEvents = new ProductReservationFailedEvents(command.getProdctId(),
                    command.getOrderId(),command.getProductQuantity());
            kafkaTemplate.send(productEventTopicName,productReservationFailedEvents);
        }
    }
}
