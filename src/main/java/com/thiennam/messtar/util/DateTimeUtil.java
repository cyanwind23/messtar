package com.thiennam.messtar.util;

import java.time.*;

public class DateTimeUtil {
    /**
     * Convert LocalDateTime to milliseconds
     * @param dateTime LocalDateTime
     * @return -1 if dateTime == null else long
     */
    public static long toMillis(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
        return -1L;
    }

    public static long toMillis(LocalDate date) {
        return toMillis(date == null ? null : LocalDateTime.of(date, LocalTime.of(0, 0, 0)));
    }
    /**
     * Convert milliseconds to LocalDateTime
     * @param millis - long
     * @return LocalDateTime if millis is valid else null
     */
    public static LocalDateTime toDateTime(long millis) {
        if (millis < 0) {
            return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return LocalDateTime.now();
    }
}
