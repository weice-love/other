package com.junit_demo.app.java8.completableFuture;

import com.junit_demo.app.config.ThreadPoolConfig;
import com.junit_demo.app.java8.model.Discount;
import com.junit_demo.app.java8.model.Quote;
import com.junit_demo.app.java8.model.Shop;
import com.junit_demo.app.util.StopWatchTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/10 16:39
 */
public class ShopFeeTest {


    @DisplayName("同步")
    @Test
    public void compare() {

        List<Shop> shops_8 = IntStream.rangeClosed(1, 8)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        List<Shop> shops_9 = IntStream.rangeClosed(1, 9)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        List<Shop> shops_16 = IntStream.rangeClosed(1, 16)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        String productName = RandomStringUtils.randomAlphabetic(5);
        StopWatchTemplate.start("串行获取各商家(8家)商品价格", () -> shops_8.stream()
                .map(shop -> shop.getPrice(productName))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList()));
        StopWatchTemplate.start("并行获取各商家(8家)商品价格", () -> shops_8.parallelStream()
                .map(shop -> shop.getPrice(productName))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList()));
        StopWatchTemplate.start("并行获取各商家(8家)商品价格, CompletableFuture使用自定义Executor", () -> findPricesWithExecutor(shops_8, productName, ThreadPoolConfig.getINSTANCE()));

        // 9家
        StopWatchTemplate.start("串行获取各商家(9家)商品价格", () -> shops_9.stream()
                .map(shop -> shop.getPrice(productName))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList()));
        StopWatchTemplate.start("并行获取各商家(9家)商品价格", () -> shops_9.parallelStream()
                .map(shop -> shop.getPrice(productName))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList()));
        StopWatchTemplate.start("并行获取各商家(9家)商品价格, CompletableFuture使用自定义Executor", () -> findPricesWithExecutor(shops_9, productName, ThreadPoolConfig.getINSTANCE()));

        // 16家
        StopWatchTemplate.start("串行获取各商家(16家)商品价格", () -> shops_16.stream()
                .map(shop -> shop.getPrice(productName))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList()));
        StopWatchTemplate.start("并行获取各商家(16家)商品价格", () -> shops_16.parallelStream()
                .map(shop -> shop.getPrice(productName))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList()));
        StopWatchTemplate.start("并行获取各商家(16家)商品价格, CompletableFuture使用自定义Executor", () -> findPricesWithExecutor(shops_16, productName, ThreadPoolConfig.getINSTANCE()));
    }

    private List<String> findPricesWithExecutor(List<Shop> shops, String product, Executor executor) {
//        List<CompletableFuture<String>> priceFutures =
//                shops.stream()
//                        .map(shop -> CompletableFuture.supplyAsync(
//                                () -> shop.getPrice(product), executor))
//                        .map(future -> future.thenApply(Quote::parse))
//                        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
//                        .collect(Collectors.toList());
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product), executor))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

}
