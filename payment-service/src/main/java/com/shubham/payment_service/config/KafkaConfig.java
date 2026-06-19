package com.shubham.payment_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
    private String paymentsEventsTopicName;
    private final static Integer TOPIC_REPLICATION_FACTOR=3;
    private final static Integer TOPIC_PARTITION=3;

    @Bean
    KafkaTemplate<String,Object> kafkaTemplate(ProducerFactory<String,Object> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    NewTopic createPaymentsEventsTopic(){
        return TopicBuilder.name(paymentsEventsTopicName)
                .partitions(TOPIC_PARTITION)
                .replicas(TOPIC_REPLICATION_FACTOR)
                .build();
    }
}
