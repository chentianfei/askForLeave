package com.ctf.dao;

import com.ctf.bean.Office;
import com.ctf.bean.Person;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public List<Person> queryAllPersonLimit(int pageNo,int pageSize)  {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;

        String sql = "select * from person_info order by person_id desc limit ?,? ";
        List<Person> personList = queryForList(Person.class, sql,start,end);
        //对查询出来的每个人绑定领导后返回
        return  getNormalResult(personList);
    }

    public List<Person> queryAllPerson() {
        String sql = "select * from person_info";

        //先查询
        List<Person> personList = queryForList(Person.class, sql);
        //对查询出来的每个人绑定领导后返回
        return  getNormalResult(personList);
    }

    //查询相关领导
    public List<Person> queryRelatedLeader(Integer person_id){
        String sql = "SELECT * FROM  person_info WHERE person_id IN(" +
                "SELECT leader_id FROM leader_subordinate " +
                "WHERE subordinate_id=?)";
        List<Person> leaderList = queryForList(Person.class, sql,person_id);
        return leaderList;
    }

    //多条件查询人员_分页
    public List<Person> querySomePersonLimit(Person person,Integer pageNo,Integer pageSize) {
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

        //分页操作
        sql.append(" limit ?,? ");
        parmas.add(start);
        parmas.add(end);

        //先查询
        List<Person> personList = queryForList(Person.class,sql.toString(),parmas.toArray());
        //对查询出来的每个人绑定领导后返回
        return  getNormalResult(personList);

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

        //先查询
        List<Person> personList = queryForList(Person.class, sql.toString(), parmas.toArray());
        //对查询出来的每个人绑定领导后返回
        return getNormalResult(personList);
        //return queryForList(Person.class,sql2,objects);

    }

    //判断手机号是否存在
    public Long queryPhone(String phoneNumber){
        String sql = "select count(*) from person_info where phone = ?";
        return (Long) queryForSingleValue(sql,phoneNumber);
    }

    //新增人员
    public Integer addAPerson(Person person) {
        System.out.println("调用了persondao的addAPerson方法："+person);
        //给出sql模板,为了便于后面添加sql语句
        String sql ="insert into person_info(person_id," +
                "NAME,nation,sex,birthdate,nativeplace,office," +
                "post,level,phone,allow_leave_days,area_class) " +
                "values(null,?,?,?,?,?,?,?,?,?,?,?)";

        String sql2 ="insert into person_info_copy(person_id," +
                "NAME,nation,sex,birthdate,nativeplace,office," +
                "post,level,phone,allow_leave_days,area_class) " +
                "values(null,?,?,?,?,?,?,?,?,?,?,?)";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(person.getName());
        parmas.add(person.getNation());
        parmas.add(person.getSex());
        parmas.add(person.getBirthDate());
        parmas.add(person.getNativePlace());
        parmas.add(person.getOffice());
        parmas.add(person.getPost());
        parmas.add(person.getLevel());
        parmas.add(person.getPhone());
        parmas.add(person.getAllow_Leave_Days());
        parmas.add(person.getArea_class());

        int insertCount = update(sql,parmas.toArray());
        int insertCopyCount = update(sql2,parmas.toArray());

        return insertCount;
    }

    //根据手机号删除一个人
    public Integer deleteAPersonByPhone(String phoneNum){
        String sql = "delete from person_info where phone = ?";
        return update(sql,phoneNum);
    }

    //根据手机号查找一个人的id
    public Integer queryIdByPhone(String phoneNum){
        String sql = "select person_id from person_info where phone = ?";
        Long id = (Long)queryForSingleValue(sql,phoneNum);
        return id.intValue();
    }

    //更新人员信息
    public Integer updatePersonInfo(Person newPersonInfo){
         String updatePerson_InfoSQL = "update person_info set " +
                 "`NAME`=?," +
                 "nation=?," +
                 "sex=?," +
                 "birthdate=?," +
                 "nativeplace=?," +
                 "office=?," +
                 "post=?," +
                 "`level`=?," +
                 "phone=?," +
                 "allow_leave_days=?," +
                 "area_class=? "+
                 "where person_id=?";
        String updatePerson_Info_CopySQL = "update person_info_copy set " +
                "`NAME`=?," +
                "nation=?," +
                "sex=?," +
                "birthdate=?," +
                "nativeplace=?," +
                "office=?," +
                "post=?," +
                "`level`=?," +
                "phone=?," +
                "allow_leave_days=?," +
                "area_class=? "+
                "where person_id=?";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(newPersonInfo.getName());
        parmas.add(newPersonInfo.getNation());
        parmas.add(newPersonInfo.getSex());
        parmas.add(newPersonInfo.getBirthDate());
        parmas.add(newPersonInfo.getNativePlace());
        parmas.add(newPersonInfo.getOffice());
        parmas.add(newPersonInfo.getPost());
        parmas.add(newPersonInfo.getLevel());
        parmas.add(newPersonInfo.getPhone());
        parmas.add(newPersonInfo.getAllow_Leave_Days());
        parmas.add(newPersonInfo.getArea_class());
        parmas.add(newPersonInfo.getPerson_id());

        int updateCount = update(updatePerson_InfoSQL,parmas.toArray());
        int updateCopyCount = update(updatePerson_Info_CopySQL,parmas.toArray());

        return updateCount;
    }

    //根据人员编号person_id查找一个人的信息
    public Person queryPersonInfoByID(Integer person_id){
        String sql = "select * from person_info where person_id = ?";
        return queryForOne(Person.class, sql,person_id);
    }

    //根据人员id删除人员信息
    public Integer deletePersonInfoByID(Integer person_id){
        String sql = "delete from person_info where person_id = ?";
        return update(sql,person_id);
    }

    //将数据库获取的原始数据重新定义格式
    public List<Person> getNormalResult(List<Person> personList) {
        Integer person_id;
        for (Person p:personList){
            //绑定相关领导
            person_id = queryIdByPhone(p.getPhone());
            List<Person> leaderList = queryRelatedLeader(person_id);
            p.setLeader(leaderList);
        }
        return personList;
    }
    //根据人员姓名查询人员信息（用于查询重名信息）
    public List<Person> queryPersonInfoByName(String person_name) {
        String sql = "select person_id,`NAME`,phone,office,post from person_info where `NAME`=?";
        return queryForList(Person.class, sql,person_name);
    }

    //绑定相关领导
    public Integer bindRelatedLeaderDao(Integer leader_id, Integer subordinate_id) {
        String sql = "insert into leader_subordinate(leader_id,subordinate_id) values(?,?)";
        return update(sql, leader_id, subordinate_id);
    }

    //删除相关领导
    public Integer deleteTheLeaderDao(Integer leader_id, Integer subordinate_id) {
        String sql = "delete from leader_subordinate where leader_id = ? and subordinate_id = ?";
        return update(sql, leader_id, subordinate_id);
    }


}
