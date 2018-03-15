package com.kevin.snake.bootlicense.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName: TimeDiff
 * @Description:JAVA8新引入的LocalDate类型 比较传入的日期与系统当前日期间隔天数，至到期日23点59分59秒以前均不会过期
 * 大于或者等于0，未到期                  小于0：到期
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午9:03:01
 */
public class TimeDiff {

    public static int countDay(String duedate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateend = LocalDate.parse(duedate, formatter);

        LocalDate datenow = LocalDate.now();

        Period periodToduedate = Period.between(datenow, dateend);

        return periodToduedate.getDays();

    }
}
