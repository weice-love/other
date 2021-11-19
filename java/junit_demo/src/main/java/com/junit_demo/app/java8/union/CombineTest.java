package com.junit_demo.app.java8.union;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * <p> @author     :  清风
 * <p> description :
 *      结合器
 * <p> create date :  2021/11/19 13:48
 */
public class CombineTest {
    private static final Logger log = LoggerFactory.getLogger(CombineTest.class);

    @DisplayName("高阶函数(结合器)")
    @Test
    public void test() {
        log.info("repeat(3): 10 -> {}", repeat(3, (Integer x) -> x * 2).apply(10));
    }

    public static <A, B, C> Function<A, C> compose(Function<A, B> fA, Function<B, C> fB) {
        return x -> fB.apply(fA.apply(x));
    }

    public static <A>Function<A, A> repeat(int n, Function<A, A> f) {
        return 0 == n ? x -> x : compose(repeat(n-1, f), f);
    }
}
