package com.ctf.bean;

/**
 * @ClassName: Office
 * @Package: com.ctf.bean
 * @Description：工作单位类
 * @Author: CTF
 * @Date：2021/11/10 21:26
 */

public class Office {
    String office_name;//工作单位名称

    public Office() {
    }

    public Office(String office_name) {
        this.office_name = office_name;
    }

    @Override
    public String toString() {
        return "Office{" +
                "office_name='" + office_name + '\'' +
                '}';
    }

    public String getOffice_name() {
        return office_name;
    }

    public void setOffice_name(String office_name) {
        this.office_name = office_name;
    }
}
