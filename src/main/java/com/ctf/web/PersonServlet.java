package com.ctf.web;

import com.ctf.bean.Person;
import com.ctf.utils.DateUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
*@Description :
*@ClassName PersonServlet
*@Author tianfeichen
*@Date 2021/8/21 16:49
*@Version v1.0
*/
public class PersonServlet extends BaseServlet{

    //查询人员基本信息
    public void queryPersonDetail(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("查询人员基本信息:调用了PersonServlet的queryPersonDetail方法");
        String person_name = request.getParameter("person_name");
        String phoneNum = request.getParameter("phoneNum");
        System.out.println("[person_name]="+person_name+"、[phoneNum]="+phoneNum);


        Person person1 = new Person(12,"测试1","男",DateUtils.StringToDate("1994-05-17"),
                "四川省888","政府办","科员","二类区","一级科员","18089922014",50,12,"汉族");
        Person person2 = new Person(13,"测试2","女",DateUtils.StringToDate("1994-05-17"),
                "四川省999","县委办","主任","二类区","一级科员","18089922014",50,12,"藏族");
        Person person3 = new Person(14,"测试3","男",DateUtils.StringToDate("1994-05-17"),
                "四川省000","组织部","部长","二类区","一级科员","18089922014",50,12,"满族");



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

    //查询人员信息
    public void queryAllPerson_json(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Person person1 = new Person(12,"测试1","男",DateUtils.StringToDate("1994-05-17"),
                "四川省888","政府办","科员","二类区","一级科员","18089922014",50,12,"汉族");
        Person person2 = new Person(13,"测试2","女",DateUtils.StringToDate("1994-05-17"),
                "四川省999","县委办","主任","二类区","一级科员","18089922014",60,13,"藏族");
        Person person3 = new Person(14,"测试3","男",DateUtils.StringToDate("1994-05-17"),
                "四川省000","组织部","部长","二类区","一级科员","18089922014",70,14,"满族");
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","哈哈");
        map.put("count",100);
        map.put("data",personList);

        String result_json = new Gson().toJson(map);
        System.out.println(result_json);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(result_json);
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
        //person1.setPerson_name(new Gson().toJson(person_name));
        person1.setPerson_name(person_name);
        person1.setSex("男");
        person1.setBirthDate(new Date());
        person1.setNationality("汉族");
        person1.setNativePlace("测试地址眉山市2");
        person1.setOffice("政府办");
        person1.setJob("一级科员");
        person1.setPhoneNum("18089922014");

        Person person2 = new Person();
        //person2.setPerson_name(new Gson().toJson(person_name));
        person2.setPerson_name(person_name);
        person2.setSex("女");
        person2.setBirthDate(new Date());
        person2.setNationality("藏族");
        person2.setNativePlace("测试地址北京市2");
        person2.setOffice("县委办");
        person2.setJob("一级主任科员");
        person2.setPhoneNum("18235232014");

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
