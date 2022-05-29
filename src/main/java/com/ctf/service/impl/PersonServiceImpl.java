package com.ctf.service.impl;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.Person;
import com.ctf.dao.AskForLeaveDao;
import com.ctf.dao.PersonDao;
import com.ctf.service.PersonService;
import com.ctf.utils.ExcelToDatabaseUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    /*
     * @Description ：批量新增人员
     * @param: filePath 上传的表格存放路径
     * @return java.lang.String
     * @Author tianfeichen
     * @Date 2021/12/29 14:11
     **/
    public int batchAddPerson(String filePath) throws IOException, SQLException, ParseException {
        List<Person> personList = ExcelToDatabaseUtils.parseExcelToPersonListOBJ(filePath);
        return  personDao.addPersonBatch(personList);
    }

    @Override
    public List<Person> queryPersonInfoByName(String person_name) {
        return personDao.queryPersonInfoByName(person_name);
    }

    @Override
    public Integer deletePersonInfoByID(Integer person_id,String operator) {
        AskForLeaveServiceImpl askForLeaveService = new AskForLeaveServiceImpl();
        AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
        Person person = personDao.queryPersonInfoByID(person_id);
        List<LeaveInfo> leaveInfoList = askForLeaveService.queryLeaveInfoByPersonId(person_id);
        for(LeaveInfo leaveInfo : leaveInfoList){
            leaveInfo.setStatus("已废除");
            //修改此人名下的请假备份数据
            askForLeaveDao.updateBackupLeaveInfo(leaveInfo);
            //记录日志
            leaveInfo.setStart_leave_operator(operator);
            askForLeaveDao.addAnOperateLogOfAskforleave(leaveInfo,"删除","人员："+person.toString()+"被删除，该数据被联动删除");
        }
        //删除此人信息
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
    public List<HashMap<String,Object>> queryAllPerson(Integer pageNo,Integer pageSize,String user_office) {
        List<Person> personList = personDao.queryAllPerson(pageNo, pageSize, user_office);
        return formatInfo(personList);
    }

    @Override
    public List<Person> querySomePerson(Map<String, String[]> map, Integer pageNo, Integer pageSize) {
        return personDao.querySomePerson(map,pageNo,pageSize);
    }

    @Override
    public Person queryPersonInfoById(Integer person_id) {
        Person person = personDao.queryPersonInfoByID(person_id);
        return person;
    }

    @Override
    public List<Person> queryPersonInfoByIdRTNList(Integer person_id) {
        List<Person> peopleList = new ArrayList<>();
        peopleList.add(personDao.queryPersonInfoByID(person_id));
        return peopleList;
    }

    public List<HashMap<String,Object>> queryPersonInfoByIdRTNMap(Integer person_id){
        ArrayList<Person> arrayList = new ArrayList<>();
        Person person = queryPersonInfoById(person_id);
        arrayList.add(person);
        return formatInfo(arrayList);
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

    //封装数据，主要为了前台显示的日期格式
    public List<HashMap<String,Object>> formatInfo(List<Person> personList){
        List<HashMap<String,Object>> mapList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        for (Person person: personList){
            //封装回显数据
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("person_id",person.getPerson_id());
            hashMap.put("name",person.getName());
            hashMap.put("sex", person.getSex());
            hashMap.put("nation", person.getNation());
            hashMap.put("birthDate", simpleDateFormat.format(person.getBirthDate()));
            hashMap.put("start_work_date", simpleDateFormat.format(person.getStart_work_date()));
            hashMap.put("nativePlace", person.getNativePlace());

            hashMap.put("marriage_status", person.getMarriage_status());
            hashMap.put("name_spouse", person.getName_spouse());
            hashMap.put("nativeplace_spouse", person.getNativeplace_spouse());

            hashMap.put("office", person.getOffice());
            hashMap.put("post", person.getPost());
            hashMap.put("area_class", person.getArea_class());
            hashMap.put("level", person.getLevel());
            hashMap.put("phone", person.getPhone());
            hashMap.put("allow_Leave_Days", person.getAllow_Leave_Days());

            mapList.add(hashMap);
        }
        return mapList;
    }

}
