package com.ctf.dao;

import com.ctf.bean.Leader;
import com.ctf.bean.LeaveInfo;
import com.ctf.bean.Office;
import com.ctf.bean.Person;
import com.ctf.utils.JDBCUtils;
import com.google.gson.Gson;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @ClassName PersonDao
 * @Author tianfeichen
 * @Date 2021/9/7 23:24
 * @Version v1.0
 */
public class PersonDao extends BaseDao{

    OfficeLeaderDao officeLeaderDao = new OfficeLeaderDao();

    //新增人员
    public int addAPerson(Person person) {
        //给出sql模板,为了便于后面添加sql语句
        String sql ="insert into person_info(person_id," +
                "NAME,nation,sex,birthdate,start_work_date,nativeplace,marriage_status," +
                "name_spouse,nativeplace_spouse,office," +
                "post,level,phone,allow_leave_days,area_class) " +
                "values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(person.getName());
        parmas.add(person.getNation());
        parmas.add(person.getSex());
        parmas.add(person.getBirthDate());
        parmas.add(person.getStart_work_date());
        parmas.add(person.getNativePlace());
        parmas.add(person.getMarriage_status());
        parmas.add(person.getName_spouse());
        parmas.add(person.getNativeplace_spouse());
        parmas.add(person.getOffice());
        parmas.add(person.getPost());
        parmas.add(person.getLevel());
        parmas.add(person.getPhone());
        parmas.add(person.getAllow_Leave_Days());
        parmas.add(person.getArea_class());

        return update(sql,parmas.toArray());
    }

    //批量新增人员
    public int addPersonBatch(List<Person> personList) throws SQLException {

        Connection conn = JDBCUtils.getConnection();
        String sql ="insert into person_info(person_id," +
                "NAME,nation,sex,birthdate,nativeplace,office," +
                "post,level,phone,allow_leave_days,area_class) " +
                "values(null,?,?,?,?,?,?,?,?,?,?,?)";

        conn.setAutoCommit(false);
        PreparedStatement prest = conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        conn.setAutoCommit(false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for(Person person : personList){

            prest.addBatch();
        }

        int[] insertCounts = prest.executeBatch();
        conn.commit();
        conn.close();
        return insertCounts.length;
    }

    //根据人员id删除人员信息
    public int deletePersonInfoByID(Integer person_id){
        String sql = "delete from person_info where person_id = ?";
        return update(sql,person_id);
    }

    //根据手机号删除一个人
    public int deleteAPersonByPhone(String phoneNum){
        String sql = "delete from person_info where phone = ?";
        return update(sql,phoneNum);
    }

    //更新人员信息
    public int updatePersonInfo(Person newPersonInfo){
        String updatePerson_InfoSQL = "update person_info set " +
                "`NAME`=?," +
                "nation=?," +
                "sex=?," +
                "birthdate=?," +
                "start_work_date=?," +
                "nativeplace=?," +
                "marriage_status=?," +
                "name_spouse=?," +
                "nativeplace_spouse=?," +
                "office=?," +
                "post=?," +
                "level=?," +
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
        parmas.add(newPersonInfo.getStart_work_date());
        parmas.add(newPersonInfo.getNativePlace());
        parmas.add(newPersonInfo.getMarriage_status());
        parmas.add(newPersonInfo.getName_spouse());
        parmas.add(newPersonInfo.getNativeplace_spouse());
        parmas.add(newPersonInfo.getOffice());
        parmas.add(newPersonInfo.getPost());
        parmas.add(newPersonInfo.getLevel());
        parmas.add(newPersonInfo.getPhone());
        parmas.add(newPersonInfo.getAllow_Leave_Days());
        parmas.add(newPersonInfo.getArea_class());
        parmas.add(newPersonInfo.getPerson_id());

        return update(updatePerson_InfoSQL,parmas.toArray());
    }

    //根据用户单位返回该单位所有人员的人员编号List
    public List<Person> queryPersonByOffice(String office){
        String sql = "select * from person_info where office=?";
        return queryForList(Person.class,sql,office);
    }

    //查询所有人员信息
    public List<Person> queryAllPerson(Integer pageNo,Integer pageSize,String user_office)  {
        StringBuilder sql = new StringBuilder("select * from person_info where 1=1 ");
        //用于保存可变参数
        List<Object> params = new ArrayList<Object>();
        if(user_office != null ){
            if(!user_office.equals("")){
                //根据单位查询
                sql.append(" and office=? ");
                params.add(user_office);
            }
        }

        //设置排序规则
        sql.append(" order by person_id desc ");

        //判断是否分页
        if (pageNo != null && pageSize != null) {
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo - 1) * pageSize;
            //分页参数：结束值
            Integer end = pageSize;
            sql.append(" limit ?,?");
            params.add(start);
            params.add(end);
        }

        return  queryForList(Person.class, sql.toString(),params.toArray());
    }

