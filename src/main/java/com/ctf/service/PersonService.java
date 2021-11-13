package com.ctf.service;

import com.ctf.bean.Office;
import com.ctf.bean.Person;

import java.util.List;

/*
 * @Description : 定义与人员基本信息相关的相关操作
 * @param: null
 * @return null
 * @Author tianfeichen
 * @Date 2021/8/29 11:52
 **/
public interface PersonService {
    //分页查询库中所有人员信息
    List<Person> queryAllPersonLimit(int pageNo,int pageSize);
    //查询库中所有人员信息
    List<Person> queryAllPerson();
    //根据不定条件查询人员信息_分页
    List<Person> querySomePerson(Person person);
    //根据不定条件查询人员信息_不分页
    List<Person> querySomePersonLimit(Person person,Integer pageNo,Integer pageSize);
    //根据人员编号查询单个人员信息
    Person queryOnePersonById(Integer person_id);
    //根据人员姓名、人员电话查询人员全部信息
    Person queryOnePersonByNameAndPhone(String person_name,String person_phoneNum);
    //根据人员姓名查询人员信息
    List<Person> querySomePersonByName(String person_name);
    //根据其他查询条件查询人员信息
    List<Person> querySomePersonByConditions(String sql_condition);
}
