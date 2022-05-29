package com.ctf.service;

import com.ctf.bean.Office;
import com.ctf.bean.Person;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Description : 定义与人员基本信息相关的相关操作
 * @param: null
 * @return null
 * @Author tianfeichen
 * @Date 2021/8/29 11:52
 **/
public interface PersonService {
    //批量新增人员
    int batchAddPerson(String filePath) throws Exception;
    //根据人员id删除人员信息
    Integer deletePersonInfoByID(Integer person_id,String operator);
    //更新人员信息
    Integer updatePersonInfo(Person person);
    //根据手机号删除人员信息
    //查询指定电话在数据库中的数量
    Long queryPhone(String phoneNum);
    //新增一个人员信息
    Integer addAPerson(Person person);
    //查询库中所有人员信息
    List<HashMap<String,Object>>  queryAllPerson(Integer pageNo,Integer pageSize,String user_office);
    //根据不定条件查询人员信息
    List<Person> querySomePerson(Map<String, String[]> map, Integer pageNo, Integer pageSize);
    //根据人员编号查询单个人员信息
    Person queryPersonInfoById(Integer person_id);
    //根据人员编号查询单个人员信息，返回绑定好领导的list
    List<Person> queryPersonInfoByIdRTNList(Integer person_id);
    //根据人员姓名、人员电话查询人员全部信息
    Person queryOnePersonByNameAndPhone(String person_name,String person_phoneNum);
    //根据人员姓名查询人员信息
    List<Person> querySomePersonByName(String person_name);
    //根据其他查询条件查询人员信息
    List<Person> querySomePersonByConditions(String sql_condition);
    //根据人员姓名查询人员信息（用于查询重名信息）
    List<Person> queryPersonInfoByName(String person_name);

}
