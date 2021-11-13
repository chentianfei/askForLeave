package com.ctf.dao;

import com.ctf.bean.Office;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SystemDataDaoTest {
    SystemDataDao systemDataDao = new SystemDataDao();
    @Test
    public void queryOffice() {
        List<Office> offices = systemDataDao.queryOffice();
        System.out.println(offices);
    }
}