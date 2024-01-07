package com.service.saga.order.service;

import com.service.saga.order.event.PaymentEvent;
import com.service.saga.order.pojo.Order;
import com.service.saga.order.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Shirshak Upadhayay
 * @Date : 12/28/23
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final RabbitTemplate rabbitTemplate;
    private List<Order> orderList = new ArrayList<>();


    @RabbitListener(queues = {"payment-event-queue"})
    public void listenToPaymentStatus(PaymentEvent paymentEvent){
        if(paymentEvent.getPaymentStatus().equals("FAILED")){
            compensateTransaction(paymentEvent);
        }else{
            System.out.println("PAYMENT SUCCESS");
        }
    }

    public void createOrder(Order order){
        orderList.add(order); //order has been created and set.
               rabbitTemplate.convertAndSend("saga-exchange", "order-event-routing-key"
                ,new OrderEvent(order.getOrderId(),order.getUserId(),order.getAmount()));
    }

    public void compensateTransaction(PaymentEvent paymentEvent){
       Integer orderId = paymentEvent.getOrderId();
       orderList.removeIf((order)-> order.getOrderId() == orderId);
    }
    public List<Order> getAllOrder(){

        return orderList;
    }

}

