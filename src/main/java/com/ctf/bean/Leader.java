package com.ctf.bean;

public class Leader{
    //单位编号
    private Integer office_id;
    //单位领导姓名【2022年新系统新增】
    private String office_leader_name;
    //领导属性（正职，副职）
    private String office_leader_type;
    //单位领导联系方式【2022年新系统新增】
    private String office_leader_phone;

    public Leader(Integer office_id, String office_leader_name,
                  String office_leader_type, String office_leader_phone) {
        this.office_id = office_id;
        this.office_leader_name = office_leader_name;
        this.office_leader_type = office_leader_type;
        this.office_leader_phone = office_leader_phone;
    }

    @Override
    public String toString() {
        return "Leader{" +
                "office_id=" + office_id +
                ", office_leader_name='" + office_leader_name + '\'' +
                ", office_leader_type='" + office_leader_type + '\'' +
                ", office_leader_phone='" + office_leader_phone + '\'' +
                '}';
    }

    public Integer getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Integer office_id) {
        this.office_id = office_id;
    }

    public String getOffice_leader_name() {
        return office_leader_name;
    }

    public void setOffice_leader_name(String office_leader_name) {
        this.office_leader_name = office_leader_name;
    }

    public String getOffice_leader_type() {
        return office_leader_type;
    }

    public void setOffice_leader_type(String office_leader_type) {
        this.office_leader_type = office_leader_type;
    }

    public String getOffice_leader_phone() {
        return office_leader_phone;
    }

    public void setOffice_leader_phone(String office_leader_phone) {
        this.office_leader_phone = office_leader_phone;
    }
}
