package com.ctf.bean;

public class User {
    //用户id
    private Integer id;
    //用户名
    private String user_name;
    //密码
    private String password;
    //所属单位
    private String office;
    //操作人姓名
    private String operator;
    //操作人联系方式
    private String operator_phone;
    // 角色编号
    private Integer role_id;

    public User() {
    }

    public User(Integer id, String user_name, String password, String office, String operator, String operator_phone, Integer role_id) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.office = office;
        this.operator = operator;
        this.operator_phone = operator_phone;
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", office='" + office + '\'' +
                ", operator='" + operator + '\'' +
                ", operator_phone='" + operator_phone + '\'' +
                ", role_id=" + role_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator_phone() {
        return operator_phone;
    }

    public void setOperator_phone(String operator_phone) {
        this.operator_phone = operator_phone;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}
