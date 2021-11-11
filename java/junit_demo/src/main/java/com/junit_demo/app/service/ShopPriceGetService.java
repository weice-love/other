package com.junit_demo.app.service;

import com.junit_demo.app.java8.model.Discount;
import com.junit_demo.app.java8.model.Quote;
import com.junit_demo.app.java8.model.Shop;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/11 11:27
 */
public class ShopPriceGetService {

    private List<Shop> shops;

    private Executor executor;

    public ShopPriceGetService(List<Shop> shops, Executor executor) {
        this.shops = shops;
        this.executor = executor;
    }

    public Stream<CompletableFuture<String>> findPriceStream(String product) {
        return shops.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor)
                )
                .map(future ->
                        future.thenApply(Quote::parse)
                )
                .map(future ->
                        future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor))
                );
    }

    public List<String> findPrices(List<Shop> shops, String product, Executor executor) {
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
