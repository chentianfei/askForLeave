package com.ctf.service.impl;

import com.ctf.dao.AskForLeaveDao;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class AskForLeaveServiceImplTest {

    AskForLeaveServiceImpl askForLeaveService = new AskForLeaveServiceImpl();
    AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    @Test
    public void queryPersonDetail() {

        int size = askForLeaveDao.queryLeaveInfoCountAtOneDay(new Date());
        System.out.println(size);


    }
}