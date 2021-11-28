package com.ctf.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void addAndSubtractDays() {

        String date_str  = "2021-11-16";
        Date date_date = DateUtils.StringToDate(date_str);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = DateUtils.addAndSubtractDays(date_date,-10);
                System.out.println(simpleDateFormat.format(newDate));

    }
}