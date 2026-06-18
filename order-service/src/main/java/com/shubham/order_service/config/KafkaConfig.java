package com.shubham.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    @Value("${orders.events.topic.name}")
    private String orderEventTopicName;
    @Value("${product.commands.topic.name}")
    private String productCommandsTopicName;
    @Value("${payments.commands.topic.name}")
    private String paymentsCommandTopicName;
    @Value("${orders.commands.topic.name}")
    private String orderCommandTopicName;

    private final static Integer TOPIC_REPLICATION_FACTOR=3;
    private final static Integer TOPIC_PARTITION=3;

    KafkaTemplate<String,Object> kafkaTemplet(ProducerFactory<String,Object> producerFactory){
       return new KafkaTemplate<>(producerFactory);
    }
    @Bean
    NewTopic createOrdersEventsTopic(){
        return TopicBuilder.name(orderEventTopicName)
                .partitions(TOPIC_PARTITION)
                .replicas(TOPIC_REPLICATION_FACTOR)
                .build();
    }

    @Bean
    NewTopic createProductCommandsTopic(){
        return TopicBuilder.name(productCommandsTopicName)
                .partitions(TOPIC_PARTITION)
                .replicas(TOPIC_REPLICATION_FACTOR)
                .build();
    }

    @Bean
    NewTopic createPaymentsCommandsTopic(){
        return TopicBuilder.name(paymentsCommandTopicName)
                .partitions(TOPIC_PARTITION)
                .replicas(TOPIC_REPLICATION_FACTOR)
                .build();
    }

    @Bean
    NewTopic createOrdersCommandsTopic(){
        return TopicBuilder.name(orderCommandTopicName)
                .partitions(TOPIC_PARTITION)
                .replicas(TOPIC_REPLICATION_FACTOR)
                .build();
    }
}
