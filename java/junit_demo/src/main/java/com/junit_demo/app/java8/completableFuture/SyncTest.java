package com.junit_demo.app.java8.completableFuture;

import com.junit_demo.app.annotion.ListSource;
import com.junit_demo.app.config.ThreadPoolConfig;
import com.junit_demo.app.java8.model.Shop;
import com.junit_demo.app.util.StopWatchTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :  异步计算demoe
 * <p> create date :  2021/11/5 16:22
 */
public class SyncTest {

    private static final Logger log = LoggerFactory.getLogger(SyncTest.class);


    @DisplayName("比较")
    @Test
    public void compare() {
        List<Shop> shops_16 = IntStream.rangeClosed(1, 16)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        List<Shop> shops_9 = IntStream.rangeClosed(1, 9)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        List<Shop> shops_8 = IntStream.rangeClosed(1, 8)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        StopWatchTemplate.start("串行流(8个shop)", () -> shops_8.stream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
        StopWatchTemplate.start("并行流(8个shop)", () -> shops_8.parallelStream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
        StopWatchTemplate.start("并行流(8个shop), CompletableFuture", () -> findPrices(shops_8));


        StopWatchTemplate.start("串行流(9个shop)", () -> shops_9.stream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
        StopWatchTemplate.start("并行流(9个shop)", () -> shops_9.parallelStream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
        StopWatchTemplate.start("并行流(9个shop), CompletableFuture", () -> findPrices(shops_9));

        // 以下使用自定义线程池执行操作
        StopWatchTemplate.start("并行流(9个shop), CompletableFuture使用自定义Executor", () -> findPricesWithExecutor(shops_9, ThreadPoolConfig.getINSTANCE()));

        StopWatchTemplate.start("串行流(16个shop)", () -> shops_16.stream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
        StopWatchTemplate.start("并行流(16个shop)", () -> shops_16.parallelStream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
        StopWatchTemplate.start("并行流(16个shop), CompletableFuture", () -> findPrices(shops_16));
        // 只需要1s，能体现出使用自定义线程池的优势了； 以上的都是使用固定线程数的线程
        StopWatchTemplate.start("并行流(16个shop), CompletableFuture使用自定义Executor", () -> findPricesWithExecutor(shops_16, ThreadPoolConfig.getINSTANCE()));

    }

    @DisplayName("比较并行流和CompletableFuture")
    @ParameterizedTest
    @ListSource(resource = "shop_8.json", clazz = Shop.class)
    public void compare_8(List<Shop> shops) {
        // 并行流的底层的线程数，由以下方法获取
        log.info("availableProcessors: {}", Runtime.getRuntime().availableProcessors());
        StopWatchTemplate.start("串行流", () -> shops.stream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
        StopWatchTemplate.start("并行流", () -> shops.parallelStream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
    }

    @DisplayName("比较并行流和CompletableFuture")
    @ParameterizedTest
    @ListSource(resource = "shop_9.json", clazz = Shop.class)
    public void compare_9(List<Shop> shops) {
        log.info("availableProcessors: {}", Runtime.getRuntime().availableProcessors());
        StopWatchTemplate.start("串行流", () -> shops.stream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
        StopWatchTemplate.start("并行流", () -> shops.parallelStream().map(shop -> String.format("%s price is %d", shop.getName(), getPrice(shop.getName()))).collect(Collectors.toList()));
    }


    @DisplayName("使用建CompletableFuture内置方法实现异步执行")
    @Test
    public void innerMethodTest() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> getPrice(RandomStringUtils.random(2)));
        try {
            Integer integer = integerCompletableFuture.get();
        } catch (Exception e) {
            log.error("msg: ", e);
        }

        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> getPrice(RandomStringUtils.random(1)));
        try {
            Integer integer = integerCompletableFuture1.get();
        } catch (Exception e) {
            log.error("msg: ", e);
        }
    }

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

    private List<String> findPricesWithExecutor(List<Shop> shops, Executor executor) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        getPrice(shop.getName()), executor))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private List<String> findPrices(List<Shop> shops) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        getPrice(shop.getName())))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
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
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
