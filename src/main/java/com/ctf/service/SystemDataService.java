package com.ctf.service;

import com.ctf.bean.LeaveType;
import com.ctf.bean.Level;
import com.ctf.bean.Nation;
import com.ctf.bean.Office;

import java.util.List;

public interface SystemDataService {
    //查询office 单位名单
    List<Office> queryOffice();
    //查询level 职级名单
    List<Level> queryLevel();
    //查询nation 民族名单
    List<Nation> queryNaiton();
    //查询leave_type 请假种类名单
    List<LeaveType> queryLeaveType();
}