    //根据条件查询人员
    public List<Person> querySomePerson(Map<String, String[]> map, Integer pageNo, Integer pageSize) {
        //查询人员信息的基础语句
        StringBuilder personInfoSQL = new StringBuilder("select * from person_info where 1=1 ");
        //用于保存可变参数
        List<Object> params = new ArrayList<Object>();
        /*----------------------------------------------------------------------------------------*/
        //遍历并解析map数据
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //姓名
                case "name":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and name like ?");
                        params.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //本人籍贯
                case "nativePlace":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nativePlace like ?");
                        params.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //工作单位
                case "office":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and office = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //类区
                case "area_class":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and area_class = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //职级
                case "level":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and level = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //联系电话
                case "phone":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and phone = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //性别
                case "sex":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and sex = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //民族
                case "nation":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nation = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //出生年月
                case "birthDate":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and birthDate = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //参公年月【2022年新系统新增】
                case "start_work_date":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and start_work_date = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //配偶姓名【2022年新系统新增】
                case "name_spouse":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and name_spouse like ?");
                        params.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //婚姻状态【2022年新系统新增】
                case "marriage_status":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and marriage_status = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
                //配偶籍贯【2022年新系统新增】
                case "nativeplace_spouse":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nativeplace_spouse like ?");
                        params.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //现任职务
                case "post":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and post like ?");
                        params.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //允许休假天数
                case "allow_Leave_Days":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and allow_Leave_Days = ?");
                        params.add(m.getValue()[0].trim());
                    }
                    break;
            }
        }

        //设置排序规则
        personInfoSQL.append(" order by person_id desc ");

        //判断是否分页
        if (pageNo != null && pageSize != null) {
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo - 1) * pageSize;
            //分页参数：结束值
            Integer end = pageSize;
            personInfoSQL.append(" limit ?,?");
            params.add(start);
            params.add(end);
        }

        return queryForList(Person.class, personInfoSQL.toString(), params.toArray());
    }

    public List<Leader> queryRelatedLeader(Integer person_id){
        if(null != person_id){
            //找到人员所在的单位
            Person person = queryPersonInfoByID(person_id);
            String office = person.getOffice();

            //根据所在单位名称获取该单位的领导，并封装为person对象
            List<Leader> leaders = officeLeaderDao.queryLeaderByOfficeName(office);
            return leaders;
        }
        return null;
    }

    //判断手机号是否存在
    public Long queryPhone(String phoneNumber){
        String sql = "select count(*) from person_info where phone = ?";
        return (Long) queryForSingleValue(sql,phoneNumber);
    }

    //根据手机号查找一个人的id
    public Integer queryIdByPhone(String phoneNum){
        String sql = "select person_id from person_info where phone = ?";
        Long id = (Long)queryForSingleValue(sql,phoneNum);
        return id.intValue();
    }

    //根据人员编号person_id查找一个人的信息
    public Person queryPersonInfoByID(Integer person_id){
        String sql = "select * from person_info where person_id = ?";
        return queryForOne(Person.class, sql,person_id);
    }

    //根据人员姓名查询人员信息（用于查询重名信息）
    public List<Person> queryPersonInfoByName(String person_name) {
        String sql = "select person_id,`NAME`,phone,office,post from person_info where `NAME`=?";
        return queryForList(Person.class, sql,person_name);
    }


}
