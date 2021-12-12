package com.ctf.dao;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.LeaveInfoCount;
import com.ctf.bean.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AskForLeaveDaoTest {

    AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    PersonDao personDao = new PersonDao();


    @Test
    public void queryRecentWorkDaysByPersonID() {
        System.out.println(askForLeaveDao.queryRecentWorkDaysByPersonID(12));
    }
}