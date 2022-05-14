package com.ctf.service;

import com.ctf.bean.*;

import java.util.List;

public interface SystemDataService {
    //查询nation 民族名单
    List<Nation> queryNaiton();

    //修改短信提醒天数
    int updateSmsAlertDays(int newDays);
    //修改短信发送对象
    int updateSendObj(String objName,int status);
    //查询发送对象状态码
    int querySendObjStatusCode(String objName);

    //新增单位
    int addAOffice(String office_name);
    // 删除单位
    int deleteAOfficeByOfficeId(int id);
    // 修改单位信息
    int updateOfficeInfo(Office office);
    //查询office 单位名单
    List<Office> queryOffice(Integer pageNo,Integer pageSize);
    //查询office 单位名单
    Office queryOfficeByOfficeId(Integer office_id);

    //新增职级
    int addALevelInfo(String level_name);
    // 删除职级
    int deleteALevelInfoByLevelInfoId(int id);
    // 修改职级信息
    int updateLevelInfo(Level level);
    // 查询level 职级名单
    List<Level> queryLevel(Integer pageNo, Integer pageSize);

    //新增假期类型
    int addALeaveType(String leave_type);
    // 删除假期类型
    int deleteALeaveTypeByLeaveTypeId(int id);
    // 修改假期类型信息
    int updateLeaveType(LeaveType leaveType);
    // 查询leave_type 请假种类名单
    List<LeaveType> queryLeaveType(Integer pageNo, Integer pageSize);

    //新增用户（创建用户）
    int addAAccount(User user);
    //删除指定用户
    int deleteAAccount(int userId);
    //修改指定用户登录密码
    int updatePasswordByUserID(int id, String newPWD,String oldPWD);
    //重置密码
    int resetPasswordByUserID(int id);
    //修改指定用户信息
    int updateUserInfo(User newUserInfo);
    //查询所有用户信息
    List<User> queryAccountInfo(Integer pageNo, Integer pageSize);
    //查询所有角色信息
    List<Role> queryRoleInfo(Integer pageNo, Integer pageSize);
    //查询短信提醒天数
    int querySmsAlertDays();
    //根据单位id查询单位领导信息
    List<Leader> queryOfficeLeaderByOfficeId(Integer office_id);
}
