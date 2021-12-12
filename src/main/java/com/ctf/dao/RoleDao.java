package com.ctf.dao;

import com.ctf.bean.Role;
import com.ctf.bean.User;

import java.util.ArrayList;
import java.util.List;

public class RoleDao extends BaseDao{
    //查询所有用户信息
    public List<Role> queryRoleInfo(Integer pageNo, Integer pageSize) {
        StringBuilder sql = new StringBuilder("select * from role_info where 1=1 ");

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

        List<Role> roles = queryForList(Role.class, sql.toString(), parmas.toArray());


        return  roles;
    }
}
