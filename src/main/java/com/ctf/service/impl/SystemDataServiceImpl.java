package com.ctf.service.impl;

import com.ctf.bean.*;
import com.ctf.dao.RoleDao;
import com.ctf.dao.SystemDataDao;
import com.ctf.dao.UserDao;
import com.ctf.service.SystemDataService;

import java.util.List;

public class SystemDataServiceImpl implements SystemDataService {

    private SystemDataDao systemDataDao = new SystemDataDao();
    private UserDao userDao = new UserDao();
    private RoleDao roleDao = new RoleDao();

    @Override
    /*
     * @Description ：查询短信提醒天数
     * @Param
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 23:36
     */
    public int querySmsAlertDays() {
        return systemDataDao.querySmsAlertDays();
    }

    @Override
    /*
     * @Description ：查询发送对象状态码
     * @Param objName
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 21:52
     */
    public int querySendObjStatusCode(String objName) {
        return systemDataDao.querySendObjStatusCode(objName);
    }

    @Override
    /*
     * @Description ：//修改短信提醒天数
     * @Param newDays
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:14
     */
    public int updateSmsAlertDays(int newDays) {
        return systemDataDao.updateSmsAlertDays(newDays);
    }

    @Override
    /*
     * @Description ：修改短信发送对象
     * @Param objName
     * @Param status
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:14
     */
    public int updateSendObj(String objName, int status) {
        return systemDataDao.updateSendObj(objName,status);
    }

    @Override
    /*
     * @Description ：新增单位
     * @Param office
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:14
     */
    public int addAOffice(String office_name) {
        int code = 0;
        if(office_name!=null){
            if(!office_name.equals("")){
                //遍历查找该名称是否已被登记，如果被登记返回-2
                List<Office> offices = queryOffice(null, null);
                for(Office office:offices){
                    if(office.getOffice_name().equals(office_name)){
                        return -2;
                    }
                }
                code = systemDataDao.addAOffice(office_name);
            }
        }
        return code;
    }

    @Override
    /*
     * @Description ：删除单位
     * @Param id
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:14
     */
    public int deleteAOfficeByOfficeId(int id) {
        return systemDataDao.deleteAOfficeByOfficeId(id);
    }

    @Override
    /*
     * @Description ：修改单位信息
     * @Param office
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:14
     */
    public int updateOfficeInfo(Office office) {
        return systemDataDao.updateOfficeInfo(office);
    }

    @Override
    public Office queryOfficeByOfficeId(Integer office_id) {
        return systemDataDao.queryOfficeByOfficeId(office_id);
    }

    @Override
    public List<Leader> queryOfficeLeaderByOfficeId(Integer office_id) {
        return systemDataDao.queryOfficeLeaderByOfficeId(office_id);

    }

    @Override
    /*
     * @Description ：新增职级
     * @Param level
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:14
     */
    public int addALevelInfo(String level_name) {
        List<Level> levels = queryLevel(null, null);
        //遍历查找该名称是否已被登记，如果被登记返回-2
        for(Level level:levels){
            if(level.getLevel_name().equals(level_name)){
                return -2;
            }
        }
        return systemDataDao.addALevelInfo(level_name);
    }

    @Override
    /*
     * @Description ：删除职级
     * @Param id
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:15
     */
    public int deleteALevelInfoByLevelInfoId(int id) {
        return systemDataDao.deleteALevelInfoByLevelInfoId(id);
    }

    @Override
    /*
     * @Description ：修改职级信息
     * @Param level
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:15
     */
    public int updateLevelInfo(Level level) {
        return systemDataDao.updateLevelInfo(level);
    }

    @Override
    /*
     * @Description ：新增假期类型
     * @Param leaveType
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:15
     */
    public int addALeaveType(String leave_type) {
        List<LeaveType> leaveTypes = queryLeaveType(null, null);
        //遍历查找该名称是否已被登记，如果被登记返回-2
        for(LeaveType leaveType:leaveTypes){
            if(leaveType.getLeave_type().equals(leave_type)){
                return -2;
            }
        }
        return systemDataDao.addALeaveType(leave_type);
    }

