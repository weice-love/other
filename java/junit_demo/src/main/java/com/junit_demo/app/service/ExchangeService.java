package com.junit_demo.app.service;

import com.junit_demo.app.model.Money;

import java.util.Random;

/**
 * <p> @author     :  清风
 * <p> description :  金额比率计算服务
 * <p> create date :  2021/11/11 10:53
 */
public class ExchangeService {

    public double getRate(Money money, Money toMoney) {
        return new Random().nextDouble();
    }

}
