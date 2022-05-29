package com.ctf.bean;

import javax.xml.crypto.Data;
import java.util.Date;

public class SMSSendLog {
    private int SerialNo_sendmsg_thisSystem;//该系统发送编号
    private String PhoneNumber;//下发手机号码
    private int SerialNo_askforleave_thisSystem;//请假流水号
    private int person_id;//下发对象人员编号
    private String SerialNo_sendmsg_server;//BizId 发送回执ID。服务商流水号
    private String TemplateId;//短信模板id
    private String SignName;//短信签名内容
    private String SmsContent;//短信内容
    private String Code;//发送状态
    private String Message;//错误描述
    private Date send_startdate;//发送起始时间
    private Date send_enddate;//发送完成时间
    private String operator;//操作者

    public SMSSendLog() {
    }

    public SMSSendLog(int serialNo_sendmsg_thisSystem, String phoneNumber,
                      int serialNo_askforleave_thisSystem, int person_id,
                      String serialNo_sendmsg_server, String templateId,
                      String signName, String smsContent, String code, String message,
                      Date send_startdate, Date send_enddate, String operator) {
        SerialNo_sendmsg_thisSystem = serialNo_sendmsg_thisSystem;
        PhoneNumber = phoneNumber;
        SerialNo_askforleave_thisSystem = serialNo_askforleave_thisSystem;
        this.person_id = person_id;
        SerialNo_sendmsg_server = serialNo_sendmsg_server;
        TemplateId = templateId;
        SignName = signName;
        SmsContent = smsContent;
        Code = code;
        Message = message;
        this.send_startdate = send_startdate;
        this.send_enddate = send_enddate;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "SMSSendLog{" +
                "SerialNo_sendmsg_thisSystem=" + SerialNo_sendmsg_thisSystem +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", SerialNo_askforleave_thisSystem=" + SerialNo_askforleave_thisSystem +
                ", person_id=" + person_id +
                ", SerialNo_sendmsg_server='" + SerialNo_sendmsg_server + '\'' +
                ", TemplateId='" + TemplateId + '\'' +
                ", SignName='" + SignName + '\'' +
                ", SmsContent='" + SmsContent + '\'' +
                ", Code='" + Code + '\'' +
                ", Message='" + Message + '\'' +
                ", send_startdate='" + send_startdate + '\'' +
                ", send_enddate=" + send_enddate +
                ", operator=" + operator +
                '}';
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getSerialNo_askforleave_thisSystem() {
        return SerialNo_askforleave_thisSystem;
    }

    public void setSerialNo_askforleave_thisSystem(int serialNo_askforleave_thisSystem) {
        SerialNo_askforleave_thisSystem = serialNo_askforleave_thisSystem;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getSerialNo_sendmsg_server() {
        return SerialNo_sendmsg_server;
    }

    public void setSerialNo_sendmsg_server(String serialNo_sendmsg_server) {
        SerialNo_sendmsg_server = serialNo_sendmsg_server;
    }

    public String getTemplateId() {
        return TemplateId;
    }

    public void setTemplateId(String templateId) {
        TemplateId = templateId;
    }

    public String getSignName() {
        return SignName;
    }

    public void setSignName(String signName) {
        SignName = signName;
    }

    public String getSmsContent() {
        return SmsContent;
    }

    public void setSmsContent(String smsContent) {
        SmsContent = smsContent;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Date getSend_startdate() {
        return send_startdate;
    }

    public void setSend_startdate(Date send_startdate) {
        this.send_startdate = send_startdate;
    }

    public Date getSend_enddate() {
        return send_enddate;
    }

    public void setSend_enddate(Date send_enddate) {
        this.send_enddate = send_enddate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getSerialNo_sendmsg_thisSystem() {
        return SerialNo_sendmsg_thisSystem;
    }

    public void setSerialNo_sendmsg_thisSystem(int serialNo_sendmsg_thisSystem) {
        SerialNo_sendmsg_thisSystem = serialNo_sendmsg_thisSystem;
    }
}
