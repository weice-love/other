package com.junit_demo.app.java8.completableFuture;

import com.junit_demo.app.config.ThreadPoolConfig;
import com.junit_demo.app.java8.model.Discount;
import com.junit_demo.app.java8.model.Quote;
import com.junit_demo.app.java8.model.Shop;
import com.junit_demo.app.model.Money;
import com.junit_demo.app.service.ExchangeService;
import com.junit_demo.app.service.ShopPriceGetService;
import com.junit_demo.app.util.StopWatchTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/10 16:39
 */
public class ShopFeeTest {

    private static final Logger log = LoggerFactory.getLogger(ShopFeeTest.class);

    @DisplayName("返回流来达到异步接收")
    @Test
    public void calcWithStream() {
        List<Shop> shops_8 = IntStream.rangeClosed(1, 8)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        List<Shop> shops_16 = IntStream.rangeClosed(1, 16)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        String product = RandomStringUtils.randomAlphabetic(3);
        ShopPriceGetService shopPriceGetService = new ShopPriceGetService(shops_8, ThreadPoolConfig.getINSTANCE());
        Stream<CompletableFuture<Void>> completableFutureStream = shopPriceGetService.findPriceStream(product).map(f -> f.thenAccept(System.out::println));
        CompletableFuture[] completableFutures = completableFutureStream.toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(completableFutures).join();
//        CompletableFuture.anyOf(completableFutures).join();
        log.info("(8个商店)All shops returned results or timed out");

        ShopPriceGetService shopPriceGetService_16 = new ShopPriceGetService(shops_16, ThreadPoolConfig.getINSTANCE());
        Stream<CompletableFuture<Void>> completableFutureStream_16 = shopPriceGetService_16.findPriceStream(product).map(f -> f.thenAccept(System.out::println));
        CompletableFuture[] completableFutures_16 = completableFutureStream_16.toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(completableFutures_16).join();
//        CompletableFuture.anyOf(completableFutures_16).join();
        log.info("(16个商店)any shops returned results or timed out");

    }


    @DisplayName("completableFuture联合")
    @Test
    public void combine() {
        List<Shop> shops_8 = IntStream.rangeClosed(1, 8)
                .boxed()
                .map(a -> new Shop(RandomStringUtils.randomAlphabetic(5)))
                .collect(Collectors.toList());
        Shop shop = shops_8.get(0);
        String product = RandomStringUtils.randomAlphabetic(5);
        ExchangeService exchangeService = new ExchangeService();
        Future<Double> futurePriceInUSD =
                CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                        .thenApply(Quote::parse)
                        .thenCombine(
                                CompletableFuture.supplyAsync(
                                        () -> exchangeService.getRate(Money.EUR, Money.USD)),
                                (price, rate) -> price.getPrice() * rate
                        );
        Double aDouble = null;
        try {
            aDouble = futurePriceInUSD.get();
            System.out.println(aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

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
