package com.ctf.bean;

import com.ctf.dao.PersonDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private String name;
    //性别
    private String sex;
    //民族
    private String nation;
    //出生年月
    private Date birthDate;
    //本人籍贯
    private String nativePlace;
    //工作单位
    private String office;
    //现任职务
    private String post;
    //所在类区
    private String area_class;
    //职级
    private String level;
    //联系电话
    private String phone;
    //允许休假天数
    private Integer allow_Leave_Days;
    //相关领导
    private List<Person> leader;

    public Person() {
    }

    public Person(Integer person_id, String name, String sex,
                  String nation, Date birthDate, String nativePlace,
                  String office, String post, String area_class,
                  String level, String phone, Integer allow_Leave_Days,
                  List<Person> leader) {
        this.person_id = person_id;
        this.name = name;
        this.sex = sex;
        this.nation = nation;
        this.birthDate = birthDate;
        this.nativePlace = nativePlace;
        this.office = office;
        this.post = post;
        this.area_class = area_class;
        this.level = level;
        this.phone = phone;
        this.allow_Leave_Days = allow_Leave_Days;
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", office='" + office + '\'' +
                ", post='" + post + '\'' +
                ", area_class='" + area_class + '\'' +
                ", level='" + level + '\'' +
                ", phone='" + phone + '\'' +
                ", allow_Leave_Days=" + allow_Leave_Days +
                ", leader='" + leader + '\'' +
                '}' + "\n";
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getArea_class() {
        return area_class;
    }

    public void setArea_class(String area_class) {
        this.area_class = area_class;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAllow_Leave_Days() {
        return allow_Leave_Days;
    }

    public void setAllow_Leave_Days(Integer allow_Leave_Days) {
        this.allow_Leave_Days = allow_Leave_Days;
    }

    public List<Person> getLeader() {
        return leader;
    }

    public void setLeader(List<Person> leader) {
        this.leader = new PersonDao().queryRelatedLeader(this.person_id);
    }
}
