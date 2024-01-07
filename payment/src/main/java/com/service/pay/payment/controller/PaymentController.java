package com.service.pay.payment.controller;

import com.service.pay.payment.pojo.Payment;
import com.service.pay.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Shirshak Upadhayay
 * @Date : 1/2/24
 */
@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @GetMapping("/get-user-balance/{userId}")
    public Payment getUserBalance(@PathVariable Integer userId){
        return paymentService.getBankBalanceOfUser(userId);
    }

}
