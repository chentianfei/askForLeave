package com.ctf.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDao();

    @Test
    public void queryNameById() {
        System.out.println(userDao.queryNameById(1));
    }

    @Test
    public void queryPWDByUserID() {

        System.out.println(userDao.isPWDCorrect("xwzzb123456",2));

    }
}