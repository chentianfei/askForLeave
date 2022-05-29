package com.ctf.dao;

import com.ctf.bean.Leader;
import com.ctf.bean.LeaveInfo;
import com.ctf.bean.LeaveInfoCount;
import com.ctf.bean.Person;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AskForLeaveDaoTest {

    AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    PersonDao personDao = new PersonDao();


    @Test
    public void queryRecentWorkDaysByPersonID() {

        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryResumeWorkInfoByDate(new Date(), 2);
        for (LeaveInfo leaveInfo:leaveInfoList) {
            System.out.println(leaveInfo);
        }

    }


}