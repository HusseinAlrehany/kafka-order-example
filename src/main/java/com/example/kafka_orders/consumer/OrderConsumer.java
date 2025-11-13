package com.example.kafka_orders.consumer;

import com.example.kafka_orders.entity.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class OrderConsumer {

    private final List<Order> consumedOrders = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void consume(Order order){
        System.out.println("Consumed Order: " + order.getOrderId() + " -> " + order.getProduct());

        consumedOrders.add(order);
    }

    public List<Order> getConsumedOrders(){
        return consumedOrders;
    }
}