    @Override
    /*
     * @Description ：删除假期类型
     * @Param id
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:15
     */
    public int deleteALeaveTypeByLeaveTypeId(int id) {
        return systemDataDao.deleteALeaveTypeByLeaveTypeId(id);
    }

    @Override
    /*
     * @Description ：修改假期类型信息
     * @Param leaveType
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:15
     */
    public int updateLeaveType(LeaveType leaveType) {
        return systemDataDao.updateLeaveType(leaveType);
    }

    @Override
    /*
     * @Description ：新增用户（创建用户）
     * @Param user
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:15
     */
    public int addAAccount(User user) {
        user.setPassword("123456");
        return userDao.addAAccount(user);
    }

    @Override
    /*
     * @Description ：删除指定用户
     * @Param userId
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:16
     */
    public int deleteAAccount(int userId) {
        return userDao.deleteAAccount(userId);
    }

    @Override
    /*
     * @Description ：修改指定用户登录密码
     * @Param id
     * @Param newPWD
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:16
     */
    public int updatePasswordByUserID(int id, String newPWD,String oldPWD) {
        if(userDao.isPWDCorrect(oldPWD,id)){
            //密码验证正确
            return userDao.updatePasswordByUserID(id,newPWD);
        }
        return -2;
    }

    @Override
    /*
     * @Description ：重置密码
     * @Param id
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/9 22:29
     */
    public int resetPasswordByUserID(int id) {
        String originalPWD = "123456";
        return userDao.updatePasswordByUserID(id,originalPWD);
    }

    //判断密码是否正确
    public boolean isPWDCorrect(String password,int userID){
        return userDao.isPWDCorrect(password,userID);
    }

    @Override
    /*
     * @Description ：修改指定用户信息
     * @Param newUserInfo
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/8 20:16
     */
    public int updateUserInfo(User newUserInfo) {
        return userDao.updateUserInfo(newUserInfo);
    }

    @Override
    /*
     * @Description ：查询所有角色信息
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<Role>
     * @Author: CTF
     * @Date ：2021/12/9 21:40
     */
    public List<Role> queryRoleInfo(Integer pageNo, Integer pageSize) {
        return roleDao.queryRoleInfo(pageNo,pageSize);
    }

    @Override
    /*
     * @Description ：查询所有用户信息
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<User>
     * @Author: CTF
     * @Date ：2021/12/8 20:16
     */
    public List<User> queryAccountInfo(Integer pageNo, Integer pageSize) {
        return userDao.queryAccountInfo(pageNo,pageSize);
    }

    @Override
    /*
     * @Description ：查询leave_type 请假种类名单
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<LeaveType>
     * @Author: CTF
     * @Date ：2021/12/8 20:16
     */
    public List<LeaveType> queryLeaveType(Integer pageNo, Integer pageSize) {
        return systemDataDao.queryLeaveType(pageNo,pageSize);
    }

    @Override
    /*
     * @Description ：查询office 单位名单
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<Office>
     * @Author: CTF
     * @Date ：2021/12/8 20:16
     */
    public List<Office> queryOffice(Integer pageNo,Integer pageSize) {
        return systemDataDao.queryOffice(pageNo,pageSize);
    }

    @Override
    /*
     * @Description ：查询level 职级名单
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<Level>
     * @Author: CTF
     * @Date ：2021/12/8 20:17
     */
    public List<Level> queryLevel(Integer pageNo, Integer pageSize){
        return systemDataDao.queryLevel(pageNo,pageSize);
    }

    @Override
    /*
     * @Description ：查询nation 民族名单
     * @Param 
     * @Return ：List<Nation>
     * @Author: CTF
     * @Date ：2021/12/8 20:17
     */
    public List<Nation> queryNaiton() {
        return systemDataDao.queryNation();
    }
}
