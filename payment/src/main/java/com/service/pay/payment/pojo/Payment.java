package com.service.pay.payment.pojo;

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
public class Payment {

    private Integer userId;
    private Double bankBalance;
}
