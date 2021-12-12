package com.ctf.service;

import com.ctf.bean.User;

public interface LoginService {

    //处理登录业务
    public User login(String username,String password);


}
