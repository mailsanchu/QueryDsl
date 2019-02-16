package org.baeldung.util;

import java.util.Calendar;
import java.util.function.BiPredicate;

public class RoundTime {

    public static Long truncateTime(BiPredicate<Long, Long> longBiPredicate, long calculatedTime, long originalTime, Calendar calendar, int delta) {
        if (longBiPredicate.test(calculatedTime, originalTime)) {
            return calculatedTime;
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + delta);
            return calendar.getTimeInMillis();
        }
    }

    public static Calendar getCalendar(Long originalStartTimeTime, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(originalStartTimeTime);
        calendar.set(Calendar.MINUTE, value);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

}
