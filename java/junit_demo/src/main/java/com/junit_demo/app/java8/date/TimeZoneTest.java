package com.junit_demo.app.java8.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.zone.ZoneRules;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;


/**
 * <p> @author     :  清风
 * <p> description :  时区，历法处理
 *      关系如下
 *      | LocalDate | LocalTime | ZoneId |
 *      |      LocalDateTime    |
 *      |        ZonedDateTime           |
 * <p> create date :  2021/11/11 13:48
 */
public class TimeZoneTest {

    private static final Logger log = LoggerFactory.getLogger(TimeZoneTest.class);

    @DisplayName("时区")
    @Test
    public void printDateTest() {
        //
        // ZoneRules zoneRules = ZoneRules.of(ZoneOffset.of("GMT+8"));
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        // 获取所有时区
//        availableZoneIds.stream().forEach(System.out::println);
        // 老的时区转换成ZoneId
        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        // 为时间点添加时区信息
        ZoneId shanghai = ZoneId.of("Asia/Shanghai");
        LocalDate localDate = LocalDate.of(2021, Month.NOVEMBER, 11);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(shanghai);
        log.info("{}", zonedDateTime);
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.NOVEMBER, 11, 16, 40, 20);
        ZonedDateTime zonedDateTime1 = localDateTime.atZone(shanghai);
        log.info("{}", zonedDateTime1);
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime2 = now.atZone(shanghai);
        log.info("{}", zonedDateTime2);

        //
    }

    @DisplayName("LocalDateTime 和 Instant 互相转化")
    @Test
    public void transfer() {
        ZoneId shanghai = ZoneId.of("Asia/Shanghai");

        // LocalDateTime => Instant
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        dateTime.toInstant(ZoneOffset.of("+8"));
        // 或者
        dateTime.toInstant(ZoneOffset.ofHours(8));

        // Instant => LocalDateTime
        Instant now = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, shanghai);
        // 测试小绿格
    }

}
