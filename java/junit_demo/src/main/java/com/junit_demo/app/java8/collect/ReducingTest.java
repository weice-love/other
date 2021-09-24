package com.junit_demo.app.java8.collect;

import com.junit_demo.app.annotion.ListSource;
import com.junit_demo.app.java8.model.Transaction;
import com.junit_demo.app.util.PrintTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p> @author     :  清风
 * <p> description : 归约
 * <p> create date :  2021/9/23 13:01
 */
public class ReducingTest {

    @ParameterizedTest
    @ListSource(resource = "transaction.json", clazz = Transaction.class)
    @DisplayName("归约使用")
    public void reducing(List<Transaction> transactions) {
        Integer sum = transactions.stream().collect(Collectors.reducing(0, Transaction::getValue, (i, j) -> i + j));
        PrintTool.print(sum);
        Optional<Transaction> maxTransaction = transactions.stream().collect(Collectors.reducing((a, b) -> (a.getValue() > b.getValue()) ? a : b));
        maxTransaction.ifPresent(PrintTool::print);
    }

    @Test
    @DisplayName("收集器")
    public void intCollect() {
        // 不可并行操作
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5).stream().reduce(new ArrayList<>(), (List<Integer> a, Integer b) -> {
            a.add(b);
            return a;
        }, (List<Integer> a, List<Integer> b) -> {
            a.addAll(b);
            return a;
        });
        List<Integer> collect = Arrays.asList(1, 2, 3, 4, 5).stream().collect(Collectors.toList());
    }
}
/*
工厂方法 返回类型 用 于
toList List<T> 把流中所有项目收集到一个 List
使用示例：List<Dish> dishes = menuStream.collect(toList());
toSet Set<T> 把流中所有项目收集到一个 Set，删除重复项
使用示例：Set<Dish> dishes = menuStream.collect(toSet());
toCollection Collection<T> 把流中所有项目收集到给定的供应源创建的集合
使用示例：Collection<Dish> dishes = menuStream.collect(toCollection(),
 ArrayList::new);
counting Long 计算流中元素的个数
使用示例：long howManyDishes = menuStream.collect(counting());
summingInt Integer 对流中项目的一个整数属性求和
使用示例：int totalCalories =
 menuStream.collect(summingInt(Dish::getCalories));
averagingInt Double 计算流中项目 Integer 属性的平均值
使用示例：double avgCalories =
 menuStream.collect(averagingInt(Dish::getCalories));
summarizingInt IntSummaryStatistics
收集关于流中项目 Integer 属性的统计值，例如最大、最小、
总和与平均值
使用示例：IntSummaryStatistics menuStatistics =
 menuStream.collect(summarizingInt(Dish::getCalories));
joining` String 连接对流中每个项目调用 toString 方法所生成的字符串
使用示例：String shortMenu =
 menuStream.map(Dish::getName).collect(joining(", "));
maxBy Optional<T>
一个包裹了流中按照给定比较器选出的最大元素的 Optional，
或如果流为空则为 Optional.empty()
使用示例：Optional<Dish> fattest =
 menuStream.collect(maxBy(comparingInt(Dish::getCalories)));
minBy Optional<T>
一个包裹了流中按照给定比较器选出的最小元素的 Optional，
或如果流为空则为 Optional.empty()
使用示例：Optional<Dish> lightest =
 menuStream.collect(minBy(comparingInt(Dish::getCalories)));
reducing 归约操作产生的类型 从一个作为累加器的初始值开始，利用 BinaryOperator 与流
中的元素逐个结合，从而将流归约为单个值
使用示例：int totalCalories =
 menuStream.collect(reducing(0, Dish::getCalories, Integer::sum));
collectingAndThen 转换函数返回的类型 包裹另一个收集器，对其结果应用转换函数
使用示例：int howManyDishes =
 menuStream.collect(collectingAndThen(toList(), List::size));
groupingBy Map<K, List<T>>
根据项目的一个属性的值对流中的项目作问组，并将属性值作
为结果 Map 的键
使用示例：Map<Dish.Type,List<Dish>> dishesByType =
 menuStream.collect(groupingBy(Dish::getType));
partitioningBy Map<Boolean,List<T>> 根据对流中每个项目应用谓词的结果来对项目进行分区
使用示例：Map<Boolean,List<Dish>> vegetarianDishes =
 menuStream.collect(partitioningBy(Dish::isVegetarian));
 */