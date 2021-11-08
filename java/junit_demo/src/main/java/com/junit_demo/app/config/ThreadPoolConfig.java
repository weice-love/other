package com.junit_demo.app.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p> @author     :  清风
 * <p> description :
 *    线程数计算公式
 *    Nthreads = NCPU * UCPU * (1 + W/C)
 *      NCPU是处理器的核的数目，可以通过Runtime.getRuntime().availableProcessors()得到
 *      UCPU是期望的CPU利用率（该值应该介于0和1之间）
 *      W/C是等待时间与计算时间的比率
 *
 * <p> create date :  2021/11/8 17:38
 */
public class ThreadPoolConfig {

    private static final ThreadPoolExecutor INSTANCE;

    static {
        INSTANCE = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() - 2,
                Runtime.getRuntime().availableProcessors() * 2,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8));
    }

    private ThreadPoolConfig() {}

    public static ThreadPoolExecutor getINSTANCE() {
        return INSTANCE;
    }
}
