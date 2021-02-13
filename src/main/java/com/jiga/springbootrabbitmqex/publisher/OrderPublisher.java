package com.jiga.springbootrabbitmqex.publisher;


import com.jiga.springbootrabbitmqex.config.MessagingConfig;
import com.jiga.springbootrabbitmqex.dto.Order;
import com.jiga.springbootrabbitmqex.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){

    order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderStatus=new OrderStatus(order,"Prosess", "Order place successfully in "+restaurantName);
        template.convertAndSend(MessagingConfig.Exchange,MessagingConfig.ROUTING_KEY,orderStatus);
        return "Success!!";

    }
}
