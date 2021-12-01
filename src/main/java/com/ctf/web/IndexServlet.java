package com.ctf.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*@Description :
*@ClassName IndexServlet
*@Author tianfeichen
*@Date 2021/8/21 16:10
*@Version v1.0
*/
public class IndexServlet extends BaseServlet{

    public void personList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("调用了IndexServlet的personList方法");
        //正式版跳转request.getRequestDispatcher("/pages/service/personinfomation/personinformation.jsp").forward(request,response);
        //演示系统跳转
        request.getRequestDispatcher("/pages/test/test.jsp").forward(request,response);
    }



}
