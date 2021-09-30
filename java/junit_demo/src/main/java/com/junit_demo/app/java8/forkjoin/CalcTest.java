package com.junit_demo.app.java8.forkjoin;

import com.junit_demo.app.util.StopWatchTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/27 17:42
 */
public class CalcTest {

    private static final Logger log = LoggerFactory.getLogger(CalcTest.class);

    @ParameterizedTest
    @ValueSource(ints = {1000000000})
    @DisplayName("使用fork/join求和")
    public void calcSum(int ans) {
        // JIT优化（使用for循环）
        IntStream.rangeClosed(1, 10).boxed().map(
                a -> StopWatchTemplate.startAndReturnRunTime("自带框架 => " + a + ". 计算总和", () -> LongStream.rangeClosed(1, ans).parallel().sum())
        ).reduce(Long::min).ifPresent(System.out::println);

        long[] numbers = LongStream.rangeClosed(1, ans).toArray();
        IntStream.rangeClosed(1, 10).boxed().map(
                a -> {
                    ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(numbers);
                    return StopWatchTemplate.startAndReturnRunTime("自定义框架 => " + a + ". 计算总和", () -> new ForkJoinPool().invoke(forkJoinSumCalculator));
                }
        ).reduce(Long::min).ifPresent(System.out::println);

//        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(numbers);
//        Long sum = StopWatchTemplate.start("2. 计算总和", () -> new ForkJoinPool().invoke(forkJoinSumCalculator));

//        log.info("1. sum: {}", StopWatchTemplate.start("1. 计算总和", () -> LongStream.rangeClosed(1, ans).parallel().sum()));
//        long[] numbers = LongStream.rangeClosed(1, ans).toArray();
//        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(numbers);
//        Long sum = StopWatchTemplate.start("2. 计算总和", () -> new ForkJoinPool().invoke(forkJoinSumCalculator));
//        log.info("2. sum: {}", sum);
    }

    @Test
    @DisplayName("获取系统参数")
    public void get() {
        log.info("{}", Runtime.getRuntime().availableProcessors());
    }
}
