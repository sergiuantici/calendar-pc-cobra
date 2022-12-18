package com.example.calendar.Utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DAY_OF_MONTH;

@Service
public class DateUtils {


    public String getStringDate(LocalDateTime date, String dateTimeFormat){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(dateTimeFormat);
        return dateFormat.format(date);
    }

    public Date getFirstDayOfTheMonth(Integer month, Integer year) {
        LocalDate date = LocalDate.of(year, month, 1);
        return convertToDate(date);
    }

    public Date getLastDayOfTheMonth(Integer month, Integer year){
        LocalDate date = LocalDate.of(year,month,1);
        LocalDate lastDay= date.with(TemporalAdjusters.lastDayOfMonth());
        return convertToDate(lastDay);
    }
    public Date convertToDate(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Integer getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(DAY_OF_MONTH);
    }

    public List<Integer> getAllDaysFromMonth(Integer month, Integer year) {
        List<Integer> days = new ArrayList<>();
        Date firstDayOfMonth = getFirstDayOfTheMonth(month, year);
        Date lastDayOfMonth = getLastDayOfTheMonth(month, year);
        Calendar start = Calendar.getInstance();
        start.setTime(firstDayOfMonth);
        Calendar end = Calendar.getInstance();
        end.setTime(lastDayOfMonth);
        for (Date current = start.getTime(); start.before(end) || start.compareTo(end) == 0; start.add(Calendar.DATE, 1), current = start.getTime()) {
            Integer dayOfMonth = getDayOfMonth(current);
            days.add(dayOfMonth);
        }
        return days;
    }
}
