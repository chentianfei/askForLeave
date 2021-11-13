package com.ctf.dao;

import com.ctf.bean.Office;
import com.ctf.bean.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

    //多条件查询人员_分页
    public List<Person> querySomePerson(Person person,Integer pageNo,Integer pageSize) {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;

        //给出sql模板,为了便于后面添加sql语句
        StringBuilder sql =new StringBuilder("select * from person_info where 1=1");

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();


        //String类型的数据，若前端有数据填写处，但是用户可能没填写数据，则该数据在trim后isEmpty == true，或该数据==""
        //String类型的数据，若前端没有数据填写处，则该数据== null
        //非String类型的数据，无论前端有无数据填写处，只要没有数据传过来，都是==null

        /*
        person_id=null, name=, sex=, nation=,
         birthDate=null, nativePlace=西藏自治区日喀则市仲巴县&&,
         office=, post=, area_class=四类区,
         level=, phone=, allow_Leave_Days=null,
         leader=null，
         */

        //人员编号
        String person_id = person.getPerson_id();
        if(person_id != null){
            sql.append(" and person_id = ?");
            parmas.add(person_id.trim());
        }

        //姓名
        String name = person.getName();
        if(name != "" && !name.trim().isEmpty()){
            sql.append(" and name like ?");
            parmas.add("%"+name.trim()+"%");
        }

        //性别
        String sex = person.getSex();
        if(sex != "" && !sex.trim().isEmpty()){
            sql.append(" and sex = ?");
            parmas.add(Integer.parseInt(sex.trim()));
        }

        //民族
        String nation = person.getNation();
        if(nation != "" && !nation.trim().isEmpty()){
            sql.append(" and nation = ?");
            parmas.add(nation.trim());
        }

        //出生年月
        Date birthDate = person.getBirthDate();
        if(birthDate != null){
            sql.append(" and birthDate = ?");
            parmas.add(birthDate);
        }

        //本人籍贯
        String nativePlace = person.getNativePlace();
        if(nativePlace != "" && !nativePlace.trim().isEmpty()){
            sql.append(" and nativePlace like ?");
            parmas.add("%"+nativePlace.trim()+"%");
        }

        //工作单位
        String office = person.getOffice();
        if(office != "" && !office.trim().isEmpty()){
            sql.append(" and office = ?");
            parmas.add(office.trim());
        }

        //现任职务
        String post = person.getPost();
        if(post != "" && !post.trim().isEmpty()){
            sql.append(" and post like ?");
            parmas.add(post.trim());
        }

        //所在类区
        String area_class = person.getArea_class();
        if(area_class != "" && !area_class.trim().isEmpty()){
            sql.append(" and area_class=?");
            parmas.add(area_class.trim());
        }

        //职级
        String level = person.getLevel();
        if(level != "" && !level.trim().isEmpty()){
            sql.append(" and level = ?");
            parmas.add(level.trim());

            // 这里虽然是string类型，但是不要多此一举为他们前后加上双引号，否则查不出来，具体原因还不清楚
            // parmas.add('"'+level.trim()+'"');
        }

        //联系电话
        String phone = person.getPhone();
        if(phone != "" && !phone.trim().isEmpty()){
            sql.append(" and phone = ?");
            parmas.add('"'+phone.trim()+'"');
        }

        //允许休假天数
        Integer allow_Leave_Days = person.getAllow_Leave_Days();
        if(allow_Leave_Days != null){
            sql.append(" and allow_Leave_Days= ?");
            parmas.add(allow_Leave_Days);
        }

        //相关领导
        List<Person> leader = queryRelatedLeader(person_id);

        //分页操作
        sql.append(" limit ?,?");
        parmas.add(start);
        parmas.add(end);

        return queryForList(Person.class,sql.toString(),parmas.toArray());

    }
    //多条件查询人员_不分页（统计数量用）
    public List<Person> querySomePerson(Person person) {

        //给出sql模板,为了便于后面添加sql语句
        StringBuilder sql =new StringBuilder("select * from person_info where 1=1");

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();


        //String类型的数据，若前端有数据填写处，但是用户可能没填写数据，则该数据在trim后isEmpty == true，或该数据==""
        //String类型的数据，若前端没有数据填写处，则该数据== null
        //非String类型的数据，无论前端有无数据填写处，只要没有数据传过来，都是==null

        /*
        person_id=null, name=, sex=, nation=,
         birthDate=null, nativePlace=西藏自治区日喀则市仲巴县&&,
         office=, post=, area_class=四类区,
         level=, phone=, allow_Leave_Days=null,
         leader=null，
         */

        //人员编号
        String person_id = person.getPerson_id();
        if(person_id != null){
            sql.append(" and person_id = ?");
            parmas.add(person_id.trim());
        }

        //姓名
        String name = person.getName();
        if(name != "" && !name.trim().isEmpty()){
            sql.append(" and name like ?");
            parmas.add("%"+name.trim()+"%");
        }

        //性别
        String sex = person.getSex();
        if(sex != "" && !sex.trim().isEmpty()){
            sql.append(" and sex = ?");
            parmas.add(Integer.parseInt(sex.trim()));
        }

        //民族
        String nation = person.getNation();
        if(nation != "" && !nation.trim().isEmpty()){
            sql.append(" and nation = ?");
            parmas.add(nation.trim());
        }

        //出生年月
        Date birthDate = person.getBirthDate();
        if(birthDate != null){
            sql.append(" and birthDate = ?");
            parmas.add(birthDate);
        }

        //本人籍贯
        String nativePlace = person.getNativePlace();
        if(nativePlace != "" && !nativePlace.trim().isEmpty()){
            sql.append(" and nativePlace like ?");
            parmas.add("%"+nativePlace.trim()+"%");
        }


        //工作单位
        String office = person.getOffice();
        if(office != "" && !office.trim().isEmpty()){
            sql.append(" and office = ?");
            parmas.add(office.trim());
        }

        //现任职务
        String post = person.getPost();
        if(post != "" && !post.trim().isEmpty()){
            sql.append(" and post like ?");
            parmas.add(post.trim());
        }

        //所在类区
        String area_class = person.getArea_class();
        if(area_class != "" && !area_class.trim().isEmpty()){
            sql.append(" and area_class=?");
            parmas.add(area_class.trim());
        }

        //职级
        String level = person.getLevel();
        if(level != "" && !level.trim().isEmpty()){
            sql.append(" and level = ?");
            parmas.add(level.trim());

            // 这里虽然是string类型，但是不要多此一举为他们前后加上双引号，否则查不出来，具体原因还不清楚
            // parmas.add('"'+level.trim()+'"');
        }

        //联系电话
        String phone = person.getPhone();
        if(phone != "" && !phone.trim().isEmpty()){
            sql.append(" and phone = ?");
            parmas.add('"'+phone.trim()+'"');
        }

        //允许休假天数
        Integer allow_Leave_Days = person.getAllow_Leave_Days();
        if(allow_Leave_Days != null){
            sql.append(" and allow_Leave_Days= ?");
            parmas.add(allow_Leave_Days);
        }

        //相关领导
        List<Person> leader = queryRelatedLeader(person_id);

        
        System.out.println(sql);
        System.out.println("parmas:"+parmas);

        return queryForList(Person.class,sql.toString(),parmas.toArray());
        //return queryForList(Person.class,sql2,objects);

    }

}
