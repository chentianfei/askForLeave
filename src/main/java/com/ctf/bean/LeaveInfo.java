package com.ctf.bean;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description :
 * @ClassName LeaveInfo
 * @Author tianfeichen
 * @Date 2021/8/26 02:35
 * @Version v1.0
 */
public class  LeaveInfo {

    private Integer serialnumber;//流水号
    private String name;//请假者姓名
    private Integer person_id;//人员编号
    private String leave_type;//请假种类
    private Date start_date;//开始日期
    private Integer leave_days_projected;//预计请假天数
    private String work_leader;//不在岗期间主持工作领导
    private String leave_reason;//请假事由
    private String approver;//批准人
    private String depart_location;//出发地
    private String arrive_location;//到达地
    private String start_leave_remark;//请假备注
    private Date end_date_maybe;//预计到岗日期
    private String start_leave_operator;//请假操作员
    private String office;//请假者单位【2022年新系统新增】
    private String phone;//请假者联系方式【2022年新系统新增】
    private int does_print;//回执单打印次数【2022年新系统新增】
    private String status;//事务状态【2022年新系统新增】：使用中，已废除

    private Date end_date;//销假日期（实际到岗日期）
    private String end_leave_remark;//销假备注
    private String end_leave_operator;//销假操作员
    private Integer leave_days_actual;//请假天数（实际请假天数）]

    public LeaveInfo() {
    }

    public LeaveInfo(Integer serialnumber, String name, Integer person_id, String leave_type, Date start_date,
                     Integer leave_days_projected, String work_leader, String leave_reason, String approver,
                     String depart_location, String arrive_location, String start_leave_remark, Date end_date_maybe,
                     String start_leave_operator, String office, String phone, int does_print, Date end_date,
                     String end_leave_remark, String end_leave_operator, Integer leave_days_actual,String status) {
        this.serialnumber = serialnumber;
        this.name = name;
        this.person_id = person_id;
        this.leave_type = leave_type;
        this.start_date = start_date;
        this.leave_days_projected = leave_days_projected;
        this.work_leader = work_leader;
        this.leave_reason = leave_reason;
        this.approver = approver;
        this.depart_location = depart_location;
        this.arrive_location = arrive_location;
        this.start_leave_remark = start_leave_remark;
        this.end_date_maybe = end_date_maybe;
        this.start_leave_operator = start_leave_operator;
        this.office = office;
        this.phone = phone;
        this.does_print = does_print;
        this.end_date = end_date;
        this.end_leave_remark = end_leave_remark;
        this.end_leave_operator = end_leave_operator;
        this.leave_days_actual = leave_days_actual;
        this.status = status;
    }

    @Override
    public String toString() {
        return "LeaveInfo{" +
                "流水号=" + serialnumber +
                ", 请假者姓名='" + name + '\'' +
                ", 人员编号=" + person_id +
                ", 请假种类='" + leave_type + '\'' +
                ", 开始日期=" + start_date +
                ", 预计请假天数=" + leave_days_projected +
                ", 不在岗期间主持工作领导='" + work_leader + '\'' +
                ", 请假事由='" + leave_reason + '\'' +
                ", 批准人='" + approver + '\'' +
                ", 出发地='" + depart_location + '\'' +
                ", 到达地='" + arrive_location + '\'' +
                ", 请假备注='" + start_leave_remark + '\'' +
                ", 预计到岗日期=" + end_date_maybe +
                ", 请假操作员='" + start_leave_operator + '\'' +
                ", 请假者单位='" + office + '\'' +
                ", 请假者联系方式='" + phone + '\'' +
                ", 回执单打印次数=" + does_print +
                ", 事务状态=" + status +
                ", 销假日期=" + end_date +
                ", 销假备注='" + end_leave_remark + '\'' +
                ", 销假操作员='" + end_leave_operator + '\'' +
                ", 实际请假天数'=" + leave_days_actual +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDoes_print() {
        return does_print;
    }

    public void setDoes_print(int does_print) {
        this.does_print = does_print;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getEnd_leave_remark() {
        return end_leave_remark;
    }

    public void setEnd_leave_remark(String end_leave_remark) {
        this.end_leave_remark = end_leave_remark;
    }

    public String getEnd_leave_operator() {
        return end_leave_operator;
    }

    public void setEnd_leave_operator(String end_leave_operator) {
        this.end_leave_operator = end_leave_operator;
    }

    public Integer getLeave_days_actual() {
        return leave_days_actual;
    }

    public void setLeave_days_actual(Integer leave_days_actual) {
        this.leave_days_actual = leave_days_actual;
    }

    public Integer getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(Integer serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Integer getLeave_days_projected() {
        return leave_days_projected;
    }

    public void setLeave_days_projected(Integer leave_days_projected) {
        this.leave_days_projected = leave_days_projected;
    }

    public String getWork_leader() {
        return work_leader;
    }

    public void setWork_leader(String work_leader) {
        this.work_leader = work_leader;
    }

    public String getLeave_reason() {
        return leave_reason;
    }

    public void setLeave_reason(String leave_reason) {
        this.leave_reason = leave_reason;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getDepart_location() {
        return depart_location;
    }

    public void setDepart_location(String depart_location) {
        this.depart_location = depart_location;
    }

    public String getArrive_location() {
        return arrive_location;
    }

    public void setArrive_location(String arrive_location) {
        this.arrive_location = arrive_location;
    }

    public String getStart_leave_remark() {
        return start_leave_remark;
    }

    public void setStart_leave_remark(String start_leave_remark) {
        this.start_leave_remark = start_leave_remark;
    }

    public Date getEnd_date_maybe() {
        return end_date_maybe;
    }

    public void setEnd_date_maybe(Date end_date_maybe) {
        this.end_date_maybe = end_date_maybe;
    }

    public String getStart_leave_operator() {
        return start_leave_operator;
    }

    public void setStart_leave_operator(String start_leave_operator) {
        this.start_leave_operator = start_leave_operator;
    }
}
