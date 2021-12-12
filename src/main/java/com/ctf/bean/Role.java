package com.ctf.bean;

public class Role {
    //角色编号
    private Integer id;
    //角色名称
    private String role_name;
    //角色描述
    private String role_description;

    public Role() {
    }

    public Role(Integer id, String role_name, String role_description) {
        this.id = id;
        this.role_name = role_name;
        this.role_description = role_description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", role_description='" + role_description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }
}
