package com.ctf.service;

import com.ctf.bean.LeaveInfo;

import java.util.List;
import java.util.Map;

//处理请假、销假等一系列操作
public interface LeavaInfoService {

    //处理请假信息登记业务，传入表单数据封装而成的map，返回操作状态，-1代表失败。0代表成功
    int askForLeaveService(Map<String,Object> parameter);
    //判断今年各类型假期还剩余天数，传入假期类型名，返回该类假期今年剩余天数
    int restDays(String leaveType);
    //获取今年各类假期请假次数、具体天数，返回Map<String leaveType,Map<Interer count,Integer days>>
    Map<String,Map<Integer,Integer>> getHistoryLeaveData();

    //处理请假待审核信息查询业务，传入查询条件，返回查询到的待审核的请假信息
    List<LeaveInfo> queryLeaveAuditData(String conditions);
    //处理请假审核业务,传入请假记录的流水号，返回处理结果状态码
    int leaveInfoAuditService(Integer leaveinfoID);

    //处理待销假信息查询业务
    List<LeaveInfo> queryEndOfLeaveAuditData(String conditions);
    //处理销假业务
    int endOfLeaveInfoService(Integer leaveinfoID);

    //处理历史请假记录查询业务
    List<LeaveInfo> queryHistoryLeaveInfo(String conditions);

}
