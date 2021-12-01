package com.ctf.service.impl;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.Person;
import com.ctf.dao.AskForLeaveDao;
import com.ctf.dao.PersonDao;
import com.ctf.service.AskForLeaveService;
import com.ctf.utils.DateUtils;
import org.apache.commons.dbutils.DbUtils;

import java.text.ParseException;
import java.util.*;

public class AskForLeaveServiceImpl implements AskForLeaveService {

    AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    PersonDao personDao = new PersonDao();

    @Override
    public List<HashMap<String, Object>> querySomeLeaveInfos(Map<String, String[]> map) throws ParseException {
        List<LeaveInfo> leaveInfoList =  askForLeaveDao.querySomeLeaveInfos(map);
        return getLeaveInfo(leaveInfoList);
    }

    @Override
    public List<HashMap<String, Object>> queryAllLeaveInfoLimit(int pageNo, int pageSize) {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryAllLeaveInfoLimit(pageNo,pageSize);
        return getLeaveInfo(leaveInfoList);
    }

    @Override
    public List<HashMap<String,Object>> queryAllLeaveInfo() {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryAllLeaveInfo();
        return getLeaveInfo(leaveInfoList);
    }

    @Override
    public Integer addLeaveInfo(LeaveInfo leaveInfo) {
        //计算预计到假时间，并为leaveInfo绑定此项数据
        Date start_date = leaveInfo.getStart_date();
        int leave_days_projected = leaveInfo.getLeave_days_projected();
        Date end_date_maybe = DateUtils.addAndSubtractDays(start_date,leave_days_projected);

        leaveInfo.setEnd_date_maybe(end_date_maybe);

        /**********************************************/
        //operator：测试用数据
        leaveInfo.setStart_leave_operator("测试请假操作者");

        leaveInfo.setApproval_status("待审批");

        System.out.println(leaveInfo);

        return askForLeaveDao.addLeaveInfoDao(leaveInfo);
    }



    //将leaveinfo对象和person对象合并封装成一个map，并加入list，以list形式返回前端
    public List<HashMap<String,Object>> getLeaveInfo(List<LeaveInfo> leaveInfoList) {
        List<HashMap<String,Object>> mapList = new ArrayList<>();
        for (LeaveInfo leaveInfo: leaveInfoList){
            int  person_id = leaveInfo.getPerson_id();
            Person person = personDao.queryPersonInfoByID(person_id);
            //封装回显数据
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("serialnumber",leaveInfo.getSerialnumber());
            hashMap.put("name",person.getName());
            hashMap.put("office",person.getOffice());
            hashMap.put("post",person.getPost());
            hashMap.put("phone", person.getPhone());
            hashMap.put("leave_type",leaveInfo.getLeave_type());
            hashMap.put("start_date",leaveInfo.getStart_date());
            hashMap.put("leave_days_projected",leaveInfo.getLeave_days_projected());
            hashMap.put("work_leader",leaveInfo.getWork_leader());
            hashMap.put("leave_reason",leaveInfo.getLeave_reason());
            hashMap.put("approver",leaveInfo.getApprover());
            hashMap.put("depart_location",leaveInfo.getDepart_location());
            hashMap.put("arrive_location",leaveInfo.getArrive_location());
            hashMap.put("end_date_maybe",leaveInfo.getEnd_date_maybe());
            hashMap.put("start_leave_remark",leaveInfo.getStart_leave_remark());
            hashMap.put("start_leave_operator",leaveInfo.getStart_leave_operator());
            hashMap.put("approval_status",leaveInfo.getApproval_status());
            mapList.add(hashMap);
        }

        return mapList;
    }

}
