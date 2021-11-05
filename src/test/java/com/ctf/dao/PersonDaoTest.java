package com.ctf.dao;

import com.ctf.bean.Person;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest {
    PersonDao personDao = new PersonDao();

    @Test
    public void queryAllPerson() {
        List<Person> people = personDao.queryAllPersonLimit(1,5);
        for(Person person:people){
            System.out.println(person);
        }

    }

    @Test
    public void queryAllPersonLimit() {
    }

    @Test
    public void queryRelatedLeader() {

        List<Person> leaderList = personDao.queryRelatedLeader("cs003");
        for(Person leader:leaderList){
            System.out.println(leader);
        }

    }
}