package com.service.saga.order.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author : Shirshak Upadhayay
 * @Date : 12/28/23
 * */

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange sagaExchange(){
        return ExchangeBuilder.directExchange("saga-exchange").build();
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public Queue orderEventQueue(){
        return new Queue("order-event-queue");
    }

    @Bean
    public Queue paymentEventQueue(){
        return new Queue("payment-event-queue");
    }



    @Bean
    public Binding orderEventbinding(){
        return BindingBuilder.bind(orderEventQueue())
                .to(sagaExchange())
                .with("order-event-routing-key");
    }

}

