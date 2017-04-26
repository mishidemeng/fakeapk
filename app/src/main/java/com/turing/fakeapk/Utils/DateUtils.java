package com.turing.fakeapk.Utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 获得当天0点时间
     */
    public static Date getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得当天24点时间
     */
    public static Date getTimesNight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得昨天0点时间
     */
    public static Date getYesterdayMorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesMorning().getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }

    /**
     * 获得昨天24点时间
     */
    public static Date getYesterdayNight() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesNight().getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }

    /**
     * 获得明天0点时间
     */
    public static Date getTomorrowMorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesMorning().getTime() + 3600 * 24 * 1000);
        return cal.getTime();
    }

    /**
     * 获得明天24点时间
     */
    public static Date getTomorrowNight() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesNight().getTime() + 3600 * 24 * 1000);
        return cal.getTime();
    }

    /**
     * 获得当天近7天时间
     */
    public static Date getWeekFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesMorning().getTime() - 3600 * 24 * 1000 * 7);
        return cal.getTime();
    }
}
