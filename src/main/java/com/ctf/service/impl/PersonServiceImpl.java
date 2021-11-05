package com.ctf.service.impl;

import com.ctf.bean.Person;
import com.ctf.dao.PersonDao;
import com.ctf.service.PersonService;

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
    public List<Person> queryAllPersonLimit(int pageNo,int pageSize) {
        return personDao.queryAllPersonLimit(pageNo,pageSize);
    }

    @Override
    public List<Person> queryAllPerson() {
        return personDao.queryAllPerson();
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
