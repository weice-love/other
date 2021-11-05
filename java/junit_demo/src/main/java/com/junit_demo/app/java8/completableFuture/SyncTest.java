package com.junit_demo.app.java8.completableFuture;

import com.junit_demo.app.util.StopWatchTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :  异步计算demoe
 * <p> create date :  2021/11/5 16:22
 */
public class SyncTest {

    private static final Logger log = LoggerFactory.getLogger(SyncTest.class);

    @DisplayName("性能测试")
    @Test
    public void calcTest() {
        Double async = IntStream.rangeClosed(1, 100).boxed().map(
                a -> StopWatchTemplate.startAndReturnRunTime("同步调用", SyncTest::doSomething)
        ).collect(Collectors.averagingLong(value -> value));
        log.info("同步调用执行100次平均时间: {}", async);
        Double sync = IntStream.rangeClosed(1, 100).boxed().map(
                a -> StopWatchTemplate.startAndReturnRunTime("异步调用", SyncTest::doSomethingSync)
        ).collect(Collectors.averagingLong(value -> value));
        log.info("异步调用执行100次平均时间: {}", sync);
    }

    @DisplayName("异常测试")
    @Test
    public void exceptionTest() {
        CompletableFuture<Integer> priceSyncWithCatchException = getPriceSyncWithCatchException(RandomStringUtils.random(1));
        try {
            Integer integer = priceSyncWithCatchException.get();
        } catch (Exception e) {
            log.error("msg: ", e);
        }

        CompletableFuture<Integer> priceSync = getPriceSync(RandomStringUtils.random(1));
        try {
            // 异常会卡死
            Integer integer = priceSync.get();
        } catch (Exception e) {
            log.error("msg1: ", e);
        }
    }

    private static void doSomething() {
        int price = getPrice(RandomStringUtils.random(5));
        int price1 = getPrice(RandomStringUtils.random(5));
        log.info("price: {}, price1: {}", price, price1);
    }

    private static void doSomethingSync() {
        CompletableFuture<Integer> priceSync = getPriceSync(RandomStringUtils.random(5));
        int price = getPrice(RandomStringUtils.random(5));
        Integer price1;
        try {
            price1 = priceSync.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("price: {}, price1: {}", price, price1);
    }

    private static CompletableFuture<Integer> getPriceSync(String product) {
        CompletableFuture<Integer> result = new CompletableFuture<>();
        new Thread(() -> {
            result.complete(getPrice(product));
        }).start();
        return result;
    }

    private static CompletableFuture<Integer> getPriceSyncWithCatchException(String product) {
        CompletableFuture<Integer> result = new CompletableFuture<>();
        new Thread(() -> {
            try {
                result.complete(getPrice(product));
            } catch (Exception e) {
                // 抛出异常
//                result.completeExceptionally(new RuntimeException("你是个猪"));
                result.completeExceptionally(e);
            }
        }).start();
        return result;
    }

    private static int getPrice(String product) {
        delay();
        return new Random().nextInt(100000) * product.charAt(0) + product.charAt(1);
    }


    private static void delay() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
