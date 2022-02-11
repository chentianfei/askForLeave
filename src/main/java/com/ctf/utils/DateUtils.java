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

    public static Date StringToDate(String date_str,String formatSTR) {
        try {
            return new SimpleDateFormat(formatSTR).parse(date_str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //返回当前年份的字符串
    public static String getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    //date对象转calendar对象
    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    //date类的加减法
    public static Date addAndSubtractDays(Date date,int days){
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        if(days > 0){
            days--;
        }else if(days < 0){
            days++;
        }
        cal.add(Calendar.DATE,days);
        date = cal.getTime();
        return date;
    }

    //精确计算相隔天数String
    public static Integer getDaysBetween(String dateSTR1, String dateSTR2,String formatSTR) {
        Date date1 = StringToDate(dateSTR1, formatSTR);
        Date date2 = StringToDate(dateSTR2, formatSTR);
        return getDaysBetween(date1,date2);
    }

    //精确计算相隔天数Date
    public static Integer getDaysBetween(Date date1, Date date2) {
        Calendar d1 = DateToCalendar(date1);
        Calendar d2 = DateToCalendar(date2);
        return getDaysBetween(d1, d2);
    }

    //精确计算相隔天数Calendar
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

    //精确计算相隔天数Calendar:加1类型
    public static Integer getDaysBetweenPlusOne(Calendar d1, Calendar d2) {
        return getDaysBetween(d1,d2)+1;
    }

    //精确计算相隔天数Date:加1类型
    public static Integer getDaysBetweenPlusOne(Date date1, Date date2) {
        Calendar d1 = DateToCalendar(date1);
        Calendar d2 = DateToCalendar(date2);
        return getDaysBetweenPlusOne(d1,d2);
    }

    //精确计算相隔天数String:加1类型
    public static Integer getDaysBetweenPlusOne(String dateSTR1, String dateSTR2,String formatSTR) {
        Date date1 = StringToDate(dateSTR1, formatSTR);
        Date date2 = StringToDate(dateSTR2, formatSTR);
        return getDaysBetweenPlusOne(date1,date2);
    }

    //将带小时格式的日期转为UNIX 时间戳（时间：秒）。
    public static long getSecondByDateStr_yyyyMMddHH(String dateSTR) throws ParseException {
        Date beginTimeDate = new SimpleDateFormat("yyyyMMddHH").parse(dateSTR);
        return beginTimeDate.getTime()/1000;
    }

}
