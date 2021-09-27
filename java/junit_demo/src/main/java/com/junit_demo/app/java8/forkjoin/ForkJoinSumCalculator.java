package com.junit_demo.app.java8.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * <p> @author     :  清风
 * <p> description :  fork/join使用
 * <p> create date :  2021/9/27 16:56
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator left = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        left.fork();
        ForkJoinSumCalculator right = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightSum = right.compute();
        Long leftSum = left.join();
        return rightSum + leftSum;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
