package com.junit_demo.app.util;

import java.util.function.Function;

/**
 * <p> @author     :  清风
 * <p> description :  功能
 * <p> create date :  2021/9/27 15:54
 */
public class FunctionUtil {

    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
