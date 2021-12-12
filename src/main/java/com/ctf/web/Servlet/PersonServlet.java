package com.ctf.web.Servlet;

import com.ctf.bean.Person;
import com.ctf.service.impl.PersonServiceImpl;
import com.ctf.utils.ExcelToDatabaseUtils;
import com.ctf.utils.JDBCUtils;
import com.ctf.utils.PropertiedUtils;
import com.ctf.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
*@Description :
*@ClassName PersonServlet
*@Author tianfeichen
*@Date 2021/8/21 16:49
*@Version v1.0
*/
@WebServlet("/personServlet")
public class PersonServlet extends BaseServlet{

    PersonServiceImpl personService = new PersonServiceImpl();

    //批量新增人员
    public void batchAddPerson(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        int result = 0;
        int judge = 0;
        String filePath = "";
        try {
            // 配置上传参数
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    int index=fileName.lastIndexOf("\\");
                    if(index!=-1) {
                        fileName=fileName.substring(index+1);
                    }
                    //定义上传文件的存放路径
                    String path = PropertiedUtils.getValue(
                            PropertiedUtils.SYSTEMPROPERTIES,
                            "path",
                            String.class
                    );
                    //定义上传文件的完整路径
                    filePath = String.format("%s\\%s",path,fileName);
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println("filePath:"+filePath);
                    // 保存文件到硬盘
                    item.write(storeFile);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 2;
        }

        ExcelToDatabaseUtils.parseExcelToPersonListOBJ(filePath);

        if(judge!=0){
            result = 1;

        }else{
            result = 2;
        }


        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //判断手机号是否存在
    public void isPhoneExists(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取电话号码
        String phone = request.getParameter("phone");

        int existCode;
        //返回1表示电话不存在，不存在重复录入的情况;
        //返回0表示电话已存在，不允许重复录入;
        if(personService.queryPhone(phone) < 1){
            existCode = 1;
        }else {
            existCode = 0;
        }

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("existCode",existCode);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //添加一个人的信息（新增人员）
    public void addAPerson(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取前端传来的参数
        Map<String, String[]> personInfo = request.getParameterMap();
        Person person = WebUtils.fillBean(personInfo, Person.class);

        //调用personService的addAPerson往数据库新增数据
        Integer code = personService.addAPerson(person);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据条件查询人员信息(查询框)
    public void querySomePersons(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));

        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));

        //获取前端传来的查询参数
        Map<String, String[]> map = request.getParameterMap();

        //通过BeanUtils封装成Person类对象
        Person person = WebUtils.fillBean(map, Person.class);

        //本次查询在进行分页后返回的数据
        List<HashMap<String, Object>> hashMaps = personService.querySomePersonLimit(person,pageNo,pageSize);

        //本次查询条件的全部数据数量
        Integer count = personService.querySomePerson(person).size();

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","哈哈");
        result.put("count",count);
        result.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //查询所有人员信息（初始化刷新）
    public void queryAllPerson(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        req.setCharacterEncoding("utf-8");

        //获取当前页码
        Integer pageNo =Integer.valueOf(req.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(req.getParameter("nums"));

        //若非超级用户，则根据单位返回相关信息
        String user_office = req.getParameter("user_office");

        //查询本人基本信息获取返回结果
        List<HashMap<String, Object>> hashMaps = personService.queryAllPersonLimit(pageNo,pageSize,user_office);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",personService.queryAllPerson(user_office).size());
        map.put("data",hashMaps);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(result_json);
    }

    //查询人员基本信息
    public void queryPersonInfoById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Integer person_id = Integer.parseInt( request.getParameter("person_id"));

        //本次查询在进行分页后返回的数据
        Person people = personService.queryPersonInfoById(person_id);
        List<Person> peopleList = new ArrayList<>();
        peopleList.add(people);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","哈哈");
        result.put("count",peopleList.size());
        result.put("data",peopleList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);

    }

    //根据人员编号查询单个人员信息，返回绑定好领导的list
    public void queryPersonInfoByIdRTNList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Integer person_id = Integer.parseInt( request.getParameter("person_id"));

        //本次查询在进行分页后返回的数据
        List<Person> peopleList = personService.queryPersonInfoByIdRTNList(person_id);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","哈哈");
        result.put("count",peopleList.size());
        result.put("data",peopleList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);

    }

    //绑定相关领导
    public void bindRelatedLeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        int code = 0;
        if(request.getParameter("leader_id") == ""  || request.getParameter("leader_id") == null){
            code = -1;
        }else {
            //获取参数
            Integer leader_id = Integer.parseInt( request.getParameter("leader_id"));
            Integer subordinate_id = Integer.parseInt( request.getParameter("subordinate_id"));
            if(personService.queryRelatedLeader(subordinate_id).size() != 0){
                //遍历领导信息，确保该领导没有被添加过
                for(Person leader : personService.queryRelatedLeader(subordinate_id)){
                    if(leader.getPerson_id() == leader_id){
                        //需要添加的领导信息已经存在，不能重复添加
                        code = -2;
                    }else {
                        code = personService.bindRelatedLeader(leader_id,subordinate_id);
                    }
                }
            }else {
                code = personService.bindRelatedLeader(leader_id,subordinate_id);
            }

        }

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //查询相关领导
    public void queryRelatedLeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取参数
        Integer subordinate_id = Integer.parseInt(request.getParameter("subordinate_id"));

        List<Person> leaderList = personService.queryRelatedLeader(subordinate_id);


        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        //map.put("count",leaderList.size());
        map.put("count",leaderList.size());
        map.put("data",leaderList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //删除相关领导
    public void deleteTheLeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取参数
        Integer leader_id = Integer.parseInt( request.getParameter("leader_id"));
        Integer subordinate_id = Integer.parseInt( request.getParameter("subordinate_id"));

        Integer code = personService.deleteTheLeader(leader_id,subordinate_id);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //更新人员信息
    public void updatePersonInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        Map<String, String[]> personInfo = request.getParameterMap();
        Person person = WebUtils.fillBean(personInfo, Person.class);

        Integer code = personService.updatePersonInfo(person);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //根据人员姓名查询人员信息（用于查询重名信息）
    public void queryPersonInfoByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取前端传来的参数
        String person_name = request.getParameter("person_name");

        //本次查询在进行分页后返回的数据
        List<Person> peoples = personService.queryPersonInfoByName(person_name);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","哈哈");
        result.put("count",peoples.size());
        result.put("data",peoples);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //删除人员信息
    public void deleteThePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("调用了PersonServlet的deleteThePerson");
        request.setCharacterEncoding("utf-8");
        Integer person_id = Integer.parseInt(request.getParameter("person_id"));

        Integer code = personService.deletePersonInfoByID(person_id);

        //以json格式返回给前端
        String result_json = new Gson().toJson(code);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);
    }

    //判断是否重名
    public void isMultipleName(HttpServletRequest request,HttpServletResponse response){
        //返回
    }

    //处理重名情况
    public void queryMultipleName(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String person_name = request.getParameter("person_name");
        //执行查询操作

        //对返回的结果做判断
            //如果结果集元素大于1个：重名
            //如果结果集元素等于1个：只有唯一一个
            //如果结果集元素小于1个：无数据

        Person person1 = new Person();


        Person person2 = new Person();
        //person2.setPerson_name(new Gson().toJson(person_name));


        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",100);
        map.put("data",personList);

        String result_str = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_str);
    }

}