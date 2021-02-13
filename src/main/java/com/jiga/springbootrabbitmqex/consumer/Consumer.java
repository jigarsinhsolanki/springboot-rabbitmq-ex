package com.jiga.springbootrabbitmqex.consumer;


import com.jiga.springbootrabbitmqex.config.MessagingConfig;
import com.jiga.springbootrabbitmqex.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message recieved from queue: "+orderStatus);
    }
}
