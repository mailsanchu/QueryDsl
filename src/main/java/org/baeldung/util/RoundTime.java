package org.baeldung.util;

import java.time.Instant;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;

import static java.time.Instant.ofEpochMilli;
import static java.time.temporal.ChronoUnit.SECONDS;

public class RoundTime {

    private static Long truncateTime(BiPredicate<Long, Long> longBiPredicate, long calculatedTime, long originalTime, Calendar calendar, int delta) {
        if (longBiPredicate.test(calculatedTime, originalTime)) {
            return calculatedTime;
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + delta);
            return calendar.getTimeInMillis();
        }
    }

    private static Calendar getCalendar(Long originalStartTimeTime, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(originalStartTimeTime);
        calendar.set(Calendar.MINUTE, value);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private static String millisToString(long millis) {
        if (millis <= 0) {
            return "Input was " + millis;
        }
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) - (hours * 60);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - ((hours * 60 * 60) + (minutes * 60));
        millis = millis - ((hours * 60 * 60 * 1000) + (minutes * 60 * 1000) + (seconds * 1000));
        StringBuilder stringBuilder = new StringBuilder();
        appendUnit(hours, stringBuilder, "", "hr");
        appendUnit(minutes, stringBuilder, " ", "min");
        appendUnit(seconds, stringBuilder, " ", "sec");
        if (millis > 0) {
            stringBuilder.append(String.format(" %d ms", millis));
        }
        return stringBuilder.toString();
    }

    private static void appendUnit(long time, StringBuilder stringBuilder, String prefix, String s2) {
        if (time > 0) {
            stringBuilder.append(String.format(prefix + "%d " + (time > 1 ? s2 + "s" : s2), time));
        }
    }

    public static void main(String[] args) {
        long startTimeOriginal = System.currentTimeMillis();
        long endTimeOriginal = TimeUnit.HOURS.toMillis(6) + startTimeOriginal;
        System.out.println(ofEpochMilli(startTimeOriginal).truncatedTo(SECONDS) + " || " + ofEpochMilli(endTimeOriginal).truncatedTo(SECONDS));
        Calendar startCalender = RoundTime.getCalendar(startTimeOriginal, 30);
        Calendar endCalender = RoundTime.getCalendar(endTimeOriginal, 30);
        Long epochMilli = truncateTime((a, b) -> a <= b, startCalender.getTimeInMillis(), startTimeOriginal, startCalender, -1);
        Instant x = ofEpochMilli(epochMilli);
        Long epochMilli1 = truncateTime((a, b) -> a > b, endCalender.getTimeInMillis(), endTimeOriginal, endCalender, 1);
        System.out.println(x + " || " + ofEpochMilli(epochMilli1));
        System.out.println(millisToString(startTimeOriginal - epochMilli).trim().toUpperCase() + " || " + millisToString(epochMilli1 - endTimeOriginal).trim().toUpperCase());

        System.out.println(millisToString(5130009));

    }

}
