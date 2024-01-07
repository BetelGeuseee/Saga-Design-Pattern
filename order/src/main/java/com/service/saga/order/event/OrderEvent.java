package com.service.saga.order.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Shirshak Upadhayay
 * @Date : 1/1/24
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private Integer orderId;
    private Integer userId;
    private Double orderAmount;
}
