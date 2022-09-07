package me.qinzc.jdk8.time;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * desc : jdk8 日期时间api测试
 *
 * @author Zane Qin
 * creatTime : 10:46 2022/8/16
 * modifier:
 * modifyTime:
 */
public class LocalDateTest {


    @Test
    public void LocalDateTest() {
        // 日期
        LocalDate date = LocalDate.of(2022, 8, 15);
        System.out.println(date.toString());
        LocalDate now = LocalDate.now();
        System.out.println(now);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(dow);
        System.out.println(len);
        System.out.println(leap);
        int year2 = date.get(ChronoField.YEAR);
        int month2 = date.get(ChronoField.MONTH_OF_YEAR);
        int day2 = date.get(ChronoField.DAY_OF_MONTH);
        // 时间
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(time);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
        LocalDate date2 = LocalDate.parse("2014-03-18");
        LocalTime time2 = LocalTime.parse("13:45:20");

        // 合并时间 2014-03-18T13:45:20
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        // 转化
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
        // 机器的时间
        Instant instant1 = Instant.now();
        Instant instant2 = Instant.now();

        Duration d1 = Duration.between(time1, time2);
        Duration d2 = Duration.between(dt1, dt2);
        Duration d3 = Duration.between(instant1, instant2);

        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays2 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

    @Test
    public void operate(){
        // 直观方式操作日期
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        // 相对方式操作日期
        LocalDate date11 = LocalDate.of(2014, 3, 18);
        LocalDate date22 = date11.plusWeeks(1);
        LocalDate date33 = date22.minusYears(3);
        LocalDate date44 = date33.plus(6, ChronoUnit.MONTHS);
    }

    /**
     * dayOfWeekInMonth     创建一个新的日期，它的值为同一个月中每一周的第几天
     * firstDayOfMonth      创建一个新的日期，它的值为当月的第一天
     * firstDayOfNextMonth  创建一个新的日期，它的值为下月的第一天
     * firstDayOfNextYear   创建一个新的日期，它的值为明年的第一天
     * firstDayOfYear       创建一个新的日期，它的值为当年的第一天
     * firstInMonth         创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
     * lastDayOfMonth       创建一个新的日期，它的值为当月的最后一天
     * lastDayOfNextMonth   创建一个新的日期，它的值为下月的最后一天
     * lastDayOfNextYear    创建一个新的日期，它的值为明年的最后一天
     * lastDayOfYear        创建一个新的日期，它的值为今年的最后一天
     * lastInMonth          创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
     * next/previous        创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期
     * nextOrSame/previousOrSame 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期，如果该日期已经符合要求，直接返回该对象
     */
    @Test
    public void temporalAdjuster(){
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
    }



}
