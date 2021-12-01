package com.ctf.service;

import com.ctf.bean.LeaveInfo;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AskForLeaveService {
    //添加请假信息
    public Integer addLeaveInfo(LeaveInfo leaveInfo);
    //查询库中所有请假待审核信息
    public  List<HashMap<String,Object>> queryAllLeaveInfo();
    //分页查询库中所有请假待审核信息
    public  List<HashMap<String,Object>> queryAllLeaveInfoLimit(int pageNo,int pageSize);
    //按条件查询请假待审核信息_不分页
    public List<HashMap<String,Object>> querySomeLeaveInfos(Map<String, String[]> map) throws ParseException;
}
