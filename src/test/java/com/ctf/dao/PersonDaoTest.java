package com.ctf.dao;

import com.ctf.bean.Person;
import com.ctf.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest {
    PersonDao personDao = new PersonDao();

    @Test
    public void queryAllPerson() {

    }

    @Test
    public void queryAllPersonLimit() {
    }

    @Test
    public void queryRelatedLeader() {

     /*   List<Person> leaderList = personDao.queryRelatedLeader("cs003");
        for(Person leader:leaderList){
            System.out.println(leader);
        }*/

    }

    @Test
    public void querySomePerson() {

        Person person = new Person(
                null,"测试","",
                "",null,"",
                "","","四类区","",
                "",null,null
        );
        System.out.println(personDao.querySomePersonLimit(person,1,2));


    }

    @Test
    public void queryPhone() {
        System.out.println(personDao.queryPhone("18089922014"));
    }

    @Test
    public void testQueryAllPersonLimit() {
    }

    @Test
    public void queryIdByPhone() {

        System.out.println(personDao.queryIdByPhone("18089922014"));


    }


}