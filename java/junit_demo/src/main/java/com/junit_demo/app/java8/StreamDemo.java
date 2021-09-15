package com.junit_demo.app.java8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/15 10:16
 */
public class StreamDemo {

    @Test
    @DisplayName("值转流")
    public void value2Stream() {
        System.out.println("value2Stream: " + Stream.of("Hello", "World").collect(Collectors.joining(" ")));
    }
}
