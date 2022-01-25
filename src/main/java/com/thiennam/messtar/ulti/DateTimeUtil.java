package com.thiennam.messtar.ulti;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {
    /**
     * Convert LocalDateTime to milliseconds
     * @param dateTime LocalDateTime
     * @return -1 if dateTime == null else long number
     */
    public static long toMilis(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
        return -1L;
    }
}
