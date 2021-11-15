package com.ctf.utils;

import com.ctf.bean.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WebUtilsTest {

    WebUtils webUtils = new WebUtils();

    @Test
    public void parseInt() {
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

    @Test
    public void generateAPersonId() {
        Person person = new Person();
        person.setName("夏雨婷");
        person.setPhone("18089922104");
        System.out.println(WebUtils.generateAPersonId(person));
    }
}