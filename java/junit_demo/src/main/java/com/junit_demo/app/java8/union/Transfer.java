package com.junit_demo.app.java8.union;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.DoubleUnaryOperator;

/**
 * <p> @author     :  清风
 * <p> description :
 *      科里化:
 *          科里化①是一种将具备2个参数（比如，x和y）的函数f转化为使用一个参数的函数g，
 *          并且这个函数的返回值也是一个函数，它会作为新函数的一个参数
 * <p> create date :  2021/11/16 11:17
 */
public class Transfer {

    private static final Logger log = LoggerFactory.getLogger(Transfer.class);

    private static DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
    private static DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
    private static DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

    @DisplayName("科里化应用(摄氏度转华氏度)")
    @Test
    public void c2f() {
        Double nowC = 23.0;
        double nowF = convertCtoF.applyAsDouble(nowC);
        log.info("当前摄氏度：{}, 华氏度：{}", nowC, nowF);
    }

    @DisplayName("科里化应用(公里转英里)")
    @Test
    public void KmtoMi() {
        Double nowKm = 23.0;
        double nowMi = convertKmtoMi.applyAsDouble(nowKm);
        log.info("当前公里：{}, 英里：{}", nowKm, nowMi);
    }

    private static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }
}
