package com.ctf.web.Servlet;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.LeaveInfoCount;
import com.ctf.bean.LeaveType;
import com.ctf.bean.User;
import com.ctf.service.impl.AskForLeaveServiceImpl;
import com.ctf.utils.WebUtils;
import com.google.gson.Gson;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @Description :
 * @ClassName AskForLeaveServlet
 * @Author tianfeichen
 * @Date 2021/8/24 22:45
 * @Version v1.0
 */
@WebServlet("/askForLeaveServlet")
public class AskForLeaveServlet extends BaseServlet{

    AskForLeaveServiceImpl askForLeaveService = new AskForLeaveServiceImpl();

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
     * @Param null
     * @Return ：null
     * @Author: CTF
     * @Date ：2022/3/4 22:28
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


    //发送催促销假警告短信
    public void sendAlertSMS(HttpServletRequest request,HttpServletResponse response)throws IOException {
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

    //查询今日应到假人员信息
    public void queryCurrentEOLPerson(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

        List<HashMap<String, Object>> hashMaps =
                askForLeaveService.queryCurrentEOLPerson(pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService
                .queryCurrentEOLPerson(null,null));
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

    //处理请假操作
    public void addLeaveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取前端传来的参数
        Map<String, String[]> leaveInfo = request.getParameterMap();
        LeaveInfo leave = WebUtils.fillBean(leaveInfo, LeaveInfo.class);

        int code = askForLeaveService.addLeaveInfo(leave);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据条件查询待审批的请假数据
    public void querySomeLeaveInfos(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

        //获取前端传来的查询参数
        Map<String, String[]> dataMap = request.getParameterMap();

        List<HashMap<String, Object>> hashMaps =
                askForLeaveService.querySomeLeaveInfosLimit(dataMap,pageNo,pageSize);


        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.querySomeLeaveInfos(dataMap));
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

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

        List<HashMap<String, Object>> hashMaps = askForLeaveService.queryAllLeaveInfoLimit(pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryAllLeaveInfo());
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

   //待审核_同意功能
    public void agreeLeave(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
        //通过序列号给service发送短信，给dao做增删操作，并返回短信发送情况\

        int code = askForLeaveService.agreeLeave(serialnumber);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

   ///待审核_不同意功能
    public void notAgreeLeave(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        String approval_reason = request.getParameter("approval_reason");
        int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
        //通过序列号给service发送短信，给dao做增删操作，并返回短信发送情况\
        int code = askForLeaveService.notAgreeLeave(serialnumber,approval_reason);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
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
        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

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
        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

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

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

        List<HashMap<String, Object>> hashMaps = askForLeaveService.queryALLResumeWorkInfoLimit(pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryALLResumeWorkInfo());
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

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

        //获取前端传来的查询参数
        Map<String, String[]> dataMap = request.getParameterMap();

        List<HashMap<String, Object>> hashMaps =
                askForLeaveService.querySomeResumeWorkInfosLimit(dataMap,pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.querySomeResumeWorkInfo(dataMap));
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
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

        int code = askForLeaveService.resumeWork(serialnumberSTR,end_leave_remarkSTR,
                end_dateSTR,end_leave_operator);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",code);
        map.put("data",code);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //删除某一条请假记录
    public void deleteAHistoryInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
        String delete_reason = request.getParameter("delete_reason");
        Date delete_date = new Date();
        String delete_operator = request.getParameter("delete_operator");

        int code = askForLeaveService.deleteAHistoryInfo(serialnumber,delete_reason,
               delete_date,delete_operator);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",code);
        map.put("data",code);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据流水号查询一条历史记录
    public void queryAHistoryInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
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

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

        //获取用户信息，判断访问资源，若非超级用户，则根据单位返回相关信息
        String user_office = request.getParameter("user_office");
        User user = (User) request.getSession().getAttribute("user");

        List<HashMap<String, Object>> hashMaps =
                askForLeaveService.queryALLHistoryInfoLimit(pageNo,pageSize,user);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",askForLeaveService.queryALLHistoryInfo(user));
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

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

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
    public void  queryOnesHistoryInfoOfOneLeaveTypeByPersonID(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
    public void  queryThisYearOneLeaveInfoCountByPersonID(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
    public void  queryRecentWorkDaysByPersonIDqueryAHistoryInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
