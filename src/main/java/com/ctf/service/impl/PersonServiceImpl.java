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
        System.out.println("调用了PersonServiceImpl的addAPerson方法："+person);
        person.setPerson_id(WebUtils.generateAPersonId(person));
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
        List<Person> personList = personDao.querySomePerson(person);

        String person_id;
        for (Person p:personList){
            person_id = p.getPerson_id();
            List<Person> leaderList = personDao.queryRelatedLeader(person_id);
            p.setLeader(leaderList);
        }
        return personList;
    }

    @Override
    public List<Person> querySomePersonLimit(Person person,Integer pageNo,Integer pageSize) {
        List<Person> personList = personDao.querySomePerson(person,pageNo,pageSize);
        String person_id;
        for (Person p:personList){
            person_id = p.getPerson_id();
            List<Person> leaderList = personDao.queryRelatedLeader(person_id);
            p.setLeader(leaderList);
        }
        return personList;
    }


    @Override
    public Person queryOnePersonById(Integer person_id) {
        return null;
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
