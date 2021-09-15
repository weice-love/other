package com.junit_demo.app.java8;

import com.junit_demo.app.util.PrintTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description : 斐波那契数列
 * <p> create date :  2021/9/15 21:08
 */
public class FiboTest {

    @Test
    @DisplayName("生成斐波那契数列1")
    public void generateFibo() {
        Stream.iterate(new int[] {0, 1}, n -> new int[] {n[1], n[0] + n[1]}).limit(20).forEach(PrintTool::printArray);
    }

    @Test
    @DisplayName("生成斐波那契数列2")
    public void generateFibo2() {
        IntStream.generate(new IntSupplier() {
            int a = 0;
            int b = 1;
            @Override
            public int getAsInt() {
                int c = a + b;
                a = b;
                b = c;
                return a;
            }
        }).limit(20).forEach(PrintTool::print);
    }
}
