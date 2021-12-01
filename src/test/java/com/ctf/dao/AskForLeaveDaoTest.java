package com.ctf.dao;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AskForLeaveDaoTest {

    AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    PersonDao personDao = new PersonDao();
    @Test
    void queryAllLeaveInfo() {

        List<HashMap<String,Object>> mapList = new ArrayList<>();


        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryAllLeaveInfo();
        int person_id;

        for (LeaveInfo leaveInfo: leaveInfoList){
            person_id = leaveInfo.getPerson_id();
            System.out.println("leaveInfo:"+leaveInfo);
//            System.out.println("person_id:"+person_id);
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

       /* for(HashMap<String,Object> objectHashMap :mapList){

            for(Map.Entry<String,Object> map : objectHashMap.entrySet()){
                System.out.println("key = " + map.getKey() + ", value = " + map.getValue());
            }

        }*/

    }
}