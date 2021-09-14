package com.zhongshi.oauth2.utils.watch;

import java.util.function.Supplier;

import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * 计时器模板类
 * 
 * @author chenminhui
 *
 */
@Slf4j
public class StopWatchTemplate {
	
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
	
	public static <T> T start(String taskId, Supplier<T> suplier) {
		StopWatch watch = new StopWatch(taskId);
		watch.start();
		try {
			return suplier.get();
		} finally {
			watch.stop();
			log.info("StopWatch '{}': running time = {} ms", watch.getId(), watch.getTotalTimeMillis());
		}
	}
}
