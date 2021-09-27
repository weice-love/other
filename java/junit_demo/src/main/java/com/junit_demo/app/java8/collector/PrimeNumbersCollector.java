package com.junit_demo.app.java8.collector;

import com.junit_demo.app.util.PrimeUtil;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (result, item) -> {
            result.get(PrimeUtil.isPrimeV3(result.get(true), item)).add(item);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        // 因为是顺序执行，永远不会执行到当前方法（留空 | 抛出异常）
        return (result1, result2) -> {
            result1.get(true).addAll(result2.get(true));
            result1.get(false).addAll(result2.get(false));
            return result1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
