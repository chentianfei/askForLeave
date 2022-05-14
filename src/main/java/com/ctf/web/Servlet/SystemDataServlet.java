package com.ctf.web.Servlet;

import com.ctf.bean.*;
import com.ctf.service.impl.SystemDataServiceImpl;
import com.ctf.utils.WebUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SystemDataServlet
 * @Package: com.ctf.web
 * @Description：
 * @Author: CTF
 * @Date：2021/11/5 17:32
 */
@WebServlet("/systemDataServlet")
public class SystemDataServlet extends BaseServlet{

    SystemDataServiceImpl systemDataService = new SystemDataServiceImpl();

    //修改短信提醒天数
    public void updateSmsAlertDays(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String smsAlertDays = request.getParameter("smsAlertDays");
        int code = -1;
        if(smsAlertDays!=null){
            if(!smsAlertDays.equals("")){
                int newDays = Integer.valueOf(smsAlertDays);
                code = systemDataService.updateSmsAlertDays(newDays);
            }
        }

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",code);
        result.put("data",code);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //查询短信提醒天数
    public void querySmsAlertDays(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int currentSmsAlertDays = systemDataService.querySmsAlertDays();

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",currentSmsAlertDays);
        result.put("data",currentSmsAlertDays);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //修改短信发送对象
    public void updateSendObj(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String doesSendSelf = request.getParameter("doesSendSelf");
        String doesSendLeader = request.getParameter("doesSendLeader");
        //doseSendSelf 状态码：0为关闭，1为开启
        //doseSendLeader 状态码：0为关闭，1为开启

        HashMap<String,Integer> hashMap = new HashMap<>();

        if(doesSendSelf!=null){
            if(!doesSendSelf.equals("")){
                //有该参数表示需要修改为on，即需要给本人发送短信
                int code = systemDataService.updateSendObj("doesSendSelf",1);
                hashMap.put("doesSendSelfUpdateCode",code);
            }
        }else {
            int code = systemDataService.updateSendObj("doesSendSelf",0);
            hashMap.put("doesSendSelfUpdateCode",code);
        }

        if(doesSendLeader!=null){
            if(!doesSendLeader.equals("")){
                //有该参数表示需要修改为on，即需要给领导发送短信
                int code = systemDataService.updateSendObj("doesSendLeader",1);
                hashMap.put("doesSendLeaderUpdateCode",code);
            }
        }else {
            int code = systemDataService.updateSendObj("doesSendLeader",0);
            hashMap.put("doesSendLeaderUpdateCode",code);
        }

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",hashMap.size());
        result.put("data",hashMap);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //查询发送对象状态码
    public void querySendObjStatusCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int doesSendSelfStatusCode = systemDataService.querySendObjStatusCode("doesSendSelf");
        int doesSendLeaderStatusCode = systemDataService.querySendObjStatusCode("doesSendLeader");

        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("doesSendSelfUpdateCode",doesSendSelfStatusCode);
        hashMap.put("doesSendLeaderUpdateCode",doesSendLeaderStatusCode);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",hashMap.size());
        result.put("data",hashMap);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //新增单位
    public void addAOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String office_name = request.getParameter("office_name");

        int code = systemDataService.addAOffice(office_name);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //绑定领导
    public void bindOfficeLeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String office_name = request.getParameter("office_name");

        int code = systemDataService.addAOffice(office_name);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }


    // 删除单位
    public void deleteAOfficeByOfficeId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String idSTR = request.getParameter("id");

        Integer id = null;
        if(idSTR!=null){
            if(!idSTR.equals("")){
                id = Integer.parseInt(idSTR);
            }
        }

        Integer code = -1;
        if(id!=null){
            code = systemDataService.deleteAOfficeByOfficeId(id);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    // 修改单位信息
    public void updateOfficeInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Map<String, String[]> personInfo = request.getParameterMap();
        Office office = WebUtils.fillBean(personInfo, Office.class);

        Integer code = -1;
        if(office!=null){
            code = systemDataService.updateOfficeInfo(office);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据单位id查询单位信息
    public void queryOfficeByOfficeId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Integer office_id = Integer.parseInt( request.getParameter("office_id"));

        //本次查询在进行分页后返回的数据
        Office office = systemDataService.queryOfficeByOfficeId(office_id);
        List<Office> officeList = new ArrayList<>();
        officeList.add(office);


        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",officeList.size());
        result.put("data",officeList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据单位id查询单位领导信息
    public void queryOfficeLeaderByOfficeId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Integer office_id = Integer.parseInt( request.getParameter("office_id"));

        //本次查询在进行分页后返回的数据
        List<Leader> leaderList = systemDataService.queryOfficeLeaderByOfficeId(office_id);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",leaderList.size());
        result.put("data",leaderList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //新增职级
    public void addALevelInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String level_name = request.getParameter("level_name");

        Integer code = -1;
        if(level_name!=null){
            if(!level_name.equals("")){
                code = systemDataService.addALevelInfo(level_name);
            }
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    // 删除职级
    public void deleteALevelInfoByLevelInfoId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String idSTR = request.getParameter("id");

        Integer id = null;
        if(idSTR!=null){
            if(!idSTR.equals("")){
                id = Integer.parseInt(idSTR);
            }
        }

        Integer code = -1;
        if(id!=null){
            code = systemDataService.deleteALevelInfoByLevelInfoId(id);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    // 修改职级信息
    public void updateLevelInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Map<String, String[]> personInfo = request.getParameterMap();
        Level level = WebUtils.fillBean(personInfo, Level.class);

        Integer code = -1;
        if(level!=null){
            code = systemDataService.updateLevelInfo(level);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //新增假期类型
    public void addALeaveType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String leave_type = request.getParameter("leave_type");

        Integer code = -1;
        if(leave_type!=null){
            if(!leave_type.equals("")){
                code = systemDataService.addALeaveType(leave_type);
            }
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    // 删除假期类型
    public void deleteALeaveTypeByLeaveTypeId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String idSTR = request.getParameter("id");

        Integer id = null;
        if(idSTR!=null){
            if(!idSTR.equals("")){
                id = Integer.parseInt(idSTR);
            }
        }

        Integer code = -1;
        if(id!=null){
            code = systemDataService.deleteALeaveTypeByLeaveTypeId(id);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    // 修改假期类型信息
    public void updateLeaveType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Map<String, String[]> personInfo = request.getParameterMap();
        LeaveType leaveType = WebUtils.fillBean(personInfo, LeaveType.class);

        Integer code = -1;
        if(leaveType!=null){
            code = systemDataService.updateLeaveType(leaveType);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //新增用户（创建用户）
    public void addAAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Map<String, String[]> personInfo = request.getParameterMap();
        User user = WebUtils.fillBean(personInfo, User.class);

        System.out.println(user);

       Integer code = -1;
        if(user!=null){
            code = systemDataService.addAAccount(user);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //删除指定用户
    public void deleteAAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String idSTR = request.getParameter("id");

        Integer id = null;
        if(idSTR!=null){
            if(!idSTR.equals("")){
                id = Integer.parseInt(idSTR);
            }
        }

        Integer code = -1;
        if(id!=null){
            code = systemDataService.deleteAAccount(id);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //修改指定用户登录密码
    public void updatePasswordByUserID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String idSTR = request.getParameter("id");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        Integer id = null;
        if(idSTR!=null){
            if(!idSTR.equals("")){
                id = Integer.parseInt(idSTR);
            }
        }

        Integer code = systemDataService.updatePasswordByUserID(id,newPassword,oldPassword);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
        //int updatePasswordByUserID(int id,String newPWD);
    }
    //重置密码
    public void resetPasswordByUserID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String idSTR = request.getParameter("id");

        Integer id = null;
        if(idSTR!=null){
            if(!idSTR.equals("")){
                id = Integer.parseInt(idSTR);
            }
        }

        Integer code = systemDataService.resetPasswordByUserID(id);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //修改指定用户信息
    public void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Map<String, String[]> personInfo = request.getParameterMap();
        User user = WebUtils.fillBean(personInfo, User.class);

       Integer code = -1;
        if(user!=null){
            code = systemDataService.updateUserInfo(user);
        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //查询请假类型
    public void queryLeaveType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //判断是否需要分页，即看当前页码和每页显示数量是否有值
        //获取当前页码
        Integer pageNo = null;
        //获取每页显示数量
        Integer pageSize = null;
        if(request.getParameter("curr")!=null &&  request.getParameter("nums")!=null){

            if(!request.getParameter("curr").equals("") && !request.getParameter("nums").equals("")){
                pageNo =Integer.valueOf(request.getParameter("curr"));
                pageSize = Integer.valueOf(request.getParameter("nums"));
            }

        }

        //若非超级用户，则根据单位返回相关信息
        String user_office = request.getParameter("user_office");

        //查询本人基本信息获取返回结果
        List<LeaveType> leaveTypes = systemDataService.queryLeaveType(pageNo, pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",systemDataService.queryLeaveType(null, null).size());
        map.put("data",leaveTypes);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //查询单位数据
    public void queryOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //判断是否需要分页，即看当前页码和每页显示数量是否有值
        //获取当前页码
        Integer pageNo = null;
        //获取每页显示数量
        Integer pageSize = null;
        if(request.getParameter("curr")!=null &&  request.getParameter("nums")!=null){

            if(!request.getParameter("curr").equals("") && !request.getParameter("nums").equals("")){
                pageNo =Integer.valueOf(request.getParameter("curr"));
                pageSize = Integer.valueOf(request.getParameter("nums"));
            }

        }

        //若非超级用户，则根据单位返回相关信息
        //String user_office = request.getParameter("user_office");

        //查询本人基本信息获取返回结果
        List<Office> offices = systemDataService.queryOffice(pageNo, pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",systemDataService.queryOffice(null, null).size());
        map.put("data",offices);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //查询职级数据
    public void queryLevelInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //判断是否需要分页，即看当前页码和每页显示数量是否有值
        //获取当前页码
        Integer pageNo = null;
        //获取每页显示数量
        Integer pageSize = null;
        if(request.getParameter("curr")!=null &&  request.getParameter("nums")!=null){

            if(!request.getParameter("curr").equals("") && !request.getParameter("nums").equals("")){
                pageNo =Integer.valueOf(request.getParameter("curr"));
                pageSize = Integer.valueOf(request.getParameter("nums"));
            }

        }

        //若非超级用户，则根据单位返回相关信息
        //String user_office = request.getParameter("user_office");

        //查询本人基本信息获取返回结果
        List<Level> levels = systemDataService.queryLevel(pageNo, pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",systemDataService.queryLevel(null, null).size());
        map.put("data",levels);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //查询所有用户信息
    public void queryAccountInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //判断是否需要分页，即看当前页码和每页显示数量是否有值
        //获取当前页码
        Integer pageNo = null;
        //获取每页显示数量
        Integer pageSize = null;
        if(request.getParameter("curr")!=null &&  request.getParameter("nums")!=null){

            if(!request.getParameter("curr").equals("") && !request.getParameter("nums").equals("")){
                pageNo =Integer.valueOf(request.getParameter("curr"));
                pageSize = Integer.valueOf(request.getParameter("nums"));
            }

        }

        //若非超级用户，则根据单位返回相关信息
        //String user_office = request.getParameter("user_office");

        List<User> users = systemDataService.queryAccountInfo(pageNo, pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",systemDataService.queryAccountInfo(null, null).size());
        map.put("data",users);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //查询所有角色信息
    public void queryRoleInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //判断是否需要分页，即看当前页码和每页显示数量是否有值
        //获取当前页码
        Integer pageNo = null;
        //获取每页显示数量
        Integer pageSize = null;
        if(request.getParameter("curr")!=null &&  request.getParameter("nums")!=null){

            if(!request.getParameter("curr").equals("") && !request.getParameter("nums").equals("")){
                pageNo =Integer.valueOf(request.getParameter("curr"));
                pageSize = Integer.valueOf(request.getParameter("nums"));
            }

        }

        //若非超级用户，则根据单位返回相关信息
        //String user_office = request.getParameter("user_office");

        List<Role> roles = systemDataService.queryRoleInfo(pageNo, pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",systemDataService.queryRoleInfo(null, null).size());
        map.put("data",roles);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }
    //查询民族信息
    public void bindNationSelectData(HttpServletRequest request,HttpServletResponse response){
        //通过SystemDataService中的queryLevel获取数据
        List<Nation> nations = systemDataService.queryNaiton();
        try {
            //以json格式返回给前端
            String result_json =new Gson().toJson(nations);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(result_json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


