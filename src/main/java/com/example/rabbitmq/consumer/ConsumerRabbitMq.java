package com.example.rabbitmq.consumer;

import com.example.rabbitmq.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class ConsumerRabbitMq {
    @Value("${rabbitMq.topic.queue.name}")
    private String queueName;

    @Async
    @Scheduled(fixedRate = 1000*60*2)
    @RabbitListener(queues = "rabbitMq-queue-name")
    public  void  getMassage(@RequestBody Order order){
        try {
            System.out.println(new ObjectMapper().writeValueAsString(order));
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
