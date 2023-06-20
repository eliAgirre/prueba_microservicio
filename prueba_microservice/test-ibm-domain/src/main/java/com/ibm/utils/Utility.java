package com.ibm.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class Utility {

    public LocalDateTime getLocalDateTimeFromString(String localDateTime, String formatPattern) {
        DateTimeFormatter formatter = getDataTimeFormatter(formatPattern);
        return LocalDateTime.parse(localDateTime, formatter);
    }

    public DateTimeFormatter getDataTimeFormatter(String formatPattern) {
        return DateTimeFormatter.ofPattern(formatPattern);
    }

    public static LocalDateTime getLocalDateTimeNow(){ return LocalDateTime.now(); }
}
