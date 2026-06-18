package com.shubham.payment_service.service.handler;

import com.shubham.core.dto.Payment;
import com.shubham.core.dto.commands.ProcessPaymentCommand;
import com.shubham.core.dto.event.PaymentFailedEvent;
import com.shubham.core.dto.event.PaymentProcessedEvents;
import com.shubham.core.exception.CreditCardProcessedUnavilableException;
import com.shubham.payment_service.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${payments.commands.topic.name}")
public class PaymentsCommandsHandler {
    private final PaymentService paymentService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final String paymentEventsTopic;

    public PaymentsCommandsHandler(PaymentService paymentService,
                                   KafkaTemplate<String,Object> kafkaTemplate,
                                   @Value("${payments.events.topic.name}")String paymentEventsTopic){
        this.paymentService=paymentService;
        this.kafkaTemplate=kafkaTemplate;
        this.paymentEventsTopic=paymentEventsTopic;
    }

    @KafkaHandler
    public void handleCommand(@Payload ProcessPaymentCommand command){
        try{
            Payment payment = new Payment(command.getOrderId(),command.getProductPrice(),
                    command.getProductId(),
                    command.getProductQuantity());
            Payment processedPayment = paymentService.process(payment);
            PaymentProcessedEvents PaymentProcessEvent = new PaymentProcessedEvents(processedPayment.getOrderId(),
                    processedPayment.getId());
            kafkaTemplate.send(paymentEventsTopic,PaymentProcessEvent);
        } catch (CreditCardProcessedUnavilableException e) {
            logger.error(e.getLocalizedMessage(),e);
            PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent(command.getOrderId(),
                    command.getProductId(),
                    command.getProductQuantity());
            kafkaTemplate.send(paymentEventsTopic,paymentFailedEvent);
        }
    }
}
