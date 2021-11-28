package com.ctf.service.impl;

import com.ctf.bean.LeaveInfo;
import com.ctf.dao.AskForLeaveDao;
import com.ctf.service.AskForLeaveService;
import com.ctf.utils.DateUtils;
import org.apache.commons.dbutils.DbUtils;

import java.util.Date;

public class AskForLeaveServiceImpl implements AskForLeaveService {

    AskForLeaveDao askForLeaveDao = new AskForLeaveDao();

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

        leaveInfo.setApprovalstatus("待审批");

        System.out.println(leaveInfo);

        return askForLeaveDao.addLeaveInfoDao(leaveInfo);
    }



}
