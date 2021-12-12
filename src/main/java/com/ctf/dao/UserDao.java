package com.ctf.dao;

import com.ctf.bean.Office;
import com.ctf.bean.Role;
import com.ctf.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao{

    //根据用户名和密码判断该用户是否存在，返回boolean
    public boolean isUserExist(String username,String password){
        User user = queryAUser(username, password);
        if(user != null){
            return true;
        }
        return false;
    }

    //根据用户名和密码判断该用户是否存在，返回User
    public User queryAUser(String username,String password){
        String sql = "select * from user_info where user_name=? and password=?";

        return queryForOne(User.class,sql,username,password);
    }

    //根据用户编号查询用户名称
    public String queryNameById(Integer id){
        String sql = "select user_name from user_info where id=?";
        Object o = queryForSingleValue(sql, id);
        return o.toString();
    }

    //根据用户编号查询角色信息
    public Role queryRoleInfoByUserId(Integer userId){
        String sql = "select * from role_info " +
                "where id=(select role_id from user_info where id=?)";
        return queryForOne(Role.class,sql,userId);
    }

    //新增用户
    public int addAAccount(User user){
        String sql = "insert into user_info(user_name,password,operator,operator_phone," +
                "office,role_id) " +
                " values(?,?,?,?,?,?)";
        List<Object> parmas = new ArrayList<>();
        parmas.add(user.getUser_name());
        parmas.add(user.getPassword());
        parmas.add(user.getOperator());
        parmas.add(user.getOperator_phone());
        parmas.add(user.getOffice());
        parmas.add(user.getRole_id());

        return update(sql,parmas.toArray());
    }

    //删除指定用户
    public int deleteAAccount(int userId){
        String sql = "delete from user_info where id = ?";
        return update(sql,userId);
    }

    //修改指定用户登录密码
    public int updatePasswordByUserID(int id,String newPWD){
        String sql = "update user_info set password=? where id=?";
        return update(sql,newPWD,id);
    }

    //修改指定用户信息
    public int updateUserInfo(User newUserInfo){
        String sql = "update user_info set user_name=?," +
                "operator=?, " +
                "operator_phone=?, " +
                "office=?, " +
                "role_id=? " +
                " where id=?";

        List<Object> parmas = new ArrayList<>();
        parmas.add(newUserInfo.getUser_name());
        parmas.add(newUserInfo.getOperator());
        parmas.add(newUserInfo.getOperator_phone());
        parmas.add(newUserInfo.getOffice());
        parmas.add(newUserInfo.getRole_id());

        parmas.add(newUserInfo.getId());
        return update(sql,parmas.toArray());
    }

    //判断密码是否正确
    public boolean isPWDCorrect(String password,int userID){
        String sql = "select * from user_info where id=?";
        User user = queryForOne(User.class, sql, userID);

        if(user.getPassword().equals(password)){
            return true;
        }

        return false;
    }

    //查询所有用户信息
    public List<User> queryAccountInfo(Integer pageNo, Integer pageSize) {
        StringBuilder sql = new StringBuilder("select * from user_info where 1=1 ");

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

        List<User> users = queryForList(User.class, sql.toString(), parmas.toArray());
        for(User user : users){
            //不显示密码
            user.setPassword("*********");
        }

        return  users;
    }


}
