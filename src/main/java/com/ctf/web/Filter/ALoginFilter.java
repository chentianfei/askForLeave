package com.ctf.web.Filter;

import com.ctf.bean.Role;
import com.ctf.bean.User;
import com.ctf.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class ALoginFilter implements Filter {

    private UserDao userDao = new UserDao();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //doFilter方法中的请求和响应对象都是ServletRequest，ServletResponse类型的，这里需要强转一下来获取
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //返回除去host（域名或者ip）部分的路径
        String uri = req.getRequestURI();
        String actual_url = req.getServletPath()+req.getQueryString();
        /*
        String getServerName()：获取服务器名，localhost；
        String getServerPort()：获取服务器端口号，8080；
        String getContextPath()：获取项目名，/Example；
        String getServletPath()：获取Servlet路径，/AServlet；
        String getQueryString()：获取参数部分，即问号后面的部分：username=zhangsan
        String getRequestURI()：获取请求URI，等于项目名+Servlet路径：/Example/AServlet
        String getRequestURL()：获取请求URL，等于不包含参数的整个请求路径：http://localhost:8080/Example/AServlet
        String request.getRemoteAddr()：获取服务器的IP，如localhost对应ip为127.0.0.1
        * */

        //如果获取到的路径包含这以下几个：登录页面、登录的servlet、css样式、js代码、验证码实现类，则不拦截放行，如果少了哪个的话，页面会显示不完整，过滤器就会把没有写到下面的资源给拦截了
        if(uri.contains("login.jsp")
                || uri.contains("/loginServlet")
                || uri.contains("/static/")
                || uri.contains("favicon.ico")
                || actual_url.contains("/askForLeaveServletaction=queryALeaveInfoForPrintBySerialnumberForQRCode&serialnumber=")
        ){
            //访问登录资源可以直接访问：放行
            chain.doFilter(request, response);
        }else {
            //不是访问登录资源：验证登录状态与身份
            //说明请求的不是登录页面，此时要判断用户登没登陆来确定给不给他访问资源
            User user = (User)req.getSession().getAttribute("user");//这里是登录返回过来的User实体类对象

            if (user == null) {
                //user为null说明没登陆
                //要跳转到登录页面
                // 没有登录，退回到登录页面并告知用户，返回代码1表示需要登录
                req.getSession().setAttribute("ErrorCode",1);
                req.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                //否则说明用户登录了：放行，由下一个过滤器执行权限验证
                chain.doFilter(request, response);
            }
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}