package com.ctf.service;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.LeaveInfoCount;
import com.ctf.bean.User;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AskForLeaveService {

    //按人员编号查询本年度某种假期的详细请假数据
    List<LeaveInfo> queryOnesHistoryInfoOfOneLeaveTypeByPersonID(Integer person_id,String leave_type,Integer pageNo,Integer pageSize);
    //按人员编号查询本年度某种假期统计数据
    LeaveInfoCount queryThisYearOneLeaveInfoCountByPersonID(Integer person_id,String leave_type);
    //按人员编号查询近期累计在岗天数
    int queryRecentWorkDaysByPersonID(Integer person_id);

    //根据人员编号person_id查询本年度请假信息统计数据
    List<LeaveInfoCount>  queryThisYearLeaveInfoCountByPersonID(Integer person_id, Integer pageNo, Integer pageSize);
    //根据人员编号person_id查询本年度历史请假记录
    List<HashMap<String,Object>> queryOnesHistoryInfoByPersonID(Integer person_id,Integer pageNo,Integer pageSize);

    //添加请假信息
     Integer addLeaveInfo(LeaveInfo leaveInfo);
    //查询库中所有请假待审核信息
      List<HashMap<String,Object>> queryAllLeaveInfo();
    //分页查询库中所有请假待审核信息
      List<HashMap<String,Object>> queryAllLeaveInfoLimit(int pageNo,int pageSize);
    //按条件查询请假待审核信息_不分页
     List<HashMap<String,Object>> querySomeLeaveInfos(Map<String, String[]> map) throws ParseException;
    //按条件查询请假待审核信息_分页
     List<HashMap<String,Object>> querySomeLeaveInfosLimit(Map<String, String[]> map,Integer pageNo,Integer pageSize) throws ParseException;
    //根据请假信息解析人员编号，并返回此编号对应的人员信息
     List<HashMap<String,Object>> queryPersonInfoByIdRTNMap(int serialnumber) throws ParseException;
    //处理请假审批中同意业务
     int agreeLeave(int serialnumber);
    //处理请假审批中不同意业务
     int notAgreeLeave(int serialnumber,String approval_reason);

    //查询库中所有待销假信息
      List<HashMap<String,Object>> queryALLResumeWorkInfo();
    //分页查询库中所有待销假信息
      List<HashMap<String,Object>> queryALLResumeWorkInfoLimit(int pageNo,int pageSize);
    //按条件查询待销假信息_不分页
     List<HashMap<String,Object>> querySomeResumeWorkInfo(Map<String, String[]> map) throws ParseException;
    //按条件查询待销假信息_分页
     List<HashMap<String,Object>> querySomeResumeWorkInfosLimit(Map<String, String[]> map,Integer pageNo,Integer pageSize) throws ParseException;
    //查询所有今日应到假人员信息
    List<HashMap<String,Object>> queryCurrentEOLPerson();
    //分页查询今日应到假人员信息
    List<HashMap<String,Object>> queryCurrentEOLPersonLimit(Integer pageNo,Integer pageSize);
    //处理销假业务
     int resumeWork(String serialnumberSTR, String end_leave_remarkSTR,String end_dateSTR,String end_leave_operator) throws ParseException;

    //查询全部历史请假记录
     List<HashMap<String,Object>> queryALLHistoryInfo(User user);
    //分页查询库中所有历史请假记录信息
     List<HashMap<String,Object>>queryALLHistoryInfoLimit(int pageNo,int pageSize,User user);
    //按条件查询历史请假记录_不分页
     List<HashMap<String,Object>> querySomeHistoryInfo(Map<String, String[]> map) throws ParseException;
    //按条件查询历史请假记录分页
     List<HashMap<String,Object>> querySomeHistoryInfoLimit(Map<String, String[]> map,Integer pageNo,Integer pageSize) throws ParseException;
    //根据流水号查询一条历史记录，与人员基础数据合并后返回
     HashMap<String, Object> queryAHistoryInfo(int serialnumber);
    //根据流水号删除一条历史记录，并执行相关操作
     int deleteAHistoryInfo(int serialnumber, String delete_reason,
                                  Date delete_date, String delete_operator);
}
