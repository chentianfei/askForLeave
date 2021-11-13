package com.ctf.web;

import com.ctf.bean.Office;
import com.ctf.bean.Person;
import com.ctf.service.impl.PersonServiceImpl;
import com.ctf.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
*@Description :
*@ClassName PersonServlet
*@Author tianfeichen
*@Date 2021/8/21 16:49
*@Version v1.0
*/
public class PersonServlet extends BaseServlet{

    PersonServiceImpl personService = new PersonServiceImpl();

    //根据条件查询人员信息
    public void querySomePersons(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取当前页码
        Integer pageNo =Integer.valueOf(request.getParameter("curr"));
        System.out.println(pageNo);
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(request.getParameter("nums"));
        System.out.println(pageSize);

        //获取前端传来的查询参数
        Map<String, String[]> map = request.getParameterMap();
        //通过BeanUtils封装成Person类对象
        Person person = WebUtils.fillBean(map, Person.class);

        List<Person> peoples = personService.querySomePersonLimit(person,pageNo,pageSize);

        Integer count = personService.querySomePerson(person).size();

        System.out.println(peoples);
        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","哈哈");
        result.put("count",count);
        result.put("data",peoples);

        //以json格式返回给前端
        String result_json = new Gson().toJson(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);

    }

    //查询所有人员信息
    public void queryAllPerson(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //获取当前页码
        Integer pageNo =Integer.valueOf(req.getParameter("curr"));
        //获取每页显示数量
        Integer pageSize = Integer.valueOf(req.getParameter("nums"));
        //查询本人基本信息获取返回结果
        List<Person> personList = personService.queryAllPersonLimit(pageNo,pageSize);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",personService.queryAllPerson().size());
        map.put("data",personList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(result_json);
    }

    //查询人员基本信息
    public void queryPersonDetail(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("查询人员基本信息:调用了PersonServlet的queryPersonDetail方法");
        String person_name = request.getParameter("person_name");
        String phoneNum = request.getParameter("phoneNum");
        System.out.println("[person_name]="+person_name+"、[phoneNum]="+phoneNum);


        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        String result_json = new Gson().toJson(person1);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);

    }

    //绑定相关领导
    public void bindRelatedLeader(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("leaveKind","username");
        req.setAttribute("totalCount","totalCount");
        req.setAttribute("totalDays","totalDays");

        System.out.println("调用了PersonServlet的bindRelatedLeader");
    }

    //更新人员信息
    public void updatePersonInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用了PersonServlet的updatePersonInfo");
    }

    //删除人员信息
    public void deleteThePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用了PersonServlet的deleteThePerson");
    }

    //增加单个人员
    public void addOnePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用了PersonServlet的addOnePerson");
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
        //response.setContentType("application/json;charset=utf-8");

        //response.setContentType("text/javascript;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(result_str);
    }

}
