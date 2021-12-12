package com.ctf.web.Filter;
import com.ctf.bean.Role;
import com.ctf.bean.User;
import com.ctf.dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/pages/*")
public class BAuthorityFilter implements Filter {

    private UserDao userDao = new UserDao();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //获取用户信息
        User user = (User)req.getSession().getAttribute("user");
        //获取用户对应的角色信息
        Role roleInfo = userDao.queryRoleInfoByUserId(user.getId());

        //判断是否是超级管理员
        if(!roleInfo.getRole_name().equals("超级管理员") && !roleInfo.getRole_name().equals("组织部")){
            //不是超级管理员
            req.getRequestDispatcher("/ordinaryPages/service/personinfomation/personinformation.jsp").forward(request,response);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
