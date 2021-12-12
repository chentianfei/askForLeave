package com.ctf.dao;

import com.ctf.bean.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SystemDataDao
 * @Package: com.ctf.dao
 * @Description：用于查询系统数据或公共数据
 * @Author: CTF
 * @Date：2021/11/5 18:13
 */

public class SystemDataDao extends BaseDao{

    //新增假期类型
    public int addALeaveType(String leave_type){
        String sql = "insert into leave_type(leave_type) values(?)";
        return update(sql,leave_type);
    }
    // 删除假期类型
    public int deleteALeaveTypeByLeaveTypeId(int id){
        String sql = "delete from leave_type where id=?";
        return update(sql,id);
    }
    // 修改假期类型信息
    public int updateLeaveType(LeaveType leaveType){
        String sql = "update leave_type set leave_type=? where id=?";
        return update(sql,leaveType.getLeave_type(),leaveType.getId());
    }

    //新增职级
    public int addALevelInfo(String level_name){
        String sql = "insert into level_info(level_name) values(?)";
        return update(sql,level_name);
    }
    // 删除职级
    public int deleteALevelInfoByLevelInfoId(int id){
        String sql = "delete from level_info where id=?";
        return update(sql,id);
    }
    // 修改职级信息
    public int updateLevelInfo(Level level){
        String sql = "update level_info set level_name=? where id=?";
        return update(sql,level.getLevel_name(),level.getId());
    }

    //新增单位
    public int addAOffice(String office_name){
        String sql = "insert into office_info(office_name) values(?)";
        return update(sql,office_name);
    }
    // 删除单位
    public int deleteAOfficeByOfficeId(int id){
        String sql = "delete from office_info where id=?";
        return update(sql,id);
    }
    // 修改单位信息
    public int updateOfficeInfo(Office office){
        String sql = "update office_info set office_name=? where id=?";
        return update(sql,office.getOffice_name(),office.getId());
    }

    //修改短信提醒天数
    public int updateSmsAlertDays(int newDays){
        String sql = "update system_info set sms_alert=?";
        return update(sql,newDays);
    }
    //修改短信发送对象
    public int updateSendObj(String objName,int status){
        String sql = "update system_info set "+objName+"=?";
        return update(sql,status);
    }

    //查询发送对象状态码
    public Integer querySendObjStatusCode(String objName) {
        String sql = "select "+objName+" from system_info";
        return Integer.parseInt(queryForANumber(sql)[0].toString());
    }

    //查询需要提前多少天提醒请假者
    public int querySmsAlertDays(){
        String smsAlertDaysSql = "select sms_alert from system_info";
        return (Integer)queryForSingleValue(smsAlertDaysSql);
    }

    //查询当前发送短信的对象代码
    public Map<String,Integer> querySendTargetCode(){
        String doesSendLeaderSql = "select doesSendLeader from system_info";
        String doesSendSelfSql = "select doesSendSelf from system_info";

        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("doesSendLeaderCode",(Integer) queryForSingleValue(doesSendLeaderSql));
        hashMap.put("doesSendSelfCode",(Integer)queryForSingleValue(doesSendSelfSql));

        return hashMap;
    }

    //查询职级名单（常用于下拉框）
    public List<Level> queryLevel(Integer pageNo,Integer pageSize){
        StringBuilder sql = new StringBuilder("select * from level_info where 1=1 ");

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();

        if(pageNo!=null && pageSize!=null){
            //需要分页查询
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;
            sql.append("order by id desc limit ?,? ");
            parmas.add(start);
            parmas.add(end);
        }

        return queryForList(Level.class, sql.toString(),parmas.toArray());
    }

    //查询请假种类名单（常用于下拉框）
    public List<LeaveType> queryLeaveType(Integer pageNo,Integer pageSize){
        StringBuilder sql = new StringBuilder("select * from leave_type where 1=1 ");

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();

        if(pageNo!=null && pageSize!=null){
            //需要分页查询
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;
            sql.append("order by id desc limit ?,? ");
            parmas.add(start);
            parmas.add(end);
        }

        return queryForList(LeaveType.class, sql.toString(),parmas.toArray());
    }

    //查询工作单位内容（常用于下拉框）
    public List<Office> queryOffice(Integer pageNo,Integer pageSize){

        StringBuilder sql = new StringBuilder("select * from office_info where 1=1 ");

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();

        if(pageNo!=null && pageSize!=null){
            //需要分页查询
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;
            sql.append("order by id desc limit ?,? ");
            parmas.add(start);
            parmas.add(end);
        }

        List<Office> offices = queryForList(Office.class, sql.toString(), parmas.toArray());

        for(int index = 0;index <= offices.size()-1; index++){
            Office office = offices.get(index);
            if(office.getId()==1){
                //是超级管理员的部门，从结果集中删除后返回
                offices.remove(index);
                //index要减1，因为删除后list长度少了一个，所有一直加下去会出现indexoutofboundsException错误
                index--;
                break;
            }
        }
        return offices;
    }

    //查询民族内容（常用于下拉框）
    public List<Nation> queryNation() {
        String sql = "select * from nation_info";
        List<Nation> nations = queryForList(Nation.class, sql);
        return nations;
    }

}
