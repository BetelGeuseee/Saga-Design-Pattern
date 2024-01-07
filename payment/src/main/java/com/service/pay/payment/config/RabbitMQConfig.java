package com.service.pay.payment.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Shirshak Upadhayay
 * @Date : 1/1/24
 */
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
    public Queue paymentEventQueue(){
        return new Queue("payment-event-queue");
    }
    @Bean
    public Queue orderEventQueue(){
        return new Queue("order-event-queue");
    }

    @Bean
    public Binding paymentEventBinding(){
        return BindingBuilder.bind(paymentEventQueue())
                .to(sagaExchange())
                .with("payment-event-routing-key");
    }

}
