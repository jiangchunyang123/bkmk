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

    public static Long addMils(Long source, int type, int num) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(source / 1000, 0,
                ZoneOffset.ofHours(8));
        switch (type) {
            case 1:
                return dateTime.plusHours(num).toInstant(ZoneOffset.of("+8")).toEpochMilli();
            case 2:
                return dateTime.plusDays(num).toInstant(ZoneOffset.of("+8")).toEpochMilli();
            case 3:
                return dateTime.plusMonths(num).toInstant(ZoneOffset.of("+8")).toEpochMilli();
            case 4:
                return dateTime.plusYears(num).toInstant(ZoneOffset.of("+8")).toEpochMilli();
            default:
                return source;
        }
    }
}
