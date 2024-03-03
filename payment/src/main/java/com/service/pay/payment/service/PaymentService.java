package com.service.pay.payment.service;

import com.service.pay.payment.event.OrderEvent;
import com.service.pay.payment.event.PaymentEvent;
import com.service.pay.payment.pojo.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author : Shirshak Upadhayay
 * @Date : 1/1/24
 */

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final RabbitTemplate rabbitTemplate;

    private List<Payment> bankBalanceInfo = List.of(
            new Payment(12,25000.0),new Payment(121,30000.0)
            ,new Payment(1211,12000.0)
    );

    @RabbitListener(queues = {"order-event-queue"})
    public void getQueueEvent(OrderEvent orderEvent){
        if(checkIfEnoughBankBalance(orderEvent)){
            completeTransaction(orderEvent);
            rabbitTemplate.convertAndSend("saga-exchange","payment-event-routing-key"
                    ,new PaymentEvent("SUCCESS",orderEvent.getOrderId(), orderEvent.getUserId()));
        }else{
            System.out.println("Not Enough Bank Balance");
            rabbitTemplate.convertAndSend("saga-exchange","payment-event-routing-key"
                    ,new PaymentEvent("FAILED",orderEvent.getOrderId(), orderEvent.getUserId()));
        }
    }

    private void completeTransaction(OrderEvent orderEvent) {
       for(Payment payment : bankBalanceInfo){
           if(orderEvent.getUserId().equals(payment.getUserId())){
               payment.setBankBalance(payment.getBankBalance()-orderEvent.getOrderAmount());
           }
       }
    }

    private boolean checkIfEnoughBankBalance(OrderEvent orderEvent){
       for(Payment payment : bankBalanceInfo){
           if(orderEvent.getUserId().equals(payment.getUserId())){
               if(payment.getBankBalance() >= orderEvent.getOrderAmount())
                   return true;
           }
       }
       return false;
    }

    public Payment getBankBalanceOfUser(Integer userId){
        for(Payment payment : bankBalanceInfo){
            if(payment.getUserId().equals(userId)){
                return payment;
            }
        }
        return null;
    }

}
