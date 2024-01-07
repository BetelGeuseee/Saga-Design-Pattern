package com.service.saga.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Shirshak Upadhayay
 * @Date : 12/28/23
 */

@Getter
@Setter
@AllArgsConstructor
public class Order {

    private String status;
    private Integer orderId;
    private Integer userId;
    private Double amount;

}
