package com.junit_demo.app.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/15 11:32
 */
public class PrintTool {

    public static <T> void print(T t) {
        System.out.print(t + ",");
    }

    public static void printArray(int[] array) {
        System.out.print("(");
        System.out.print(Arrays.stream(array).boxed().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.print(")");
    }
}
