package org.baeldung.util;

import java.time.Instant;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;

import static java.time.Instant.ofEpochMilli;
import static java.time.temporal.ChronoUnit.SECONDS;

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

    public static void main(String[] args) {
        long startTimeOriginal = System.currentTimeMillis();
        long endTimeOriginal = TimeUnit.HOURS.toMillis(6) + startTimeOriginal;
        System.out.println(ofEpochMilli(startTimeOriginal).truncatedTo(SECONDS) + " || " + ofEpochMilli(endTimeOriginal).truncatedTo(SECONDS));
        Calendar startCalender = RoundTime.getCalendar(startTimeOriginal, 30);
        Calendar endCalender = RoundTime.getCalendar(endTimeOriginal, 30);
        Instant x = ofEpochMilli(truncateTime((a, b) -> a <= b, startCalender.getTimeInMillis(), startTimeOriginal, startCalender, -1));
        System.out.println(x + " || " + ofEpochMilli(truncateTime((a, b) -> a > b, endCalender.getTimeInMillis(), endTimeOriginal, endCalender, 1)));
    }

}
