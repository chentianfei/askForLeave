package com.ctf.service.impl;

import com.ctf.bean.LeaveInfo;
import com.ctf.service.LeavaInfoService;

import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @ClassName LeavaInfoServiceImpl
 * @Author tianfeichen
 * @Date 2021/9/4 23:00
 * @Version v1.0
 */
public class LeavaInfoServiceImpl implements LeavaInfoService{

    @Override
    public int askForLeaveService(Map<String, Object> parameter) {
        return 0;
    }

    @Override
    public int restDays(String leaveType) {
        return 0;
    }

    @Override
    public Map<String, Map<Integer, Integer>> getHistoryLeaveData() {
        return null;
    }

    @Override
    public List<LeaveInfo> queryLeaveAuditData(String conditions) {
        return null;
    }

    @Override
    public int leaveInfoAuditService(Integer leaveinfoID) {
        return 0;
    }

    @Override
    public List<LeaveInfo> queryEndOfLeaveAuditData(String conditions) {
        return null;
    }

    @Override
    public int endOfLeaveInfoService(Integer leaveinfoID) {
        return 0;
    }

    @Override
    public List<LeaveInfo> queryHistoryLeaveInfo(String conditions) {
        return null;
    }
}
