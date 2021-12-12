package com.ctf.dao;

import com.ctf.bean.Nation;
import com.ctf.bean.Office;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SystemDataDaoTest {
    SystemDataDao systemDataDao = new SystemDataDao();
    @Test
    public void queryOffice() {

    }

    @Test
    public void queryNation() {
        List<Nation> nations = systemDataDao.queryNation();
        System.out.println(nations);
    }

    @Test
    public void querySmsAlertDays() {
        int i = systemDataDao.querySmsAlertDays();
        System.out.println(i);
    }

    @Test
    public void querySendObjStatusCode() {
        String objName = "doesSendLeader";
        System.out.println(systemDataDao.querySendObjStatusCode(objName));
    }


}