package com.ctf.web.Servlet;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.ctf.bean.*;
import com.ctf.service.impl.AskForLeaveServiceImpl;
import com.ctf.utils.WebUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

 /**
  * @ClassName: AskForLeaveServlet
  * @Package: com.ctf.web.Servlet
  * @Description：
  * @Author: CTF
  * @Date：2022/5/12 20:50
  */
@WebServlet("/askForLeaveServlet")
public class AskForLeaveServlet extends BaseServlet{

    AskForLeaveServiceImpl askForLeaveService = new AskForLeaveServiceImpl();

    //处理请假操作
    public void addLeaveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取前端传来的参数
        Map<String, String[]> leaveInfo = request.getParameterMap();
        LeaveInfo leave = WebUtils.fillBean(leaveInfo, LeaveInfo.class);
        System.out.println(leave);
        //处理并获取存入数据库的对象
        Map<String, Object> stringObjectMap = askForLeaveService.addLeaveInfo(leave);

        //以json格式返回给前端
        String result_json = new Gson().toJson(stringObjectMap);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

     //根据序列号查询一条请假数据
     public void queryALeaveInfoForPrintBySerialnumber(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
         //解决post请求方式获取请求参数的中文乱码问题
         request.setCharacterEncoding("utf-8");

         String serialnumber_str = request.getParameter("serialnumber");
         Integer serialnumber = null;
         if(serialnumber_str!=null){
             if(!serialnumber_str.trim().equals("")){
                 serialnumber = Integer.parseInt(serialnumber_str);
             }
         }

         HashMap<String, Object> hashMapList = askForLeaveService.queryALeaveInfoForPrintBySerialnumber(serialnumber);


         //以json格式返回给前端
         String result_json = new Gson().toJson(hashMapList);
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().write(result_json);
     }

     //更新请假数据
     public void  updateLeaveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
         //解决post请求方式获取请求参数的中文乱码问题
         request.setCharacterEncoding("utf-8");
         //获取前端传来的参数
         Map<String, String[]> newLeaveInfo = request.getParameterMap();
         LeaveInfo leaveInfo = WebUtils.fillBean(newLeaveInfo, LeaveInfo.class);

         int code = 0;
         String message = "修改成功";
         try {
             code = askForLeaveService.updateLeaveInfo(leaveInfo);
         } catch (Exception e) {
             message = e.getMessage();
         }

         Map<String,Object> result = new HashMap<>();
         result.put("code",code);
         result.put("message",message);

