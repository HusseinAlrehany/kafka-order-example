package com.example.kafka_orders.producer;

import com.example.kafka_orders.configuration.KafkaConfig;
import com.example.kafka_orders.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrder(Order order){
        //using orderId as key

        kafkaTemplate.send(KafkaConfig.ORDERS_TOPIC, order.getOrderId(), order);

    }

}
