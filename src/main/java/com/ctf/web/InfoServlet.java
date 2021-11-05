package com.ctf.web;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.Person;
import com.ctf.service.impl.LeavaInfoServiceImpl;
import com.ctf.utils.DateUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @ClassName InfoServlet
 * @Author tianfeichen
 * @Date 2021/8/24 22:45
 * @Version v1.0
 */
public class InfoServlet extends BaseServlet{
    LeavaInfoServiceImpl leavaInfoService = new LeavaInfoServiceImpl();

    //处理请假操作
    public void askForLeave(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //获取参数，并封装成map
        Map<String,Object> parameterMap = new HashMap<>();

        //根据人员姓名、电话查询人员编号
        parameterMap.put("person_name",request.getParameter("person_name"));
        parameterMap.put("phoneNum",request.getParameter("phoneNum"));


        parameterMap.put("startDate",request.getParameter("startDate"));
        parameterMap.put("leaveType",request.getParameter("leaveType"));
        parameterMap.put("leaveDays",request.getParameter("leaveDays"));
        parameterMap.put("workLeader",request.getParameter("workLeader"));
        parameterMap.put("reason",request.getParameter("reason"));
        parameterMap.put("permmitPerson",request.getParameter("permmitPerson"));
        parameterMap.put("startLocation",request.getParameter("startLocation"));
        parameterMap.put("endLocation",request.getParameter("endLocation"));
        parameterMap.put("leaveRemark",request.getParameter("leaveRemark"));


        //调用LeavaInfoServiceImpl的askForLeaveService()方法，传入必要参数
        int status = leavaInfoService.askForLeaveService(parameterMap);
        //操作成功的情况下给ajax返回一点信息，代表操作成功
        //response.getWriter().write("");
    }

    //待审核_同意功能
    public void agreeLeave(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("调用了InfoServlet的agreeLeave方法");
        String person_name = request.getParameter("person_name");
        String phoneNum = request.getParameter("phoneNum");
        System.out.println("[person_name]="+person_name+"、[phoneNum]="+phoneNum);

        response.setContentType("application/x-json;charset=utf-8");
        response.getWriter().write(new Gson().toJson("哈哈哈"));
    }

    //待审核_不同意功能
    public void notAgreeLeave(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("调用了InfoServlet的notAgreeLeave方法");
        String person_name = request.getParameter("person_name");
        String phoneNum = request.getParameter("phoneNum");
        System.out.println("[person_name]="+person_name+"、[phoneNum]="+phoneNum);

        response.setContentType("application/x-json;charset=utf-8");
        response.getWriter().write(new Gson().toJson("哈哈哈"));
    }

