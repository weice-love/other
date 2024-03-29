package com.junit_demo.app.java8.model;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/10 16:37
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5),GOLD(10),PLATINUM(15),DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }

        public int getPercentage() {
            return percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        // 远程服务，模拟延时
        delay();
        return price * (100 - code.percentage) / 100;
    }

    private static void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
