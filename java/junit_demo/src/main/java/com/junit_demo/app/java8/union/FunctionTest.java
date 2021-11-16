package com.junit_demo.app.java8.union;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :  函数式编程实战
 * <p> create date :  2021/11/12 11:49
 */
public class FunctionTest {

    private static final Logger log = LoggerFactory.getLogger(FunctionTest.class);

    @DisplayName("取出子集")
    @Test
    public void getSubList() {

//        Function<Double, Double> TEST = (Double x) -> x * x;
        List<Integer> collect = IntStream.rangeClosed(1, 3).boxed().collect(Collectors.toList());
        log.info("{}", collect);
        log.info("{}", subsets(collect));
    }

    private List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<List<Integer>> subans = subsets(list.subList(1, list.size()));
        List<List<Integer>> subans2 = insertAll(first, subans);
        return concat(subans, subans2);
    }

    private List<List<Integer>> concat(List<List<Integer>> subans, List<List<Integer>> subans1) {
//        List<List<Integer>> ans = new ArrayList<>();
//        ans.addAll(subans);
        List<List<Integer>> ans = new ArrayList<>(subans);
        ans.addAll(subans1);
        return ans;
    }

    private List<List<Integer>> insertAll(Integer first, List<List<Integer>> subans) {
        List<List<Integer>> ans = new ArrayList<>();
        subans.forEach(e -> {
            ans.add(new ArrayList<>(e));
        });
        ans.forEach(e -> e.add(first));
        return ans;
    }


}
