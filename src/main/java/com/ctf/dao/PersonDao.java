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

    public List<Person> queryAllPersonLimit(int pageNo,int pageSize) {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;
        String sql = "select * from person_info limit ?,?";
        List<Person> personList = queryForList(Person.class, sql,start,end);

        for(Person person:personList){
            person.setLeader(
                    new PersonDao().queryRelatedLeader(person.getPerson_id())
            );
            personList.set(personList.indexOf(person),person);
        }

        return personList;
    }

    public List<Person> queryAllPerson() {
        String sql = "select * from person_info";
        List<Person> personList = queryForList(Person.class, sql);
        return personList;
    }

    //查询相关领导
    public List<Person> queryRelatedLeader(String person_id){
        String sql = "SELECT * FROM  person_info WHERE person_id IN(" +
                "SELECT leader_id FROM leader_subordinate " +
                "WHERE subordinate_id=?)";
        List<Person> leaderList = queryForList(Person.class, sql,person_id);
        return leaderList;
    }

}
