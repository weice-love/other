package com.junit_demo.app.java8.partition;

import com.alibaba.fastjson.JSON;
import com.junit_demo.app.annotion.ListSource;
import com.junit_demo.app.java8.collector.PrimeNumbersCollector;
import com.junit_demo.app.java8.model.Menu;
import com.junit_demo.app.util.PrimeUtil;
import com.junit_demo.app.util.PrintTool;
import com.junit_demo.app.util.StopWatchTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :  分区用法
 * <p> create date :  2021/9/24 15:15
 */
public class PartitionTest {

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("分区测试(true | false)")
    public void partitionByCalories(List<Menu> menus) {
        Map<Boolean, List<Menu>> collect = menus.stream().collect(Collectors.partitioningBy(e -> e.getCalories() > 250));
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("分区测试(true | false)")
    public void partitionBy(List<Menu> menus) {
        Map<Boolean, Map<Integer, List<Menu>>> collect = menus.stream().collect(Collectors.partitioningBy(e -> e.getCalories() > 250, Collectors.groupingBy(Menu::getType)));
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ValueSource(ints = {200})
    @DisplayName("质数区分测试(true | false)")
    public void partitionByPrime(int limit) {
        Map<Boolean, List<Integer>> collect = IntStream.range(2, limit).boxed().collect(Collectors.partitioningBy(PrimeUtil::isPrimeV2));
        PrintTool.print(JSON.toJSONString(collect));
    }


    @ParameterizedTest
    @ValueSource(ints = {200})
    @DisplayName("质数区分测试(true | false) 使用自定义收集器")
    public void partitionByPrimeWithSelfCollector(int limit) {
        Map<Boolean, List<Integer>> collect = IntStream.range(2, limit).boxed().collect(new PrimeNumbersCollector());
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ValueSource(ints = {5000})
    @DisplayName("质数区分 时间比较")
    public void primeTimeCompare(int limit) {
        StopWatchTemplate.start("分区收集", () -> IntStream.range(2, limit).boxed().collect(Collectors.partitioningBy(PrimeUtil::isPrimeV2)));
        StopWatchTemplate.start("自定义收集器", () -> IntStream.range(2, limit).boxed().collect(new PrimeNumbersCollector()));
    }

}
