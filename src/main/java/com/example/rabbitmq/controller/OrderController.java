package com.example.rabbitmq.controller;

import com.example.rabbitmq.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitMq.topic.queue.name}")
    private String queueName;

    @Value("${rabbitMq.topic.exchange.name}")
    private String exchangeName;

    @Value("${rabbitMq.topic.routing.key}")
    private String routingKey;

    @PostMapping("/order/send")
    public void send(@RequestBody Order order){
        rabbitTemplate.convertAndSend(exchangeName,routingKey,order);
    }



}
