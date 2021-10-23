package com.ctf.bean;

import java.util.Date;

/**
*@Description :
*@ClassName Person
*@Author tianfeichen
*@Date 2021/8/20 20:35
*@Version v1.0
*/
public class Person {
    //人员编号
    private Integer person_id;
    //姓名
    private String person_name;
    //性别
    private String sex;
    //出生年月
    private Date birthDate;
    //本人籍贯
    private String nativePlace;
    //工作单位
    private String office;
    //现任职务
    private String job;
    //所在类区
    private String area;
    //职级
    private String level;
    //联系电话
    private String phoneNum;
    //允许休假天数
    private Integer leaveDays;
    //相关领导id
    private Integer leaderID;
    //相关领导姓名
    private Integer leaderName;
    //民族
    private String nationality;

    public Person() {
    }

    public Person(Integer person_id, String person_name, String sex,
                  Date birthDate, String nativePlace, String office, String job,
                  String area, String level, String phoneNum, Integer leaveDays,
                  Integer leaderID, String nationality) {
        this.person_id = person_id;
        this.person_name = person_name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.nativePlace = nativePlace;
        this.office = office;
        this.job = job;
        this.area = area;
        this.level = level;
        this.phoneNum = phoneNum;
        this.leaveDays = leaveDays;
        this.leaderID = leaderID;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", person_name='" + person_name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", nativePlace='" + nativePlace + '\'' +
                ", office='" + office + '\'' +
                ", job='" + job + '\'' +
                ", area='" + area + '\'' +
                ", level='" + level + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", leaveDays=" + leaveDays +
                ", leaderID=" + leaderID +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }

    public Integer getLeaderID() {
        return leaderID;
    }

    public void setLeaderID(Integer leaderID) {
        this.leaderID = leaderID;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
