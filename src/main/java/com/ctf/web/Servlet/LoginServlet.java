package com.ctf.web.Servlet;

import com.ctf.bean.Role;
import com.ctf.bean.User;
import com.ctf.dao.UserDao;
import com.ctf.service.LoginService;
import com.ctf.service.impl.LoginServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends BaseServlet{

    private LoginServiceImpl loginService = new LoginServiceImpl();
    private UserDao userDao = new UserDao();
    //处理登录功能
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = loginService.login(username, password);

        if(user != null){
            //用户存在，可以登录
            //1.绑定session数据
            request.getSession().setAttribute("user",user);
            //2.获取用户角色信息
            Role role = userDao.queryRoleInfoByUserId(user.getId());
            //3.重定向到人员管理页面
            if(role.getRole_name().equals("超级管理员") || role.getRole_name().equals("组织部")){
                response.sendRedirect(request.getContextPath()+"/pages/service/personinfomation/personinformation.jsp");
            }else {
                response.sendRedirect(request.getContextPath()+"/ordinaryPages/service/personinfomation/personinformation.jsp");
            }
        }else {
            //登录失败,回到登录页面，告知用户
            request.setAttribute("message","用户名或密码错误，请重新输入");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    //处理退出功能
    public void loginOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1、销毁Session中用户登录的信息（或者销毁Session）
        request.getSession().invalidate();
        //2、重定向到首页（或登录页面）。
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

}