         //以json格式返回给前端
         String result_json = new Gson().toJson(result);
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().write(result_json);
     }

     //更新历史数据
     public void  updateHistoryInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
         //解决post请求方式获取请求参数的中文乱码问题
         request.setCharacterEncoding("utf-8");
         //获取前端传来的参数
         Map<String, String[]> newLeaveInfo = request.getParameterMap();
         LeaveInfo leaveInfo = WebUtils.fillBean(newLeaveInfo, LeaveInfo.class);

         int code = 0;
         String message = "修改成功";
         try {
             code = askForLeaveService.updateAHistoryInfoBySerialnumber(leaveInfo);
         } catch (Exception e) {
             message = e.getMessage();
         }

         Map<String,Object> result = new HashMap<>();
         result.put("code",code);
         result.put("message",message);

         //以json格式返回给前端
         String result_json = new Gson().toJson(result);
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().write(result_json);
     }

     //处理销假到岗
    public void resumeWork(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
    //解决post请求方式获取请求参数的中文乱码问题
    request.setCharacterEncoding("utf-8");

    String serialnumberSTR = request.getParameter("serialnumber");
    String end_leave_remarkSTR = request.getParameter("end_leave_remark");
    String end_dateSTR = request.getParameter("end_date");
    String end_leave_operator = request.getParameter("end_leave_operator");
    String person_id_str = request.getParameter("person_id");


    Map<String, Object> stringObjectMap = askForLeaveService.resumeWork(serialnumberSTR, end_leave_remarkSTR,
                end_dateSTR, end_leave_operator);


    //以json格式返回给前端
    String result_json = new Gson().toJson(stringObjectMap);
    response.setContentType("text/html;charset=utf-8");
    response.getWriter().write(result_json);
    }

    //根据序列号删除一条请假记录
    public void deleteALeaveInfoBySerialnumber(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
        String delete_reason = request.getParameter("delete_reason");
        String delete_operator = request.getParameter("delete_operator");

        int code = 0;
        String message = "删除成功";
        try {
            code = askForLeaveService.deleteALeaveInfoBySerialnumber(serialnumber,delete_reason,"已废除", delete_operator);
        }catch (Exception e){
            message = e.getMessage();
        }

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("message",message);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

     //批量删除请假记录
     public void batchDeleteALeaveInfoBySerialnumber(HttpServletRequest request,HttpServletResponse response) throws IOException {
         //解决post请求方式获取请求参数的中文乱码问题
         request.setCharacterEncoding("utf-8");

         String serialnumber_set_str = request.getParameter("serialnumber_set");
         String[] serialnumber_set = serialnumber_set_str
                 .replace("[", "")
                 .replace("]", "")
                 .split(",");
         List<Integer> serialnumberList = new ArrayList<>();
         for(String serialnumber : serialnumber_set){
             serialnumberList.add(Integer.parseInt(serialnumber));
         }
         String delete_reason = request.getParameter("delete_reason");
         String delete_operator = request.getParameter("delete_operator");

         int count = askForLeaveService.batchDeleteALeaveInfoBySerialnumber(serialnumberList,delete_reason, delete_operator);
         String message = "完成，需要删除【"+serialnumberList.size()+"】条，成功删除【"+count+"】条";

         //封装成json字符串，通过getWriter().write()返回给页面
         Map<String,Object> map = new HashMap<>();
         map.put("code",count);
         map.put("message",message);

         //以json格式返回给前端
         String result_json = new Gson().toJson(map);
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().write(result_json);
     }

     //批量删除历史记录
     public void batchDeleteHistoryInfoBySerialnumber(HttpServletRequest request,HttpServletResponse response) throws IOException {
         //解决post请求方式获取请求参数的中文乱码问题
         request.setCharacterEncoding("utf-8");

         String serialnumber_set_str = request.getParameter("serialnumber_set");
         String[] serialnumber_set = serialnumber_set_str
                 .replace("[", "")
                 .replace("]", "")
                 .replace("\"", "")
                 .split(",");
         List<Integer> serialnumberList = new ArrayList<>();
         for(String serialnumber : serialnumber_set){
             serialnumberList.add(Integer.parseInt(serialnumber));
         }
         String delete_reason = request.getParameter("delete_reason");
         String delete_operator = request.getParameter("delete_operator");

         int count = askForLeaveService.batchDeleteHistoryInfoBySerialnumber(serialnumberList,delete_reason, delete_operator);
         String message = "完成，需要删除【"+serialnumberList.size()+"】条，成功删除【"+count+"】条";

         //封装成json字符串，通过getWriter().write()返回给页面
         Map<String,Object> map = new HashMap<>();
         map.put("code",count);
         map.put("message",message);

         //以json格式返回给前端
         String result_json = new Gson().toJson(map);
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().write(result_json);
     }

     //批量执行销假操作
     public void batchResumeWork(HttpServletRequest request,HttpServletResponse response) throws IOException {
         //解决post请求方式获取请求参数的中文乱码问题
         request.setCharacterEncoding("utf-8");

         //获取序列号集合
         String serialnumber_set_str = request.getParameter("serialnumber_set");
         String[] serialnumber_set = serialnumber_set_str
                 .replace("\"", "")
                 .split(",");
         //获取销假备注
         String end_leave_remark = request.getParameter("end_leave_remark");
         //获取销假时间
         String end_date = request.getParameter("end_date");
         //获取销假操作者
         String end_leave_operator = request.getParameter("end_leave_operator");
         /*String[] serialnumber_set2 = new String[0];*/
         int count = askForLeaveService.batchResumeWork(serialnumber_set,end_leave_remark, end_date,end_leave_operator);
         String message = "完成，需要销假【"+serialnumber_set.length+"】条，成功销假【"+count+"】条";

         //封装成json字符串，通过getWriter().write()返回给页面
         Map<String,Object> map = new HashMap<>();
         map.put("count",count);
         map.put("message",message);

         //以json格式返回给前端
         String result_json = new Gson().toJson(map);
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().write(result_json);
     }

    //删除某一条请假记录
    public void deleteAHistoryInfoBySerialnumber(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
        String delete_reason = request.getParameter("delete_reason");
        String delete_operator = request.getParameter("delete_operator");

        int code = 0;
        String msg = "删除成功";
        try {
            code = askForLeaveService.deleteAHistoryInfoBySerialnumber(serialnumber,delete_reason, delete_operator);
        } catch (Exception e) {
            msg = e.getMessage();
        }

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    /*
    * @Description ：按条件查询今日到期未销假人员
    * @Param null
    * @Return ：null
    * @Author: CTF
    * @Date ：2022/3/4 22:28
    */
    public void querySomeAllCurrentEOLPerson(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
    //解决post请求方式获取请求参数的中文乱码问题
    request.setCharacterEncoding("utf-8");

    //处理页码
    String curr = request.getParameter("curr");
    String nums = request.getParameter("nums");
    //获取当前页码
    Integer pageNo = null;
    //获取每页显示数量
    Integer pageSize = null;
    if(!curr.trim().equals("") && curr!=null){
     pageNo =Integer.valueOf(curr);
    }
    if(!nums.trim().equals("") && nums!=null){
     pageSize =Integer.valueOf(nums);
    }

    //获取前端传来的查询参数
    Map<String, String[]> dataMap = request.getParameterMap();

    List<HashMap<String, Object>> hashMaps =
         askForLeaveService.querySomeAllCurrentEOLPerson(dataMap,pageNo,pageSize);

    //封装成json字符串，通过getWriter().write()返回给页面
    Map<String,Object> map = new HashMap<>();
    map.put("code",0);
    map.put("msg","");
    map.put("count",askForLeaveService
         .querySomeAllCurrentEOLPerson(dataMap,null,null));
    map.put("data",hashMaps);

    //以json格式返回给前端
    String result_json = new Gson().toJson(map);
    response.setContentType("text/html;charset=utf-8");
    response.getWriter().write(result_json);
    }

    /*
    * @Description ：按条件查询以往到期未销假人员
    * @Param request:
    * @Param response:
    * @Return ：void
    * @Author: CTF
    * @Date ：2022/5/12 21:05
    */
    public void querySomeCurrentEOLPerson(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
    //解决post请求方式获取请求参数的中文乱码问题
    request.setCharacterEncoding("utf-8");

    //处理页码
    String curr = request.getParameter("curr");
    String nums = request.getParameter("nums");
    //获取当前页码
    Integer pageNo = null;
    //获取每页显示数量
    Integer pageSize = null;
    if(!curr.trim().equals("") && curr!=null){
     pageNo =Integer.valueOf(curr);
    }
    if(!nums.trim().equals("") && nums!=null){
     pageSize =Integer.valueOf(nums);
    }

    //获取前端传来的查询参数
    Map<String, String[]> dataMap = request.getParameterMap();

    List<HashMap<String, Object>> hashMaps =
         askForLeaveService.querySomeCurrentEOLPerson(dataMap,pageNo,pageSize);

    //封装成json字符串，通过getWriter().write()返回给页面
    Map<String,Object> map = new HashMap<>();
    map.put("code",0);
    map.put("msg","");
    map.put("count",askForLeaveService
         .querySomeCurrentEOLPerson(dataMap,null,null));
    map.put("data",hashMaps);

    //以json格式返回给前端
    String result_json = new Gson().toJson(map);
    response.setContentType("text/html;charset=utf-8");
    response.getWriter().write(result_json);
    }

    /*
    * @Description ：发送催促销假警告短信
    * @Param request:
    * @Param response:
    * @Return ：void
    * @Author: CTF
    * @Date ：2022/5/12 21:06
    */
    /*public void sendAlertSMS(HttpServletRequest request,HttpServletResponse response)throws IOException {
    //解决post请求方式获取请求参数的中文乱码问题
    request.setCharacterEncoding("utf-8");

    //获取流水号
    String serialnumber_str = request.getParameter("serialnumber");
    Integer serialnumber = null;
    if(serialnumber_str!=null){
     if(!serialnumber_str.trim().equals("")){
         serialnumber =  Integer.valueOf(serialnumber_str);
     }
    }

    SendSmsResponse sendSmsResponse = askForLeaveService.sendAlertSMS(serialnumber);

    //通过发射后的相应对象，获取此次发射的响应状态集数组
    SendStatus[] sendStatusSet = sendSmsResponse.getSendStatusSet();
    //由于每次只会有一个集合，故取sendStatusSet[0]即可获取此次发射的响应状态集合
    SendStatus thisSendStatus = sendStatusSet[0];

    Map<String,Object> sendStatusMap = new HashMap<>();
    sendStatusMap.put("code",thisSendStatus.getCode());
    sendStatusMap.put("message",thisSendStatus.getMessage());

    //以json格式返回给前端
    String result_json = new Gson().toJson(sendStatusMap);
    response.setContentType("text/html;charset=utf-8");
    response.getWriter().write(result_json);
    }

    /*
    * @Description ：查询今日应到假人员信息
    * @Param request:
    * @Param response:
    * @Return ：void
    * @Author: CTF
    * @Date ：2022/5/12 21:06
    */

    public void queryCurrentEOLPerson(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
         //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String pageNoStr = request.getParameter("curr");
        String pageSizeStr = request.getParameter("nums");
        Integer pageNo = null;
        Integer pageSize =null;
        //获取当前页码
        if(pageNoStr!=null){
            if(!pageNoStr.trim().equals("")){
                pageNo =Integer.valueOf(pageNoStr);
            }
        }
        //获取每页显示数量
        if(pageSizeStr!=null){
            if(!pageSizeStr.trim().equals("")){
                pageSize = Integer.valueOf(pageSizeStr);
            }
        }

        List<HashMap<String, Object>> hashMaps = askForLeaveService.queryCurrentEOLPerson(pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryCurrentEOLPerson(null,null));
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
     }

     //查询所有到假未到岗人员
    public void queryAllCurrentEOLPerson(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
         //解决post请求方式获取请求参数的中文乱码问题
         request.setCharacterEncoding("utf-8");

         String curr_str = request.getParameter("curr");
         String nums_str = request.getParameter("nums");
         Integer pageNo = null;
         Integer pageSize = null;
         if(curr_str!=null){
             if(!curr_str.trim().equals("")){
                 //获取当前页码
                 pageNo =Integer.valueOf(curr_str);
             }
         }
         if(nums_str!=null){
             if(!nums_str.trim().equals("")){
                 //获取每页显示数量
                 pageSize = Integer.valueOf(nums_str);
             }
         }

         List<HashMap<String, Object>> hashMaps =
                 askForLeaveService.queryAllCurrentEOLPerson(pageNo,pageSize);

         //封装成json字符串，通过getWriter().write()返回给页面
         Map<String,Object> map = new HashMap<>();
         map.put("code",0);
         map.put("msg","");
         map.put("count",askForLeaveService
                 .queryAllCurrentEOLPerson(null,null));
         map.put("data",hashMaps);

         //以json格式返回给前端
         String result_json = new Gson().toJson(map);
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().write(result_json);
     }

     //根据条件查询待审批的请假数据
    public void querySomeLeaveInfos(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String curr_str = request.getParameter("curr");
         String nums_str = request.getParameter("nums");
         Integer pageNo = null;
         Integer pageSize = null;
         if(curr_str!=null){
             if(!curr_str.trim().equals("")){
                 //获取当前页码
                 pageNo =Integer.valueOf(curr_str);
             }
         }
         if(nums_str!=null){
             if(!nums_str.trim().equals("")){
                 //获取每页显示数量
                 pageSize = Integer.valueOf(nums_str);
             }
         }

        //获取前端传来的查询参数
        Map<String, String[]> dataMap = request.getParameterMap();

        List<HashMap<String, Object>> hashMaps =
                askForLeaveService.querySomeLeaveInfos(dataMap,pageNo,pageSize);


        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.querySomeLeaveInfos(dataMap,null,null));
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //查询所有待审批的请假数据
    public void queryAllLeaveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String curr_str = request.getParameter("curr");
         String nums_str = request.getParameter("nums");
         Integer pageNo = null;
         Integer pageSize = null;
         if(curr_str!=null){
             if(!curr_str.trim().equals("")){
                 //获取当前页码
                 pageNo =Integer.valueOf(curr_str);
             }
         }
         if(nums_str!=null){
             if(!nums_str.trim().equals("")){
                 //获取每页显示数量
                 pageSize = Integer.valueOf(nums_str);
             }
         }

        List<HashMap<String, Object>> hashMaps = askForLeaveService.queryAllLeaveInfo(pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryAllLeaveInfo(null,null));
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据请假信息解析人员信息
    public void queryPersonDetail(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
        List<HashMap<String,Object>> hashMapList= askForLeaveService.queryPersonInfoByIdRTNMap(serialnumber);

        //以json格式返回给前端
        String result_json = new Gson().toJson(hashMapList);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //查询本年度请假信息统计数据
    public void queryThisYearLeaveInfoCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String personIdSTR = request.getParameter("person_id");
        Integer person_id = null;
        if(personIdSTR!=null){
            if(!personIdSTR.equals("")){
                person_id = Integer.parseInt(personIdSTR);
            }
        }
       String curr_str = request.getParameter("curr");
         String nums_str = request.getParameter("nums");
         Integer pageNo = null;
         Integer pageSize = null;
         if(curr_str!=null){
             if(!curr_str.trim().equals("")){
                 //获取当前页码
                 pageNo =Integer.valueOf(curr_str);
             }
         }
         if(nums_str!=null){
             if(!nums_str.trim().equals("")){
                 //获取每页显示数量
                 pageSize = Integer.valueOf(nums_str);
             }
         }

        List<LeaveInfoCount> leaveInfoCounts =
                askForLeaveService.queryThisYearLeaveInfoCountByPersonID(person_id, pageNo, pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryThisYearLeaveInfoCountByPersonID(person_id,null,null).size());
        map.put("data",leaveInfoCounts);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);

    }

    //查询一个人所在单位的正在请假情况统计数据
    public void queryOfficeLeaveInfoByPersonId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String personIdSTR = request.getParameter("person_id");
        Integer person_id = null;
        if(personIdSTR!=null){
            if(!personIdSTR.equals("")){
                person_id = Integer.parseInt(personIdSTR);
            }
        }

        String curr_str = request.getParameter("curr");
        String nums_str = request.getParameter("nums");
        Integer pageNo = null;
        Integer pageSize = null;
        if(curr_str!=null){
            if(!curr_str.trim().equals("")){
                //获取当前页码
                pageNo =Integer.valueOf(curr_str);
            }
        }
        if(nums_str!=null){
            if(!nums_str.trim().equals("")){
                //获取每页显示数量
                pageSize = Integer.valueOf(nums_str);
            }
        }

        HashMap<String, Object> hashMap = askForLeaveService.queryOfficeLeaveInfoByPersonId(person_id, pageNo, pageSize);

        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        hashMapList.add(hashMap);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",hashMapList.size());
        map.put("data",hashMapList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

     //查询一个人所在单位的正在请假情况详情
     public void queryOfficeCurrentLeaveInfoByPersonId(HttpServletRequest request, HttpServletResponse response) throws IOException {
         //解决post请求方式获取请求参数的中文乱码问题
         request.setCharacterEncoding("utf-8");
         String personIdSTR = request.getParameter("person_id");
         Integer person_id = null;
         if(personIdSTR!=null){
             if(!personIdSTR.equals("")){
                 person_id = Integer.parseInt(personIdSTR);
             }
         }

         String curr_str = request.getParameter("curr");
         String nums_str = request.getParameter("nums");
         Integer pageNo = null;
         Integer pageSize = null;
         if(curr_str!=null){
             if(!curr_str.trim().equals("")){
                 //获取当前页码
                 pageNo =Integer.valueOf(curr_str);
             }
         }
         if(nums_str!=null){
             if(!nums_str.trim().equals("")){
                 //获取每页显示数量
                 pageSize = Integer.valueOf(nums_str);
             }
         }

         List<HashMap<String, Object>> hashMapList = askForLeaveService.queryOfficeCurrentLeaveInfoByPersonId(person_id, pageNo, pageSize);

         //封装成json字符串，通过getWriter().write()返回给页面
         Map<String,Object> map = new HashMap<>();
         map.put("code",0);
         map.put("msg","");
         map.put("count",askForLeaveService.queryOfficeCurrentLeaveInfoByPersonId(person_id, null, null));
         map.put("data",hashMapList);

         //以json格式返回给前端
         String result_json = new Gson().toJson(map);
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().write(result_json);
     }

    //查询本年度请假信息详情数据
    public void queryThisYearLeaveInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String personIdSTR = request.getParameter("person_id");
        Integer person_id = null;
        if(personIdSTR!=null){
            if(!personIdSTR.equals("")){
                person_id = Integer.parseInt(personIdSTR);
            }
        }

        String curr_str = request.getParameter("curr");
        String nums_str = request.getParameter("nums");
        Integer pageNo = null;
        Integer pageSize = null;
        if(curr_str!=null){
             if(!curr_str.trim().equals("")){
                 //获取当前页码
                 pageNo =Integer.valueOf(curr_str);
             }
        }
        if(nums_str!=null){
             if(!nums_str.trim().equals("")){
                 //获取每页显示数量
                 pageSize = Integer.valueOf(nums_str);
             }
        }

        List<HashMap<String, Object>> hashMapList = askForLeaveService.queryOnesHistoryInfoByPersonID(person_id, pageNo, pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryOnesHistoryInfoByPersonID(person_id,null,null).size());
        map.put("data",hashMapList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //查询全部待销假数据
    public void queryALLResumeWorkInfo (HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String curr_str = request.getParameter("curr");
        String nums_str = request.getParameter("nums");
        Integer pageNo = null;
        Integer pageSize = null;
        if(curr_str!=null){
            if(!curr_str.trim().equals("")){
                 //获取当前页码
                 pageNo =Integer.valueOf(curr_str);
            }
        }
        if(nums_str!=null){
            if(!nums_str.trim().equals("")){
                 //获取每页显示数量
                 pageSize = Integer.valueOf(nums_str);
            }
        }

        List<HashMap<String, Object>> hashMaps = askForLeaveService.queryALLResumeWorkInfo(pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryALLResumeWorkInfo(null,null));
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据条件查询待销假数据
    public void querySomeResumeWorkInfo(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String curr_str = request.getParameter("curr");
        String nums_str = request.getParameter("nums");
        Integer pageNo = null;
        Integer pageSize = null;
        if(curr_str!=null){
            if(!curr_str.trim().equals("")){
                //获取当前页码
                pageNo =Integer.valueOf(curr_str);
            }
        }
        if(nums_str!=null){
            if(!nums_str.trim().equals("")){
                //获取每页显示数量
                pageSize = Integer.valueOf(nums_str);
            }
        }

        //获取前端传来的查询参数
        Map<String, String[]> dataMap = request.getParameterMap();

        List<HashMap<String, Object>> hashMaps =
                askForLeaveService.querySomeResumeWorkInfo(dataMap,pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.querySomeResumeWorkInfo(dataMap,null,null));
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据流水号查询一条历史记录
    public void queryAHistoryInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String serialnumber_str = request.getParameter("serialnumber");
        int serialnumber = 0;
        if(serialnumber_str!=null){
            if(!serialnumber_str.equals("")){
                serialnumber = Integer.parseInt(serialnumber_str);
            }
        }

        HashMap<String, Object> hashMapList = askForLeaveService.queryAHistoryInfo(serialnumber);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",hashMapList.size());
        map.put("data",hashMapList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //查询所有历史请假记录
    public void queryALLHistoryInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String curr_str = request.getParameter("curr");
        String nums_str = request.getParameter("nums");
        Integer pageNo = null;
        Integer pageSize = null;
        if(curr_str!=null){
            if(!curr_str.trim().equals("")){
                //获取当前页码
                pageNo =Integer.valueOf(curr_str);
            }
        }
        if(nums_str!=null){
            if(!nums_str.trim().equals("")){
                //获取每页显示数量
                pageSize = Integer.valueOf(nums_str);
            }
        }

        //获取用户信息，判断访问资源，若非超级用户，则根据单位返回相关信息
        String user_office = request.getParameter("user_office");
        User user = (User) request.getSession().getAttribute("user");

        List<HashMap<String, Object>> hashMaps =
                askForLeaveService.queryALLHistoryInfo(user,pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryALLHistoryInfo(user,null,null));
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //按条件查询历史请假记录
    public void querySomeHistoryLeaveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String curr_str = request.getParameter("curr");
        String nums_str = request.getParameter("nums");
        Integer pageNo = null;
        Integer pageSize = null;
        if(curr_str!=null){
            if(!curr_str.trim().equals("")){
             //获取当前页码
             pageNo =Integer.valueOf(curr_str);
            }
        }
        if(nums_str!=null){
            if(!nums_str.trim().equals("")){
             //获取每页显示数量
             pageSize = Integer.valueOf(nums_str);
            }
        }

        //获取前端传来的查询参数
        Map<String, String[]> dataMap = request.getParameterMap();

        List<HashMap<String, Object>> hashMaps =
                askForLeaveService.querySomeHistoryInfo(dataMap,pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService
                .querySomeHistoryInfo(dataMap,null,null));
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //按人员编号查询本年度某种假期的详细请假数据
    public void queryOnesHistoryInfoOfOneLeaveTypeByPersonID(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String personIDSTR = request.getParameter("person_id");
        Integer person_id = null;
        if(personIDSTR!=null){
            if(!personIDSTR.equals("")){
                person_id =  Integer.parseInt(personIDSTR);
            }
        }
        String leaveTypeSTR = request.getParameter("leave_type");

        String curr = request.getParameter("curr");
        String nums = request.getParameter("nums");
        //获取当前页码
        Integer pageNo = null;
        //获取每页显示数量
        Integer pageSize = null;
        if(curr!=null){
            if(!curr.equals("")){
                pageNo = Integer.valueOf(curr);
            }
        }
        if(nums!=null){
            if(!nums.equals("")){
                pageNo = Integer.valueOf(nums);
            }
        }

        List<LeaveInfo> leaveInfoList = askForLeaveService.queryOnesHistoryInfoOfOneLeaveTypeByPersonID(
                person_id, leaveTypeSTR, pageNo, pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryOnesHistoryInfoOfOneLeaveTypeByPersonID(
                person_id, leaveTypeSTR, null, null).size());
        map.put("data",leaveInfoList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //按人员编号查询本年度某种假期统计数据
    public void queryThisYearOneLeaveInfoCountByPersonID(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String personIDSTR = request.getParameter("person_id");
        Integer person_id = null;
        if(personIDSTR!=null){
            if(!personIDSTR.equals("")){
                person_id =  Integer.parseInt(personIDSTR);
            }
        }
        String leaveTypeSTR = request.getParameter("leave_type");

        String curr = request.getParameter("curr");
        String nums = request.getParameter("nums");
        //获取当前页码
        Integer pageNo = null;
        //获取每页显示数量
        Integer pageSize = null;
        if(curr!=null){
            if(!curr.equals("")){
                pageNo = Integer.valueOf(curr);
            }
        }
        if(nums!=null){
            if(!nums.equals("")){
                pageNo = Integer.valueOf(nums);
            }
        }

        LeaveInfoCount leaveInfoCount = askForLeaveService.queryThisYearOneLeaveInfoCountByPersonID(person_id, leaveTypeSTR);

        //以json格式返回给前端
        String result_json = new Gson().toJson(leaveInfoCount);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //按人员编号查询近期累计在岗天数
    public void queryRecentWorkDaysByPersonIDqueryAHistoryInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String personIDSTR = request.getParameter("person_id");
        Integer person_id = null;
        if(personIDSTR!=null){
            if(!personIDSTR.equals("")){
                person_id =  Integer.parseInt(personIDSTR);
            }
        }
        String curr = request.getParameter("curr");
        String nums = request.getParameter("nums");
        //获取当前页码
        Integer pageNo = null;
        //获取每页显示数量
        Integer pageSize = null;
        if(curr!=null){
            if(!curr.equals("")){
                pageNo = Integer.valueOf(curr);
            }
        }
        if(nums!=null){
            if(!nums.equals("")){
                pageNo = Integer.valueOf(nums);
            }
        }

        int totalWorkDays = askForLeaveService.queryRecentWorkDaysByPersonID(person_id);

        //以json格式返回给前端
        String result_json = new Gson().toJson(totalWorkDays);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

}
