package com.ctf.dao;

import com.ctf.bean.SMSSendLog;
import com.ctf.utils.DateUtils;
import com.ctf.utils.SendMsg_Tecent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SMSLogDao_Tecent extends BaseDao{

    public int insertALog(String insertsql,List<Object> params){
        return update(insertsql,params.toArray());
    }

    //插入短信记录
    public int insertASendLog(SMSSendLog smsSendLog){
        String sql = "insert into sms_sendlog(SerialNo_sendmsg_thisSystem,PhoneNumber,SerialNo_askforleave_thisSystem," +
                "person_id,SerialNo_sendmsg_server,SmsSdkAppId,TemplateId,SignName,SmsContent,ExtendCode,SessionContext," +
                "SendStatus,ErrorMsg,Fee,IsoCode,send_startdate,send_enddate,operator) values(null,?,?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,)";

        List<Object> params = new ArrayList<>();
        params.add(smsSendLog.getPhoneNumber());
        params.add(smsSendLog.getSerialNo_askforleave_thisSystem());
        params.add(smsSendLog.getPerson_id());
        params.add(smsSendLog.getSerialNo_sendmsg_thisSystem());
        /*params.add(smsSendLog.getSmsSdkAppId());*/
        params.add(smsSendLog.getTemplateId());
        params.add(smsSendLog.getSignName());
        params.add(smsSendLog.getSmsContent());
       /* params.add(smsSendLog.getExtendCode());
        params.add(smsSendLog.getSessionContext());
        params.add(smsSendLog.getSendStatus());
        params.add(smsSendLog.getErrorMsg());
        params.add(smsSendLog.getFee());
        params.add(smsSendLog.getIsoCode());*/
        params.add(smsSendLog.getSend_startdate());
        params.add(smsSendLog.getSend_enddate());
        params.add(smsSendLog.getOperator());

        return update(sql,params.toArray());
    }

    //根据请假流水号  查询  向 今天之前  所有到期未销假人员  发送提醒短信的次数
    public int queryCountOfAlertsms_history(int SerialNo_askforleave_thisSystem){
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        //当前日期的前一天
        String yesterday = simpleDateFormat.format(DateUtils.addAndSubtractDays(new Date(), -2));
        //当前日期的后一天
        String tomorrow = simpleDateFormat.format(DateUtils.addAndSubtractDays(new Date(), 2));

        String sql = "select * from sms_sendlog where SerialNo_askforleave_thisSystem=? " +
                " and SendStatus='Ok'" +
                " and (send_enddate>'"+yesterday+" 23:59:59' and send_enddate<'"+tomorrow+" 00:00:00')" +
                " and TemplateId="+ SendMsg_Tecent.TEMPLATEID_ALERTTORETURN_HISTORY;

        return queryForList(SMSSendLog.class,sql,SerialNo_askforleave_thisSystem).size();
    }
    //根据请假流水号  查询  向 今天假期到假未销假人员  发送提醒短信的次数
    public int queryCountOfAlertsms_today(int SerialNo_askforleave_thisSystem){
        String sql = "select * from sms_sendlog where SerialNo_askforleave_thisSystem=? and TemplateId="+ SendMsg_Tecent.TEMPLATEID_ALERTTORETURN;
        return queryForList(SMSSendLog.class,sql,SerialNo_askforleave_thisSystem).size();
    }

}