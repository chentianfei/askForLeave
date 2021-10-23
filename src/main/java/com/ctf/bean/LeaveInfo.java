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
public class LeaveInfo {

    private Person person;//请假者基本信息
    private String person_name;//请假者姓名
    private String office;//现在工作单位
    private String job;//现任职务
    private String phoneNum;//联系电话

    private String info_id;//流水号
    private String leaveType;//请假类型
    private Integer days;//预计请假天数
    private Integer actualDays;//预计请假天数
    private Date startDate;//开始日期
    private Date endDate;//预计到岗日期
    private Date actualEndDate;//实际到岗日期
    private String workLeader;//不在岗期间主持工作领导
    private String reason;//请假事由
    private String permitPerson;//批准人
    private String startLocation;//出发地
    private String endLocation;//到达地
    private String leaveRemark;//请假备注

    private boolean approveStatus;//审批状态

    private String removeComment;//销假备注

    private Integer thisYearRestDays;//本年度剩余可请假天数
    private Integer shijiaDays;//已请事假天数
    private Integer xiujiaDays;//已请休假天数
    private Integer bingjiaDays;//已请病假天数
    private Integer hunjiaDays;//已请婚假天数
    private Integer sangjiaDays;//已请丧假天数
    private Integer peichanjiaDays;//已请陪产假天数
    private Integer chanjiaDays;//已请产假天数
    private Integer chuchaiDays;//已请出差天数
    private Integer peixunDays;//已请培训天数
    private Integer hanshouDays;//已请函授天数
    private Integer nextYearRestDays;//下年度剩余可请假天数

    private boolean isThisYearLeave;//本年度是否允许休假
    private boolean isNextYearLeave;//下一年度是否允许休假

    private Date thisYear;//记录服务器当前时间，用于判断是否跨年

    public LeaveInfo() {
    }

    public String getLeaveRemark() {
        return leaveRemark;
    }

    public void setLeaveRemark(String leaveRemark) {
        this.leaveRemark = leaveRemark;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public Integer getActualDays() {
        return actualDays;
    }

    public void setActualDays(Integer actualDays) {
        this.actualDays = actualDays;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPerson_name() {
        return person.getPerson_name();
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getOffice() {
        return person.getOffice();
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getJob() {
        return person.getJob();
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(boolean approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWorkLeader() {
        return workLeader;
    }

    public void setWorkLeader(String workLeader) {
        this.workLeader = workLeader;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPermitPerson() {
        return permitPerson;
    }

    public void setPermitPerson(String permitPerson) {
        this.permitPerson = permitPerson;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }


    public String getRemoveComment() {
        return removeComment;
    }

    public void setRemoveComment(String removeComment) {
        this.removeComment = removeComment;
    }

    public Integer getThisYearRestDays() {
        return thisYearRestDays;
    }

    public void setThisYearRestDays(Integer thisYearRestDays) {
        this.thisYearRestDays = thisYearRestDays;
    }

    public Integer getShijiaDays() {
        return shijiaDays;
    }

    public void setShijiaDays(Integer shijiaDays) {
        this.shijiaDays = shijiaDays;
    }

    public Integer getXiujiaDays() {
        return xiujiaDays;
    }

    public void setXiujiaDays(Integer xiujiaDays) {
        this.xiujiaDays = xiujiaDays;
    }

    public Integer getBingjiaDays() {
        return bingjiaDays;
    }

    public void setBingjiaDays(Integer bingjiaDays) {
        this.bingjiaDays = bingjiaDays;
    }

    public Integer getHunjiaDays() {
        return hunjiaDays;
    }

    public void setHunjiaDays(Integer hunjiaDays) {
        this.hunjiaDays = hunjiaDays;
    }

    public Integer getSangjiaDays() {
        return sangjiaDays;
    }

    public void setSangjiaDays(Integer sangjiaDays) {
        this.sangjiaDays = sangjiaDays;
    }

    public Integer getPeichanjiaDays() {
        return peichanjiaDays;
    }

    public void setPeichanjiaDays(Integer peichanjiaDays) {
        this.peichanjiaDays = peichanjiaDays;
    }

    public Integer getChanjiaDays() {
        return chanjiaDays;
    }

    public void setChanjiaDays(Integer chanjiaDays) {
        this.chanjiaDays = chanjiaDays;
    }

    public Integer getChuchaiDays() {
        return chuchaiDays;
    }

    public void setChuchaiDays(Integer chuchaiDays) {
        this.chuchaiDays = chuchaiDays;
    }

    public Integer getPeixunDays() {
        return peixunDays;
    }

    public void setPeixunDays(Integer peixunDays) {
        this.peixunDays = peixunDays;
    }

    public Integer getHanshouDays() {
        return hanshouDays;
    }

    public void setHanshouDays(Integer hanshouDays) {
        this.hanshouDays = hanshouDays;
    }

    public Integer getNextYearRestDays() {
        return nextYearRestDays;
    }

    public void setNextYearRestDays(Integer nextYearRestDays) {
        this.nextYearRestDays = nextYearRestDays;
    }

    public boolean isThisYearLeave() {
        return isThisYearLeave;
    }

    public void setThisYearLeave(boolean thisYearLeave) {
        isThisYearLeave = thisYearLeave;
    }

    public boolean isNextYearLeave() {
        return isNextYearLeave;
    }

    public void setNextYearLeave(boolean nextYearLeave) {
        isNextYearLeave = nextYearLeave;
    }

    public Date getThisYear() {
        return thisYear;
    }

    public void setThisYear(Date thisYear) {
        this.thisYear = thisYear;
    }
}
