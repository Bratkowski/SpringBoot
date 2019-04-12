package com.bratkowski.booklibary.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date addDaysToDate(Date date, Integer days) {

        if(date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            return calendar.getTime();
        }else
            return null;
    }
}