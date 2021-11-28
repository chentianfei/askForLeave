package com.ctf.dao;

import com.ctf.bean.LeaveType;
import com.ctf.bean.Level;
import com.ctf.bean.Nation;
import com.ctf.bean.Office;

import java.util.List;

/**
 * @ClassName: SystemDataDao
 * @Package: com.ctf.dao
 * @Description：用于查询系统数据或公共数据
 * @Author: CTF
 * @Date：2021/11/5 18:13
 */

public class SystemDataDao extends BaseDao{

    //查询职级名单（常用于下拉框）
    public List<Level> queryLevel(){
        String sql = "select * from level_info";
        List<Level> levels = queryForList(Level.class, sql);
        return levels;
    };

    //查询请假种类名单（常用于下拉框）
    public List<LeaveType> queryLeaveType(){
        String sql = "select * from leave_type";
        return queryForList(LeaveType.class, sql);
    }

    //查询工作单位内容（常用于下拉框）
    public List<Office> queryOffice(){
        String sql = "select * from office_info";
        List<Office> offices = queryForList(Office.class, sql);
        return offices;
    }

    public List<Nation> queryNation() {
        String sql = "select * from nation_info";
        List<Nation> nations = queryForList(Nation.class, sql);
        return nations;
    }
}
