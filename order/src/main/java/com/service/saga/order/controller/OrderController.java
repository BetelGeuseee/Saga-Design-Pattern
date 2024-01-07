package com.service.saga.order.controller;

import com.service.saga.order.pojo.Order;
import com.service.saga.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Shirshak Upadhayay
 * @Date : 12/28/23
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public String triggerApiInSaga(@RequestBody Order order){
        orderService.createOrder(order);
        return "Order Created";
    }
    @GetMapping("/get-all-order")
    public List<Order> triggerApiInSaga(){
      return orderService.getAllOrder();
    }
}
