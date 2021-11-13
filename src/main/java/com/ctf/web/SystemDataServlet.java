package com.ctf.web;

import com.ctf.bean.Level;
import com.ctf.bean.Office;
import com.ctf.service.SystemDataService;
import com.ctf.service.impl.SystemDataServiceImpl;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName: SystemDataServlet
 * @Package: com.ctf.web
 * @Description：
 * @Author: CTF
 * @Date：2021/11/5 17:32
 */

public class SystemDataServlet extends BaseServlet{

    SystemDataServiceImpl systemDataService = new SystemDataServiceImpl();

    /*
     * @Description ：通过查询数据库绑定下拉菜单数据
     * @Param request
     * @Param response
     * @Return ：List<String> 查询到的系统参数列表
     * @Author: CTF
     * @Date ：2021/11/5 18:05
     */
    public void bindOfficeSelectData(HttpServletRequest request, HttpServletResponse response){
        //通过SystemDataService中的queryOffice()获取数据
        List<Office> offices = systemDataService.queryOffice();
        try {
            //以json格式返回给前端
            String result_json = new Gson().toJson(offices);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(result_json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bindLevelSelectData(HttpServletRequest request,HttpServletResponse response){
        //通过SystemDataService中的queryOffice()获取数据
        List<Level> levels = systemDataService.queryLevel();
        try {
            //以json格式返回给前端
            String result_json = new Gson().toJson(levels);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(result_json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bindNationSelectData(HttpServletRequest request,HttpServletResponse response){
        //通过SystemDataService中的queryOffice()获取数据
        List<Level> levels = systemDataService.queryLevel();
        try {

            //以json格式返回给前端
            String result_json = "" ;
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(result_json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


