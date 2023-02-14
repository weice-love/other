package org.example.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/15 14:59
 */
public class ScheduledTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println(LocalTime.now().getMinute()), 1, 10, TimeUnit.SECONDS);
    }
}
