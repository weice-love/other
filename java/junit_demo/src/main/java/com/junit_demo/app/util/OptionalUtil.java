package com.junit_demo.app.util;

import com.junit_demo.app.java8.optional.OptionalTest;

import java.util.Optional;

/**
 * <p> @author     :  清风
 * <p> description :  optional工具类
 * <p> create date :  2021/11/5 13:58
 */
public class OptionalUtil {

    public static Optional<Integer> string2Int(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        OptionalTest.User user = (OptionalTest.User)Optional.of(null).get();
        Optional<OptionalTest.Car> car = user.getCar();
    }
}
