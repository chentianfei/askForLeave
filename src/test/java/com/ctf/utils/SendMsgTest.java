package com.ctf.utils;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.ctf.service.impl.AskForLeaveServiceImpl;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SendMsgTest {

    @Test
    public void sendMsgByPhoneNum() {

        /*{1}您好，您单位的{2}请{3}已通过，请假时间为{4}至{5}，共计{6}天，请知晓！*/
 /*
        aliyun:${name}，您好，您的${kind}假申请已通过审核，请假天数为${day}天，
            请假时间为${time1}到${time2}，请按规定时间归假！*/

        Map<String,String> templateParamList_askforleave_self = new LinkedHashMap<>();
        templateParamList_askforleave_self.put("name","陈天飞");
        templateParamList_askforleave_self.put("kind","事假");
        templateParamList_askforleave_self.put("day","2");
        templateParamList_askforleave_self.put("time1","2021年12月1日");
        templateParamList_askforleave_self.put("time2","2021年12月10日");
        String templateParamList_askforleave_self_json = new Gson().toJson(templateParamList_askforleave_self);

        Map<String,String> templateParamList_askforleave_leader = new LinkedHashMap<>();
        templateParamList_askforleave_leader.put("name","陈天飞");
        templateParamList_askforleave_leader.put("kind","事假");
        templateParamList_askforleave_leader.put("day","2");
        templateParamList_askforleave_leader.put("time1","2021年12月1日");
        templateParamList_askforleave_leader.put("time2","2021年12月10日");
        String templateParamList_askforleave_leader_json = new Gson().toJson(templateParamList_askforleave_leader);

        Map<String,String> templateParamList_resumework_self = new LinkedHashMap<>();
        templateParamList_resumework_self.put("name","陈天飞");
        templateParamList_resumework_self.put("kind","事假");
        templateParamList_resumework_self.put("day","2");
        templateParamList_resumework_self.put("time1","2021年12月1日");
        templateParamList_resumework_self.put("time2","2021年12月10日");
        String templateParamList_resumework_self_json = new Gson().toJson(templateParamList_resumework_self);

        Map<String,String> templateParamList_resumework_leader = new LinkedHashMap<>();
        templateParamList_resumework_leader.put("name","陈天飞");
        templateParamList_resumework_leader.put("kind","事假");
        templateParamList_resumework_leader.put("day","2");
        templateParamList_resumework_leader.put("time1","2021年12月1日");
        templateParamList_resumework_leader.put("time2","2021年12月10日");
        String templateParamList_resumework_leader_json = new Gson().toJson(templateParamList_resumework_leader);

        Map<String,String> templateParamList_alert = new LinkedHashMap<>();
        templateParamList_alert.put("name","陈天飞");
        templateParamList_alert.put("kind","事假");
        templateParamList_alert.put("day","2");
        templateParamList_alert.put("time1","2021年12月1日");
        templateParamList_alert.put("time2","2021年12月10日");
        String templateParamList_alert_json = new Gson().toJson(templateParamList_alert);

        Map<String,String> templateParamList_alertforreturn = new LinkedHashMap<>();
        templateParamList_alertforreturn.put("name","陈天飞");
        templateParamList_alertforreturn.put("kind","事假");
        templateParamList_alertforreturn.put("day","2");
        templateParamList_alertforreturn.put("time1","2021年12月1日");
        templateParamList_alertforreturn.put("time2","2021年12月10日");
        String templateParamList_alertforreturn_json = new Gson().toJson(templateParamList_alert);

        Map<String,String> templateParamList_alertforreturn_history = new LinkedHashMap<>();
        templateParamList_alertforreturn_history.put("name","陈天飞");
        templateParamList_alertforreturn_history.put("kind","事假");
        templateParamList_alertforreturn_history.put("day","2");
        templateParamList_alertforreturn_history.put("time1","2021年12月1日");
        templateParamList_alertforreturn_history.put("time2","2021年12月10日");
        String templateParamList_alertforreturn_history_json = new Gson().toJson(templateParamList_alertforreturn_history);

        try {
            SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(SendMsg.SIGNNAME_RBXRSJ,
                    SendMsg.TEMPLATEID_TOSELFWHENASKFORLEAVE,
                    "18089922014",
                    templateParamList_askforleave_leader_json);
            SendSmsResponseBody body = sendSmsResponse.getBody();

            System.out.println("BizId:"+body.getBizId());
            System.out.println("Code:"+body.getCode());
            System.out.println("Message:"+body.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void pullSmsSendStatus() {
        System.out.println(SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOSELF));
    }



    @Test
    public void addSmsTemplate() {
    }
}