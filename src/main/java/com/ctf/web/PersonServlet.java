package com.ctf.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
*@Description :
*@ClassName PersonServlet
*@Author tianfeichen
*@Date 2021/8/21 16:49
*@Version v1.0
*/
public class PersonServlet extends BaseServlet{

    //绑定相关领导
    public void bindRelatedLeader(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("leaveKind","username");
        req.setAttribute("totalCount","totalCount");
        req.setAttribute("totalDays","totalDays");

        System.out.println("调用了PersonServlet的bindRelatedLeader");
    }

    //更新人员信息
    public void updatePersonInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用了PersonServlet的updatePersonInfo");
    }

    //删除人员信息
    public void deleteThePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用了PersonServlet的deleteThePerson");
    }

    //增加单个人员
    public void addOnePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用了PersonServlet的addOnePerson");
    }

}
