package com.ctf.utils;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.Person;
import com.ctf.dao.AskForLeaveDao;
import com.ctf.dao.PersonDao;
import com.ctf.dao.SystemDataDao;
import com.ctf.service.impl.PersonServiceImpl;
import com.ctf.web.Servlet.AskForLeaveServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUtilsTest {
    private static final String DATEFORMAT_YMD = "yyyy年MM月dd日";

    private static AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    private static PersonDao personDao = new PersonDao();
    private static PersonServiceImpl personService = new PersonServiceImpl();
    private static SystemDataDao systemDataDao = new SystemDataDao();
    WebUtils webUtils = new WebUtils();

    AskForLeaveServlet askForLeaveServlet = new AskForLeaveServlet();

    @Test
    public void parseInt() {

    }

    @Test
    public void stringListTostringArray(){


    }

    @Test
    public void queryDataByConfidition() {

    }

    @Test
    public void sendMsg() {
    }

    @Test
    public void exportToExcel() {
    }

    @Test
    public void mapToObject() throws InvocationTargetException, IllegalAccessException {
        Map<String,Object> map = new HashMap<>();

        map.put("name","陈天飞");
        map.put("sex","男");
        map.put("nation","汉族");
        map.put("birthDate","2017-11-15");
        map.put("nativePlace","四川省眉山市彭山区");
        map.put("office","政府办");
        map.put("post","科员");
        map.put("area_class","二类区");
        map.put("level","一级科员");
        map.put("phone","18089922014");
        map.put("allow_Leave_Days","50");

        Person person = new Person();

        BeanUtils.populate(Person.class, map);
        System.out.println(person);
    }

}