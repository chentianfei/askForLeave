package com.ctf.bean;

import javax.xml.crypto.Data;
import java.util.Date;

public class SMSSendLog {
    private int serialNo_sendmsg_thisSystem;//该系统发送编号
    private String phoneNumber;//下发手机号码
    private int serialNo_askforleave_thisSystem;//请假流水号
    private int person_id;//下发对象人员编号
    private String serialNo_sendmsg_server;//服务商流水号
    private String smsSdkAppId;//短信应用id
    private String templateId;//短信模板id
    private String signName;//短信签名内容
    private String smsContent;//短信内容
    private String extendCode;//短信码号扩展号
    private String sessionContext;//用户session内容
    private String sendStatus;//发送状态
    private String errorMsg;//错误描述
    private int fee;//计费条数
    private String isoCode;//国家码或地区码
    private String operator;//发送起始时间
    private Date send_startdate;//发送完成时间
    private Date send_enddate;//操作者

    public SMSSendLog() {
    }

    public SMSSendLog(int serialNo_sendmsg_thisSystem, String phoneNumber, int serialNo_askforleave_thisSystem,
                      int person_id, String serialNo_sendmsg_server, String smsSdkAppId, String templateId,
                      String signName, String smsContent, String extendCode, String sessionContext, String sendStatus,
                      String errorMsg, int fee, String isoCode, String operator, Date send_startdate, Date send_enddate) {
        this.serialNo_sendmsg_thisSystem = serialNo_sendmsg_thisSystem;
        this.phoneNumber = phoneNumber;
        this.serialNo_askforleave_thisSystem = serialNo_askforleave_thisSystem;
        this.person_id = person_id;
        this.serialNo_sendmsg_server = serialNo_sendmsg_server;
        this.smsSdkAppId = smsSdkAppId;
        this.templateId = templateId;
        this.signName = signName;
        this.smsContent = smsContent;
        this.extendCode = extendCode;
        this.sessionContext = sessionContext;
        this.sendStatus = sendStatus;
        this.errorMsg = errorMsg;
        this.fee = fee;
        this.isoCode = isoCode;
        this.operator = operator;
        this.send_startdate = send_startdate;
        this.send_enddate = send_enddate;
    }

    @Override
    public String toString() {
        return "SMSSendLog{" +
                "serialNo_sendmsg_thisSystem=" + serialNo_sendmsg_thisSystem +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", serialNo_askforleave_thisSystem=" + serialNo_askforleave_thisSystem +
                ", person_id=" + person_id +
                ", serialNo_sendmsg_server='" + serialNo_sendmsg_server + '\'' +
                ", smsSdkAppId='" + smsSdkAppId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", signName='" + signName + '\'' +
                ", smsContent='" + smsContent + '\'' +
                ", extendCode='" + extendCode + '\'' +
                ", sessionContext='" + sessionContext + '\'' +
                ", sendStatus='" + sendStatus + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", fee=" + fee +
                ", isoCode='" + isoCode + '\'' +
                ", operator='" + operator + '\'' +
                ", send_startdate=" + send_startdate +
                ", send_enddate=" + send_enddate +
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

    public String getSmsSdkAppId() {
        return smsSdkAppId;
    }

    public void setSmsSdkAppId(String smsSdkAppId) {
        this.smsSdkAppId = smsSdkAppId;
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

    public String getExtendCode() {
        return extendCode;
    }

    public void setExtendCode(String extendCode) {
        this.extendCode = extendCode;
    }

    public String getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(String sessionContext) {
        this.sessionContext = sessionContext;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
}
