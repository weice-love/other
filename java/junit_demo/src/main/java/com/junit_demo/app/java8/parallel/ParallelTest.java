package com.junit_demo.app.java8.parallel;

import com.junit_demo.app.util.StopWatchTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :  并行流（parallel）
 *                     串行流（sequential）
 * <p> create date :  2021/9/27 11:33
 */
public class ParallelTest {

    private static final Logger log = LoggerFactory.getLogger(ParallelTest.class);

    @ParameterizedTest
    @ValueSource(ints = {1000000000})
    @DisplayName("并行流求和(无限流)")
    public void sumParallel(int ans) {
        Long sum = StopWatchTemplate.start("串行", () -> Stream.iterate(1L, l -> l + 1).limit(ans).reduce(0L, Long::sum));
        // 超级慢？？？
        // 并没有并行，iterate需要依赖前一个数值，无法进行拆分
        Long parallelSum = StopWatchTemplate.start("并行", () -> Stream.iterate(1L, l -> l + 1).limit(ans).parallel().reduce(0L, Long::sum));
        log.info("sum: {}, parallelSum: {}", sum, parallelSum);
        Assertions.assertEquals(sum, parallelSum, "两个数一致");
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE})
    @DisplayName("并行流求和")
    public void sumParallelV2(int ans) {
        // 配置fork/join 的线程数量
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12")
        Long sum = StopWatchTemplate.start("串行", () -> LongStream.rangeClosed(1L, ans).sum());
        // 超级快？？？
        // 使用了并行拆分
        Long parallelSum = StopWatchTemplate.start("并行", () -> LongStream.rangeClosed(1L, ans).parallel().sum());
        log.info("sum: {}, parallelSum: {}", sum, parallelSum);
        Assertions.assertEquals(sum, parallelSum, "两个数一致");
    }
}
