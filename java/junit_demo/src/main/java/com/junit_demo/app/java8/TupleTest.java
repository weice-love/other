package com.junit_demo.app.java8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/14 17:44
 */
public class TupleTest {

    private final int[] tuple = new int[3];

    @Test
    @DisplayName("生成勾股数")
    public void generateTuple() {
        IntStream.range(1, 100).boxed().flatMap(a -> {
            return IntStream.range(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> {
                //    new int[3] {a, b, (int)Math.sqrt(a * a + b * b)}
                int[] tuples = new int[3];
                tuples[0] = a;
                tuples[1] = b;
                tuples[2] = (int)Math.sqrt(a * a + b * b);
                return tuples;
//                return new int[3] {a, b, (int)Math.sqrt(a * a + b * b)};
            });
        }).forEach(e -> {

            System.out.print(e[0]);
            System.out.print(", " +e[1]);
            System.out.println(", " + e[2]);
        });
    }
}
