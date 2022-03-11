package com.ctf.web.Servlet;

import com.ctf.bean.Person;
import com.ctf.dao.PersonDao;
import com.ctf.service.impl.PersonServiceImpl;
import com.ctf.utils.*;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    //上传文件
    public void uploadFiles(HttpServletRequest request,HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8");

        Gson gson = new Gson();

        try {
            request.setCharacterEncoding("utf-8");
            String filePath = "";
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
                    int index = fileName.lastIndexOf(File.separator);
                    if (index != -1) {
                        fileName = fileName.substring(index + 1);
                    }
                    //定义上传文件的存放路径
                    String path = "E:"+File.separator+"uploadFiles"
                            +File.separator+"AskForLeave"
                            +File.separator+"zbx-zzb";
                    //String path = File.separator+"Users"+File.separator+"tianfeichen"+File.separator+"Desktop";
                    //定义上传文件的完整路径
                    filePath = String.format("%s"+File.separator+"%s", path, fileName);

                    File storeFile = new File(filePath);
                    // 保存文件到硬盘
                    item.write(storeFile);
                }
                //以json格式返回给前端

                //封装成json字符串，通过getWriter().write()返回给页面
                Map<String,Object> result = new HashMap<>();
                result.put("filepath",filePath);
                result.put("msg","ok");

                String result_json = gson.toJson(result);

                response.getWriter().write(new Gson().toJson(result_json));
            }
        } catch (UnsupportedEncodingException e) {
            //以json格式返回给前端
            try {
                //封装成json字符串，通过getWriter().write()返回给页面
                Map<String,Object> result = new HashMap<>();
                result.put("msg",e.getMessage());
                String result_json = gson.toJson(result);
                response.getWriter().write(new Gson().toJson(result_json));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            //以json格式返回给前端
            try {
                //封装成json字符串，通过getWriter().write()返回给页面
                Map<String,Object> result = new HashMap<>();
                result.put("msg",e.getMessage());
                String result_json = gson.toJson(result);
                response.getWriter().write(new Gson().toJson(result_json));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            //以json格式返回给前端
            try {
                //封装成json字符串，通过getWriter().write()返回给页面
                Map<String,Object> result = new HashMap<>();
                result.put("msg",e.getMessage());
                String result_json = gson.toJson(result);
                response.getWriter().write(new Gson().toJson(result_json));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (FileUploadException e) {
            //以json格式返回给前端
            try {
                //封装成json字符串，通过getWriter().write()返回给页面
                Map<String,Object> result = new HashMap<>();
                result.put("msg",e.getMessage());
                String result_json = gson.toJson(result);
                response.getWriter().write(new Gson().toJson(result_json));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) { //以json格式返回给前端
            //以json格式返回给前端
            try {
                //封装成json字符串，通过getWriter().write()返回给页面
                Map<String,Object> result = new HashMap<>();
                result.put("msg",e.getMessage());
                String result_json = gson.toJson(result);
                response.getWriter().write(new Gson().toJson(result_json));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //批量新增人员
    public void batchAddPerson(HttpServletRequest request,HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        Gson gson = new Gson();
        //解决post请求方式获取请求参数的中文乱码问题
        try {
            request.setCharacterEncoding("utf-8");
            String filepath = request.getParameter("filepath");
            int batchAddPersonCounts = personService.batchAddPerson(filepath);

            //封装成json字符串，通过getWriter().write()返回给页面
            Map<String,Object> result = new HashMap<>();
            result.put("addCounts",batchAddPersonCounts);
            result.put("status","ok");
            String result_json = gson.toJson(result);
            response.getWriter().write(result_json);
        } catch (UnsupportedEncodingException e) {
            //封装成json字符串，通过getWriter().write()返回给页面
            Map<String,Object> result = new HashMap<>();
            result.put("status","fail");
            result.put("msg",e.getMessage());
            String result_json = gson.toJson(result);
            try {
                response.getWriter().write(result_json);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            int errorCode = throwables.getErrorCode();
            String errorMessage = ErrorCode.returnCHNMessageByErrorCode(errorCode);
            try {
                //封装成json字符串，通过getWriter().write()返回给页面
                Map<String,Object> result = new HashMap<>();
                result.put("status","fail");
                result.put("statusCode",errorCode);
                result.put("msg",errorMessage);
                String result_json = gson.toJson(result);
                response.getWriter().write(result_json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            //封装成json字符串，通过getWriter().write()返回给页面
            Map<String,Object> result = new HashMap<>();
            result.put("status","fail");
            result.put("msg",e.getMessage());
            String result_json = gson.toJson(result);
            try {
                response.getWriter().write(result_json);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }  catch (Exception e) {
            e.printStackTrace();
            //封装成json字符串，通过getWriter().write()返回给页面
            Map<String,Object> result = new HashMap<>();
            result.put("status","fail");
            result.put("msg",e.getMessage());
            String result_json = gson.toJson(result);
            try {
                response.getWriter().write(result_json);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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

        //本次查询在进行分页后返回的数据
        List<Person> hashMaps = personService.querySomePerson(map,pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",personService.querySomePerson(map,null,null));
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
        List<Person> hashMaps = personService.queryAllPerson(pageNo,pageSize,user_office);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",personService.queryAllPerson(null,null,user_office));
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
