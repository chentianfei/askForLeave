package com.ctf.utils;

import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void addAndSubtractDays() {
    }

    @Test
    public void getDaysBetween() throws ParseException {
        Date start_date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-8-25");
        Date end_date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-7-20");
        System.out.println(DateUtils.getDaysBetweenPlusOne(start_date,end_date));
    }

    @Test
    public void testGetDaysBetween() {
    }
}