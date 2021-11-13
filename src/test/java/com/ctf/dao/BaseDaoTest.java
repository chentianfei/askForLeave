package com.ctf.dao;

import com.ctf.bean.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BaseDaoTest {
    BaseDao baseDao = new PersonDao();
    @Test
    public void update() {
    }

    @Test
    public void queryForOne() {
    }

    @Test
    public void queryForList() {
        String sql1 = "select * from person_info where 1=1";
        String sql2 = "select * from person_info where office='仁钦则乡人民政府'";
        String sql3 = "select * from person_info where 1=1 and area_class = ?";
        System.out.println(baseDao.queryForList(Person.class,sql1));
        System.out.println(baseDao.queryForList(Person.class,sql2));
        List<Object> parmas = new ArrayList<Object>();
        parmas.add("二类区");

        Object[] objects = new Object[]{
                "二类区"
        };
        System.out.println(sql3);
        System.out.println(parmas);
        System.out.println(baseDao.queryForList(Person.class,sql3,parmas.toArray()));

    }

    @Test
    public void queryForSingleValue() {
    }
}