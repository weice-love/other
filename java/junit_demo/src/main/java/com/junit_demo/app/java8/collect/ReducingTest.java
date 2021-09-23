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