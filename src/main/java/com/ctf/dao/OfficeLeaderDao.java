package com.ctf.dao;

import com.ctf.bean.Leader;
import com.ctf.bean.Office;
import com.ctf.bean.Person;

import java.util.List;

public class OfficeLeaderDao extends BaseDao{
    //根据办公室名称查询办公室对应的领导
    public List<Leader> queryLeaderByOfficeName(String office_name){
        //根据办公室名称查询办公室id
        String queryIDSQL = "select * from office_info where office_name = ?";
        Office office = queryForOne(Office.class, queryIDSQL, office_name);
        int office_id = office.getId();//办公室id
        //根据办公室id查询领导信息
        String queryLeaderSQL = "select * from office_info_leader where office_id = ?";
        List<Leader> leaders = queryForList(Leader.class, queryLeaderSQL, office_id);
        return leaders;
    }
}
