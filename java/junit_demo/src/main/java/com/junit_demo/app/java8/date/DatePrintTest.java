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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;


/**
 * <p> @author     :  清风
 * <p> description :  日期打印
 * <p> create date :  2021/11/11 13:48
 */
public class DatePrintTest {

    private static final Logger log = LoggerFactory.getLogger(DatePrintTest.class);

    @DisplayName("日期打印功能")
    @Test
    public void printDateTest() {
        // 打印
        LocalDate now = LocalDate.of(2021, 3, 18);
        log.info("BASIC ISO DATE format: {}", now.format(DateTimeFormatter.BASIC_ISO_DATE));
        log.info("ISO LOCAL DATE format: {}", now.format(DateTimeFormatter.ISO_LOCAL_DATE));

        // 利用字符串创建日期实例
        LocalDate localDate = LocalDate.parse("20211111", DateTimeFormatter.BASIC_ISO_DATE);
        log.info("localDate: {}", localDate);

        // 按照某个模式创建dateTimeFormatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        log.info("yyyy/MM/dd format: {}", now.format(dateTimeFormatter));

        // 创建本地化的dateTimeFormatter
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        log.info("d. MMMM yyyy format: {}", now.format(dateTimeFormatter1));

        // 使用DateTimeFormatterBuilder
        DateTimeFormatter dateTimeFormatter2 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        log.info("自定义format: {}", now.format(dateTimeFormatter2));


    }

}
