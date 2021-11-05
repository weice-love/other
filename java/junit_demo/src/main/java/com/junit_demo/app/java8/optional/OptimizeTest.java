package com.junit_demo.app.java8.optional;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * <p> @author     :  清风
 * <p> description :  优化测试类
 * <p> create date :  2021/11/5 11:08
 */
public class OptimizeTest {

    @DisplayName("利用Optional优化if的判空")
    @Test
    public void optimizeIf() {

        Optional<OptionalTest.Insurance> insurance = nullSafeFindCheapestInsurance(null, Optional.of(new OptionalTest.Car()));
        Optional<OptionalTest.Insurance> insurance1 = nullSafeFindCheapestInsuranceV2(null, Optional.of(new OptionalTest.Car()));
        // 都存在情况下
        Optional<OptionalTest.Insurance> insurance2 = nullSafeFindCheapestInsurance(Optional.of(new OptionalTest.User()), Optional.of(new OptionalTest.Car()));
        Optional<OptionalTest.Insurance> insurance3 = nullSafeFindCheapestInsuranceV2(Optional.of(new OptionalTest.User()), Optional.of(new OptionalTest.Car()));

    }

    private Optional<OptionalTest.Insurance> nullSafeFindCheapestInsuranceV2(Optional<OptionalTest.User> user, Optional<OptionalTest.Car> car) {
        return user.flatMap(user1 -> car.map(car1 -> findCheapestInsurance(user1, car1)));
    }

    private Optional<OptionalTest.Insurance> nullSafeFindCheapestInsurance(Optional<OptionalTest.User> user, Optional<OptionalTest.Car> car) {
        if (user.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(user.get(), car.get()));
        }
        return Optional.empty();
    }

    private OptionalTest.Insurance findCheapestInsurance(OptionalTest.User user, OptionalTest.Car car) {
        // 不同的保险公司提供的查询服务
        // 对比所有数据
        OptionalTest.Insurance cheapestCompany = new OptionalTest.Insurance();
        return cheapestCompany;
    }
}
