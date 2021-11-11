package com.junit_demo.app.java8.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * <p> @author     :  清风
 * <p> description :  日期使用
 *      1. Temporal接口定义了如何读取和操纵为时间建模的对象的值
 *      2. 由于LocalDateTime和Instant是为不同的目的而设计的，一个是为了便于人阅读使用，另一个是为了便于机器处理，所以你不能将二者混用
 * <p> create date :  2021/11/11 13:48
 */
public class DateTest {

    private static final Logger log = LoggerFactory.getLogger(DateTest.class);

    @DisplayName("修改时间")
    @Test
    public void modify() {
        // 使用with
        LocalDate localDate = LocalDate.of(2021, 11, 11);
        log.info("address: {}", System.identityHashCode(localDate));
        LocalDate localDate1 = localDate.withYear(2022);
        log.info("address1: {}", System.identityHashCode(localDate1));
        LocalDate date4 = localDate1.with(ChronoField.MONTH_OF_YEAR, 9);

        // 以相对方式修改
        LocalDate localDate2 = localDate.plusDays(10);
        LocalDate localDate3 = localDate2.plus(11, ChronoUnit.DAYS);

    }
/*
        from 是 依据传入的 Temporal 对象创建对象实例
        now 是 依据系统时钟创建 Temporal 对象
        of 是 由 Temporal 对象的某个部分创建该对象的实例
        parse 是 由字符串创建 Temporal 对象的实例
        atOffset 否 将 Temporal 对象和某个时区偏移相结合
        atZone 否 将 Temporal 对象和某个时区相结合
        format 否 使用某个指定的格式器将Temporal对象转换为字符串（Instant类不提供该方法）
        get 否 读取 Temporal 对象的某一部分的值
        minus 否 创建 Temporal 对象的一个副本，通过将当前 Temporal 对象的值减去一定的时长
        创建该副本
        plus 否 创建 Temporal 对象的一个副本，通过将当前 Temporal 对象的值加上一定的时长
        创建该副本
        with 否 以该 Temporal 对象为模板，对某些状态进行修改创建该对象的副本
 */


    @DisplayName("Duration 或 Period使用")
    @Test
    public void durationTest() {
        // durantion 主要是以秒和纳秒衡量时间的长短
        Duration d1 = Duration.between(LocalTime.of(12,12), LocalTime.of(13,13));
        Duration d2 = Duration.between(LocalDateTime.of(2021,11,11, 11, 11),
                LocalDateTime.of(2021,11,11, 22, 22));
        Duration d3 = Duration.between(Instant.ofEpochSecond(1), Instant.ofEpochSecond(10));
        log.info("d1: {}", d1.getSeconds());
        log.info("d2: {}", d2.getSeconds());
        log.info("d3: {}", d3.getSeconds());
        // Period是以年，月，日对多个时间单为建模
        Period oneYear = Period.between(LocalDate.of(2020, 11, 12), LocalDate.of(2021, 11, 11));
        log.info("days: {}", oneYear.getDays());
        log.info("months: {}", oneYear.getMonths());
        log.info("years: {}", oneYear.getYears());

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration _threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }
    /*
        日期-时间类中表示时间间隔的通用方法
            between 是 创建两个时间点之间的 interval
            from 是 由一个临时时间点创建 interval
            of 是 由它的组成部分创建 interval 的实例
            parse 是 由字符串创建 interval 的实例
            addTo 否 创建该 interval 的副本，并将其叠加到某个指定的 temporal 对象
            get 否 读取该 interval 的状态
            isNegative 否 检查该 interval 是否为负值，不包含零
            isZero 否 检查该 interval 的时长是否为零
            minus 否 通过减去一定的时间创建该 interval 的副本
            multipliedBy 否 将 interval 的值乘以某个标量创建该 interval 的副本
            negated 否 以忽略某个时长的方式创建该 interval 的副本
            plus 否 以增加某个指定的时长的方式创建该 interval 的副本
            subtractFrom 否 从指定的 temporal 对象中减去该 interval
     */

    @DisplayName("Instant使用")
    @Test
    public void instantTest() {
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        // 2秒之后再加100万纳秒
        Instant.ofEpochSecond(2, 1_000_000_000);
        // 4秒之前的100万纳秒
        Instant.ofEpochSecond(4, -1_000_000_000);
        // 会抛出异常，机器识别不了人类的时间
        log.info("DAY_OF_MONTH: {}", Instant.now().get(ChronoField.DAY_OF_MONTH));
    }

    @DisplayName("LocalDateTime使用")
    @Test
    public void localDateTimeTest() {
        // 不带时区信息
        LocalDate date = LocalDate.of(2014, 3, 18);
        LocalTime time = LocalTime.of(13, 45, 20);
        // 2014-03-18T13:45:20
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
    }

    @DisplayName("日期使用")
    @Test
    public void test() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        // 从工厂方法返回日期
        LocalDate today = LocalDate.now();

        // 使用TemporalField参数， 获取信息
        int day_of_month = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        LocalDate _date = LocalDate.parse("2014-03-18");
        LocalTime _time = LocalTime.parse("13:45:20");
        LocalTime now = LocalTime.now();
    }
}
