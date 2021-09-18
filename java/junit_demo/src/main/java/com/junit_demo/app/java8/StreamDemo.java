package com.junit_demo.app.java8;

import com.alibaba.fastjson.JSON;
import com.junit_demo.app.util.PrintTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/15 10:16
 */
public class StreamDemo {

    @ParameterizedTest
    @ValueSource(strings = {"test", "prod", "dev"})
    @DisplayName("字符串拆解")
    public void splitStr(String str) {
        List<String> strings = Arrays.asList(str);
        List<Stream<String>> collect = strings.stream().map(e -> e.split("")).map(Stream::of).collect(Collectors.toList());
//        System.out.println("collect: " + JSON.toJSONString(strings.stream().map(e -> e.split("")).map(Stream::of).collect(Collectors.toList())));
        System.out.println("collect: " + JSON.toJSONString(strings.stream().map(e -> e.split("")).flatMap(Stream::of).collect(Collectors.toList())));
    }

    @Test
    @DisplayName("找到最大值")
    public void findMaxValue() {
        int[] nums = {1,2,4654,31241654,454,1521,313,465464,1};
        OptionalInt optionalInt = Arrays.stream(nums).reduce(Math::max);
        optionalInt.ifPresent(PrintTool::print);
    }

    @Test
    @DisplayName("求和")
    public void sumValue() {
        int[] nums = {1,2,4654,31241654,454,1521,313,465464,1};
        int sum = Arrays.stream(nums).reduce(0, Integer::sum);
        int sum2 = 0;
        for (int i = 0; i < nums.length; i++) {
            sum2 += nums[i];
        }
        PrintTool.print(sum);
        PrintTool.print(sum2);
        Assertions.assertEquals(sum2, sum, () -> "总和不相等");
    }

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
