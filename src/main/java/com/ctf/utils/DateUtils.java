package com.ctf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description :
 * @ClassName DateUtils
 * @Author tianfeichen
 * @Date 2021/8/26 03:12
 * @Version v1.0
 */
public class DateUtils {

    public static Date StringToDate(String date_str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date_str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //date对象转calendar对象
    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    //精确计算相隔天数
    public static Integer getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }
}
