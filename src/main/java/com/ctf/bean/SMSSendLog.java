package com.ctf.bean;

import javax.xml.crypto.Data;
import java.util.Date;

public class SMSSendLog {
    private int serialNo_sendmsg_thisSystem;//该系统发送编号
    private String phoneNumber;//下发手机号码
    private int serialNo_askforleave_thisSystem;//请假流水号
    private int person_id;//下发对象人员编号
    private String serialNo_sendmsg_server;//BizId 发送回执ID。服务商流水号
    private String templateId;//短信模板id
    private String signName;//短信签名内容
    private String smsContent;//短信内容
    private String code;//发送状态
    private String message;//错误描述
    private String send_startdate;//发送起始时间
    private Date send_enddate;//发送完成时间
    private Date operator;//操作者

    public SMSSendLog() {
    }

    public SMSSendLog(int serialNo_sendmsg_thisSystem, String phoneNumber,
                            int serialNo_askforleave_thisSystem, int person_id,
                            String serialNo_sendmsg_server, String templateId,
                            String signName, String smsContent, String code,
                            String message, String send_startdate, Date send_enddate,
                            Date operator) {
        this.serialNo_sendmsg_thisSystem = serialNo_sendmsg_thisSystem;
        this.phoneNumber = phoneNumber;
        this.serialNo_askforleave_thisSystem = serialNo_askforleave_thisSystem;
        this.person_id = person_id;
        this.serialNo_sendmsg_server = serialNo_sendmsg_server;
        this.templateId = templateId;
        this.signName = signName;
        this.smsContent = smsContent;
        this.code = code;
        this.message = message;
        this.send_startdate = send_startdate;
        this.send_enddate = send_enddate;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "SMSSendLogAliyun{" +
                "serialNo_sendmsg_thisSystem=" + serialNo_sendmsg_thisSystem +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", serialNo_askforleave_thisSystem=" + serialNo_askforleave_thisSystem +
                ", person_id=" + person_id +
                ", serialNo_sendmsg_server='" + serialNo_sendmsg_server + '\'' +
                ", templateId='" + templateId + '\'' +
                ", signName='" + signName + '\'' +
                ", smsContent='" + smsContent + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", send_startdate='" + send_startdate + '\'' +
                ", send_enddate=" + send_enddate +
                ", operator=" + operator +
                '}';
    }

    public int getSerialNo_sendmsg_thisSystem() {
        return serialNo_sendmsg_thisSystem;
    }

    public void setSerialNo_sendmsg_thisSystem(int serialNo_sendmsg_thisSystem) {
        this.serialNo_sendmsg_thisSystem = serialNo_sendmsg_thisSystem;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSerialNo_askforleave_thisSystem() {
        return serialNo_askforleave_thisSystem;
    }

    public void setSerialNo_askforleave_thisSystem(int serialNo_askforleave_thisSystem) {
        this.serialNo_askforleave_thisSystem = serialNo_askforleave_thisSystem;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getSerialNo_sendmsg_server() {
        return serialNo_sendmsg_server;
    }

    public void setSerialNo_sendmsg_server(String serialNo_sendmsg_server) {
        this.serialNo_sendmsg_server = serialNo_sendmsg_server;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSend_startdate() {
        return send_startdate;
    }

    public void setSend_startdate(String send_startdate) {
        this.send_startdate = send_startdate;
    }

    public Date getSend_enddate() {
        return send_enddate;
    }

    public void setSend_enddate(Date send_enddate) {
        this.send_enddate = send_enddate;
    }

    public Date getOperator() {
        return operator;
    }

    public void setOperator(Date operator) {
        this.operator = operator;
    }
}
