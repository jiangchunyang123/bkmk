package com.eve.bookmarks.utils;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static Long getTimeMils(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static Long nowMils() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static String getTimeMilsStr(LocalDateTime localDateTime) {
        return getTimeMils(localDateTime).toString();
    }

    public static LocalDateTime strToDate(String dateStr) {
        return strToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static LocalDateTime strToDate(String dateStr, String formatter) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(formatter));
    }

    public static String dateToStr(LocalDateTime localDateTime, String formatterStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterStr);
        return formatter.format(localDateTime);
    }

    public static LocalDateTime addMils(LocalDateTime dateTime, int type, int num) {

        switch (type) {
            case 1:
                return dateTime.plusHours(num);
            case 2:
                return dateTime.plusDays(num);
            case 3:
                return dateTime.plusMonths(num);
            case 4:
                return dateTime.plusYears(num);
            default:
                return dateTime;
        }
    }
}
