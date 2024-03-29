package com.junit_demo.app.java8.model;

import java.util.Random;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/8 11:48
 */
public class Shop {
    private String name;
    private static final Random random = new Random();

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private static void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    /**
     * @author     : 清风
     * <p>description : 0.5s ~ 2.5s随机延迟
     * <p>create time : 11:20 2021/11/11
     *
     */
    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
