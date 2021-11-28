package com.ctf.dao;

import com.ctf.bean.LeaveInfo;

import java.util.ArrayList;
import java.util.List;

public class AskForLeaveDao extends BaseDao {
    //插入请假记录
    public int addLeaveInfoDao(LeaveInfo leaveInfo){
        String sql = "insert into approval(`name`,person_id," +
                "leave_type,start_date,leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator,approval_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String sql2 = "insert into approval_backups(`name`,person_id," +
                "leave_type,start_date,leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator,approval_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(leaveInfo.getName());
        parmas.add(leaveInfo.getPerson_id());
        parmas.add(leaveInfo.getLeave_type());
        parmas.add(leaveInfo.getStart_date());
        parmas.add(leaveInfo.getLeave_days_projected());
        parmas.add(leaveInfo.getWork_leader());
        parmas.add(leaveInfo.getLeave_reason());
        parmas.add(leaveInfo.getApprover());
        parmas.add(leaveInfo.getDepart_location());
        parmas.add(leaveInfo.getArrive_location());
        parmas.add(leaveInfo.getStart_leave_remark());
        parmas.add(leaveInfo.getEnd_date_maybe());
        parmas.add(leaveInfo.getStart_leave_operator());
        parmas.add(leaveInfo.getApprovalstatus());

        int insertCount = update(sql,parmas.toArray());

        //保存备份数据
        //update(sql2,parmas.toArray());

        return insertCount;
    }


    //按人员编号查询本年度请假情况统计数据
    //按人员编号查询本年度请假详细数据
    //按人员编号查询近期累计在岗天数
    //查询所有请假记录（展示请假审核用）
    //按条件查询请假记录（搜索请假审核用）
    //查询所有待销假记录（展示销假使用）
    //按条件查询待销假记录（搜索销假使用）
    //查询指定日期的待销假记录（待销假提示用）
    //查询所有历史请假记录（展示历史请假记录用）
    //按条件查询历史请假记录（搜索历史请假记录用）
    //按假期种类和人员编号查询并统计本年度此人此类假期总天数与总次数
    //删除历史请假记录
    //查询并返回当前某种假期可请假天数
}
