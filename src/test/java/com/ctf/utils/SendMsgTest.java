package com.ctf.utils;

import org.junit.Test;

public class SendMsgTest {

    @Test
    public void sendMsgByPhoneNum() {
        String[] templateParamSet = {"夏雨婷","测试下属","事假","2021年12月1日","2021年12月10日","5"};
        /*{1}您好，您单位的{2}请{3}已通过，请假时间为{4}至{5}，共计{6}天，请知晓！*/

    }

    @Test
    public void pullSmsSendStatus() {
        System.out.println(SendMsg_Tecent.querySendMsgObjCode(SendMsg_Tecent.DOESSENDMSGTOSELF));
    }

    @Test
    public void sendStatusStatistics() {
        SendMsg_Tecent.sendStatusStatistics("2021112800","2021120223",
                SendMsg_Tecent.SENDMSGSDKAPPID,
                10L,
                0L);
    }

    @Test
    public void addSmsTemplate() {
    }
}