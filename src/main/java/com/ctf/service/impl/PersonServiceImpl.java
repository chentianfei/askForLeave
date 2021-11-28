package com.ctf.service.impl;

import com.ctf.bean.Office;
import com.ctf.bean.Person;
import com.ctf.dao.PersonDao;
import com.ctf.service.PersonService;
import com.ctf.utils.WebUtils;

import java.util.List;

/**
 * @Description :
 * @ClassName PersonServiceImpl
 * @Author tianfeichen
 * @Date 2021/9/4 23:36
 * @Version v1.0
 */
public class PersonServiceImpl implements PersonService {
    PersonDao personDao = new PersonDao();

    @Override
    public Integer deleteTheLeader(Integer leader_id, Integer subordinate_id) {
        return personDao.deleteTheLeaderDao(leader_id,subordinate_id);
    }

    @Override
    public List<Person> queryRelatedLeader(Integer subordinate_id) {
        return personDao.queryRelatedLeader(subordinate_id);
    }

    @Override
    public Integer bindRelatedLeader(Integer leader_id, Integer subordinate_id) {
        return personDao.bindRelatedLeaderDao(leader_id,subordinate_id);
    }

    @Override
    public List<Person> queryPersonInfoByName(String person_name) {
        return personDao.queryPersonInfoByName(person_name);
    }

    @Override
    public Integer deletePersonInfoByID(Integer person_id) {
        return personDao.deletePersonInfoByID(person_id);
    }

    @Override
    public Integer updatePersonInfo(Person person) {

        return personDao.updatePersonInfo(person);
    }

    @Override
    public Long queryPhone(String phoneNum) {
        return personDao.queryPhone(phoneNum);
    }

    @Override
    /*
     * @Description ：
     * @Param person 新增的人员信息
     * @Return ：Integer 数据库操作的数据量
     * @Author: CTF
     * @Date ：2021/11/14 17:33
     */
    public Integer addAPerson(Person person) {
        return personDao.addAPerson(person);
    }

    @Override
    public List<Person> queryAllPersonLimit(int pageNo,int pageSize) {
        return personDao.queryAllPersonLimit(pageNo,pageSize);
    }

    @Override
    public List<Person> queryAllPerson() {
        return personDao.queryAllPerson();
    }

    @Override
    public List<Person> querySomePerson(Person person) {
        return personDao.querySomePerson(person);
    }

    @Override
    public List<Person> querySomePersonLimit(Person person,Integer pageNo,Integer pageSize) {
         return personDao.querySomePersonLimit(person,pageNo,pageSize);
    }

    @Override
    public Person queryPersonInfoById(Integer person_id) {
        return personDao.queryPersonInfoByID(person_id);
    }

    @Override
    public Person queryOnePersonByNameAndPhone(String person_name, String person_phoneNum) {
        return null;
    }

    @Override
    public List<Person> querySomePersonByName(String person_name) {
        return null;
    }

    @Override
    public List<Person> querySomePersonByConditions(String sql_condition) {
        return null;
    }


}
