package com.ctf.web;

import com.ctf.service.SystemDataService;
import com.ctf.service.impl.SystemDataServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public List<String> bindSelectData(HttpServletRequest request, HttpServletResponse response){
        //获取要绑定的数据描述
        String dataName = request.getParameter("dataName");

        //将dataName传给SystemDataService获取数据
        List<String> systemDataList = systemDataService.querySelectData(dataName);

        return null;
    }
}


