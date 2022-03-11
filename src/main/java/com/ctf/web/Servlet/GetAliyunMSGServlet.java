package com.ctf.web.Servlet;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getAliyunMSGServlet")
public class GetAliyunMSGServlet extends BaseServlet{

    //##处理销假到岗
    public void getSMGUpstreamMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","成功");

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }


}
