package com.ctf.dao;

import com.ctf.bean.Person;

import java.util.List;

/**
 * @Description :
 * @ClassName PersonDao
 * @Author tianfeichen
 * @Date 2021/9/7 23:24
 * @Version v1.0
 */
public class PersonDao extends BaseDao{
    public List<Person> queryAllPerson() {
        String sql = "";
        List<Person> personList = queryForList(Person.class, sql);
        return personList;
    }
}
