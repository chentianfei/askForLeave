package com.ctf.bean;

import java.util.Date;

 /**
  * @ClassName: Person
  * @Package: com.ctf.bean
  * @Description：
  * @Author: CTF
  * @Date：2022/5/9 23:17
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
    //参公年月【2022年新系统新增】
    private Date start_work_date;
    //本人籍贯
    private String nativePlace;
    //婚姻状态【2022年新系统新增】
    private String marriage_status;
    //配偶姓名【2022年新系统新增】
    private String name_spouse;
    //配偶籍贯【2022年新系统新增】
    private String nativeplace_spouse;
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

    public Person() {
    }

    public Person(Integer person_id, String name, String sex, String nation, Date birthDate, Date start_work_date, String nativePlace, String marriage_status, String name_spouse, String nativeplace_spouse, String office, String post, String area_class, String level, String phone, Integer allow_Leave_Days) {
        this.person_id = person_id;
        this.name = name;
        this.sex = sex;
        this.nation = nation;
        this.birthDate = birthDate;
        this.start_work_date = start_work_date;
        this.nativePlace = nativePlace;
        this.marriage_status = marriage_status;
        this.name_spouse = name_spouse;
        this.nativeplace_spouse = nativeplace_spouse;
        this.office = office;
        this.post = post;
        this.area_class = area_class;
        this.level = level;
        this.phone = phone;
        this.allow_Leave_Days = allow_Leave_Days;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", birthDate=" + birthDate +
                ", start_work_date=" + start_work_date +
                ", nativePlace='" + nativePlace + '\'' +
                ", marriage_status='" + marriage_status + '\'' +
                ", name_spouse='" + name_spouse + '\'' +
                ", nativeplace_spouse='" + nativeplace_spouse + '\'' +
                ", office='" + office + '\'' +
                ", post='" + post + '\'' +
                ", area_class='" + area_class + '\'' +
                ", level='" + level + '\'' +
                ", phone='" + phone + '\'' +
                ", allow_Leave_Days=" + allow_Leave_Days +
                '}';
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

     public Date getStart_work_date() {
         return start_work_date;
     }

     public void setStart_work_date(Date start_work_date) {
         this.start_work_date = start_work_date;
     }

     public String getNativePlace() {
         return nativePlace;
     }

     public void setNativePlace(String nativePlace) {
         this.nativePlace = nativePlace;
     }

     public String getMarriage_status() {
         return marriage_status;
     }

     public void setMarriage_status(String marriage_status) {
         this.marriage_status = marriage_status;
     }

     public String getName_spouse() {
         return name_spouse;
     }

     public void setName_spouse(String name_spouse) {
         this.name_spouse = name_spouse;
     }

     public String getNativeplace_spouse() {
         return nativeplace_spouse;
     }

     public void setNativeplace_spouse(String nativeplace_spouse) {
         this.nativeplace_spouse = nativeplace_spouse;
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
 }
