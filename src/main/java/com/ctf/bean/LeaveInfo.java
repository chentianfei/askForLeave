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
}
