package com.ctf.service.impl;

import com.ctf.bean.User;
import com.ctf.dao.UserDao;
import com.ctf.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private UserDao userDao = new UserDao();

    @Override
    public User login(String username, String password) {
        return userDao.queryAUser(username,password);
    }

}
