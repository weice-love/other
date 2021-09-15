package com.junit_demo.app.java8;

import com.junit_demo.app.util.PrintTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    @Test
    @DisplayName("数组转流")
    public void array2Stream() {
        int[] numbers = {1,4,9,16,25,36,49,64,81};
        // 区别
        Stream<int[]> numbers1 = Stream.of(numbers);
        IntStream stream = Arrays.stream(numbers);
        System.out.println("sum: " + stream.sum());
    }

    @Test
    @DisplayName("文件转流")
    public void file2Stream() throws IOException {

        System.out.println("相对路径");
        try ( Stream<String> stream = Files.lines(Paths.get("src/main/resources/data.txt"), StandardCharsets.UTF_8).flatMap(e -> Arrays.stream(e.split(" ")))) {
            stream.distinct().forEach(System.out::println);
        } catch (Exception e) {

        }
        System.out.println("==========================");
        System.out.println("通过资源路径");
        Enumeration<URL> resources = this.getClass().getClassLoader().getResources("data.txt");
        URL url = resources.nextElement();
        try ( Stream<String> stream = Files.lines(Paths.get(url.toURI()), StandardCharsets.UTF_8).flatMap(e -> Arrays.stream(e.split(" ")))) {
            stream.distinct().forEach(System.out::println);
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("无限流")
    public void end2Stream() {
        Stream.iterate(1, n -> n + 1).limit(100).forEach(PrintTool::print);
    }
}
