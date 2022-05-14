package com.ctf.bean;

/**
 * @ClassName: Office
 * @Package: com.ctf.bean
 * @Description：工作单位类
 * @Author: CTF
 * @Date：2021/11/10 21:26
 */

public class Office extends Person {
    private String office_name;//工作单位名称
    private int id;//工作单位id

    public Office() {
    }

    public Office(String office_name, int id) {
        this.office_name = office_name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Office{" +
                "office_name='" + office_name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getOffice_name() {
        return office_name;
    }

    public void setOffice_name(String office_name) {
        this.office_name = office_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
