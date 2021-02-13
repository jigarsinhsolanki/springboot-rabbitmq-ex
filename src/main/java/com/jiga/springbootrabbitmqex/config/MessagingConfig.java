package com.jiga.springbootrabbitmqex.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String QUEUE="newQueue";
    public static final String Exchange="newExchange";
    public static final String ROUTING_KEY="newRoutingKey";

    @Bean
    public Queue queue(){

        return new Queue(QUEUE);
    }
    @Bean
    public TopicExchange topicExchange(){

        return new TopicExchange(Exchange);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){

        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter(){

        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate template(ConnectionFactory connectionFactory){

        final RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
