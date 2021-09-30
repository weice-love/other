package com.junit_demo.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.function.Supplier;

/**
 * 计时器模板类
 */
public class StopWatchTemplate {

	private static final Logger log = LoggerFactory.getLogger(StopWatchTemplate.class);
	
	public static void start(String taskId, Runnable runnable) {
		StopWatch watch = new StopWatch(taskId);
		watch.start();
		try {
			runnable.run();
		} finally {
			watch.stop();
			log.info("StopWatch '{}': running time = {} ms", watch.getId(), watch.getTotalTimeMillis());
		}
	}
	
	public static <T> T start(String taskId, Supplier<T> supplier) {
		StopWatch watch = new StopWatch(taskId);
		watch.start();
		try {
			return supplier.get();
		} finally {
			watch.stop();
			log.info("StopWatch '{}': running time = {} ms", watch.getId(), watch.getTotalTimeMillis());
		}
	}

	public static <T> long startAndReturnRunTime(String taskId, Supplier<T> supplier) {
		StopWatch watch = new StopWatch(taskId);
		watch.start();
		try {
			supplier.get();
		} finally {
			watch.stop();
			log.info("StopWatch '{}': running time = {} ms", watch.getId(), watch.getTotalTimeMillis());
		}
		return watch.getTotalTimeMillis();
	}
}
