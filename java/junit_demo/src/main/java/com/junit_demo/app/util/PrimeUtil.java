package com.junit_demo.app.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :  质数相关工具类
 * <p> create date :  2021/9/24 15:46
 */
public class PrimeUtil {

    public static boolean isPrime(int num) {
        int fac = (int)Math.sqrt(num);
        for (int i = 2; i <= fac; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeV2(int num) {
        int fac = (int)Math.sqrt(num);
        return IntStream.rangeClosed(2, fac).noneMatch(i -> num % i == 0);
//        return IntStream.rangeClosed(2, fac).parallel().noneMatch(i -> fac % i == 0);
    }

    public static boolean isPrimeV3(List<Integer> primes, int num) {
        int fac = (int)Math.sqrt(num);
        return takeWhile(primes, item -> item <= fac).stream().noneMatch(i -> num % i == 0);
    }

    private static List<Integer> takeWhile(List<Integer> primes, Predicate<Integer> test) {
        int i = 0;
        for (int i1 = 0; i1 < primes.size(); i1++) {
            if (!test.test(primes.get(i))) {
                return primes.subList(0, i);
            }
        }
        return primes;
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,15,16,17,22,27,31})
    @DisplayName("判断是否是质数")
    public void judge(int num) {
//        System.out.println("数字: " + num +", 是否是质数(isPrimeV3): " + isPrimeV3(new ArrayList<>(), num));
        System.out.println("数字: " + num +", 是否是质数(isPrimeV2): " + isPrimeV2(num));
        System.out.println("数字: " + num +", 是否是质数(isPrime): " + isPrime(num));
        Assertions.assertEquals(isPrime(num), isPrimeV2(num));
    }
}
