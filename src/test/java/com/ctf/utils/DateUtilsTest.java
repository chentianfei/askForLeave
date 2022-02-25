package com.ctf.utils;

import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {
    private static final String YMDHMSFORMAT = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void addAndSubtractDaystest() {
        System.out.println(DateUtils.addAndSubtractDays(new Date(),-2));
    }
}