    //待审核_根据条件查询待审核数据
    public void queryAuditInfoByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数
        //封装成Map对象
        //调用Webutils.queryDataByConfidition（）方法返回结果集
    }


    //待审核_查询全部待审核数据
    public void queryAuditInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("调用了InfoServlet的queryAuditInfo方法");
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        LeaveInfo leaveInfo1 = new LeaveInfo();


        LeaveInfo leaveInfo2 = new LeaveInfo();


        List<LeaveInfo> leaveInfos = new ArrayList<LeaveInfo>();
        leaveInfos.add(leaveInfo1);
        leaveInfos.add(leaveInfo2);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",100);
        map.put("data",leaveInfos);

        String result_json = new Gson().toJson(map);
        response.setContentType("application/x-json;charset=UTF-8");
        response.getWriter().write(result_json);
    }

    //查询本年度请假信息统计数据
    public void queryThisYearLeaveInfoCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String person_name = request.getParameter("person_name");
        String phoneNum = request.getParameter("phoneNum");
        System.out.println("person_name="+person_name+",phoneNum="+phoneNum);

        if( person_name == null && phoneNum == null){
            Map<String,Object> map = new HashMap<>();
            map.put("code",0);
            map.put("msg","");
            map.put("count",100);
            map.put("data",new ArrayList<LeaveInfo>());

            String result_json = new Gson().toJson(map);
            response.setContentType("application/x-json;charset=UTF-8");
            response.getWriter().write(result_json);
        }else {
            List<Map<String,Object>> leaveInfoList = new ArrayList<>();

            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("type","事假");
            hashMap.put("time",2);
            hashMap.put("totalDays",22);

            Map<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("type","病假");
            hashMap2.put("time",1);
            hashMap2.put("totalDays",15);

            leaveInfoList.add(hashMap);
            leaveInfoList.add(hashMap2);

            System.out.println(leaveInfoList);

            Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", 100);
            map.put("data", leaveInfoList);

            String result_json = new Gson().toJson(map);
            response.setContentType("application/x-json;charset=UTF-8");
            response.getWriter().write(result_json);
        }



    }

    //查询本年度请假信息详情数据
    public void queryThisYearLeaveInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String person_name = request.getParameter("person_name");
        String phoneNum = request.getParameter("phoneNum");
        System.out.println("person_name="+person_name+",phoneNum="+phoneNum);

        if( person_name == null && phoneNum == null){
            Map<String,Object> map = new HashMap<>();
            map.put("code",0);
            map.put("msg","");
            map.put("count",100);
            map.put("data",new ArrayList<LeaveInfo>());

            String result_json = new Gson().toJson(map);
            response.setContentType("application/x-json;charset=UTF-8");
            response.getWriter().write(result_json);
        }else {
            List<Map<String,Object>> leaveInfoList = new ArrayList<>();

            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("type","事假");
            hashMap.put("actualDays",2);
            hashMap.put("startDate","2021-10-11");
            hashMap.put("endDate","2021-10-13");

            Map<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("type","病假");
            hashMap2.put("actualDays",20);
            hashMap2.put("startDate","2021-8-21");
            hashMap2.put("endDate","2021-8-29");

            Map<String, Object> hashMap3 = new HashMap<>();
            hashMap3.put("type","病假");
            hashMap3.put("actualDays",20);
            hashMap3.put("startDate","2021-8-21");
            hashMap3.put("endDate","2021-8-29");

            Map<String, Object> hashMap4 = new HashMap<>();
            hashMap4.put("type","病假");
            hashMap4.put("actualDays",20);
            hashMap4.put("startDate","2021-8-21");
            hashMap4.put("endDate","2021-8-29");

            Map<String, Object> hashMap5 = new HashMap<>();
            hashMap5.put("type","病假");
            hashMap5.put("actualDays",20);
            hashMap5.put("startDate","2021-8-21");
            hashMap5.put("endDate","2021-8-29");

            Map<String, Object> hashMap6 = new HashMap<>();
            hashMap6.put("type","病假");
            hashMap6.put("actualDays",20);
            hashMap6.put("startDate","2021-8-21");
            hashMap6.put("endDate","2021-8-29");


            Map<String, Object> hashMap7 = new HashMap<>();
            hashMap7.put("type","病假");
            hashMap7.put("actualDays",20);
            hashMap7.put("startDate","2021-8-21");
            hashMap7.put("endDate","2021-8-29");


            leaveInfoList.add(hashMap);
            leaveInfoList.add(hashMap2);
            leaveInfoList.add(hashMap3);
            leaveInfoList.add(hashMap4);
            leaveInfoList.add(hashMap5);
            leaveInfoList.add(hashMap6);
            leaveInfoList.add(hashMap7);

            System.out.println(leaveInfoList);


            Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", 100);
            map.put("data", leaveInfoList);

            String result_json = new Gson().toJson(map);
            response.setContentType("application/x-json;charset=UTF-8");
            response.getWriter().write(result_json);
        }

    }

    //查询待销假数据
    public void queryNotArrive (HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("调用了InfoServlet的queryNotArrive方法");
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        LeaveInfo leaveInfo1 = new LeaveInfo();


        LeaveInfo leaveInfo2 = new LeaveInfo();



        List<LeaveInfo> leaveInfos = new ArrayList<LeaveInfo>();
        leaveInfos.add(leaveInfo1);
        leaveInfos.add(leaveInfo2);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",100);
        map.put("data",leaveInfos);

        String result_json = new Gson().toJson(map);
        response.setContentType("application/x-json;charset=UTF-8");
        response.getWriter().write(result_json);
    }

    //处理销假到岗
    public void manageArrive(HttpServletRequest request,HttpServletResponse response){
        System.out.println("调用了InfoServlet的manageArrive方法");
        System.out.println("person_name="+request.getParameter("person_name"));
        System.out.println("phoneNum="+request.getParameter("phoneNum"));
        System.out.println("endLeaveComment="+request.getParameter("endLeaveComment"));
        System.out.println("getEndDate="+request.getParameter("getEndDate"));
    }

    //查询所有历史请假记录
    public void queryAllHistoryLeaveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {

        System.out.println("调用了InfoServlet的queryNotArrive方法");
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        LeaveInfo leaveInfo1 = new LeaveInfo();


        LeaveInfo leaveInfo2 = new LeaveInfo();



        List<LeaveInfo> leaveInfos = new ArrayList<LeaveInfo>();
        leaveInfos.add(leaveInfo1);
        leaveInfos.add(leaveInfo2);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",100);
        map.put("data",leaveInfos);

        String result_json = new Gson().toJson(map);
        response.setContentType("application/x-json;charset=UTF-8");
        response.getWriter().write(result_json);
    }

    //删除某一条请假记录
    public void deleteHistoryLeaveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {

    }

    //按条件查询历史请假记录
    public void querySomeHistoryLeaveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {

    }

    //查询已累计在岗时间
    public void queryTotalWorkDays(HttpServletRequest request,HttpServletResponse response) throws IOException {

    }

}
