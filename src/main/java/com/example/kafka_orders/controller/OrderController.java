package com.example.kafka_orders.controller;

import com.example.kafka_orders.consumer.OrderConsumer;
import com.example.kafka_orders.entity.Order;
import com.example.kafka_orders.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;
    private final OrderConsumer orderConsumer;

    //Send the order to KAFKA topic
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        orderProducer.sendOrder(order);

        return ResponseEntity.accepted().body("Order sent to kafka: " + order.getOrderId());
    }

    @GetMapping("/consumed")
    public ResponseEntity<List<Order>> consumedOrders(){
        return ResponseEntity.ok(orderConsumer.getConsumedOrders());
    }

}
