package com.zhongshi.puppy.service.insurance.config;

import org.slf4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author :  清风
 * description :
 * create date :  2021/1/27 17:09
 */
public final class ThreadUtils {
    private static final int THREAD_MULTIPLER = 2;

    public ThreadUtils() {
    }

    public static void objectWait(Object object) {
        try {
            object.wait();
        } catch (InterruptedException var2) {
            Thread.interrupted();
        }

    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
        }

    }

    public static void countDown(CountDownLatch latch) {
        latch.countDown();
    }

    public static void latchAwait(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException var2) {
            Thread.currentThread().interrupt();
        }

    }

    public static void latchAwait(CountDownLatch latch, long time, TimeUnit unit) {
        try {
            latch.await(time, unit);
        } catch (InterruptedException var5) {
            Thread.currentThread().interrupt();
        }

    }

    public static int getSuitableThreadCount() {
        return getSuitableThreadCount(2);
    }

    public static int getSuitableThreadCount(int threadMultiple) {
        int coreCount = Runtime.getRuntime().availableProcessors();

        int workerCount;
        for(workerCount = 1; workerCount < coreCount * threadMultiple; workerCount <<= 1) {
        }

        return workerCount;
    }

    public static void shutdownThreadPool(ExecutorService executor) {
        shutdownThreadPool(executor, (Logger)null);
    }

    public static void shutdownThreadPool(ExecutorService executor, Logger logger) {
        executor.shutdown();
        int retry = 3;

        while(retry > 0) {
            --retry;

            try {
                if (executor.awaitTermination(1L, TimeUnit.SECONDS)) {
                    return;
                }
            } catch (InterruptedException var4) {
                executor.shutdownNow();
                Thread.interrupted();
            } catch (Throwable var5) {
                if (logger != null) {
                    logger.error("ThreadPoolManager shutdown executor has error : {}", var5);
                }
            }
        }

        executor.shutdownNow();
    }

    public static void addShutdownHook(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
    }
}
