package com.ctf.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SendMsgTest {

    @Test
    public void sendMsgByPhoneNum() {
        String[] templateParamSet = {"夏雨婷","测试下属","事假","2021年12月1日","2021年12月10日","5"};
        /*{1}您好，您单位的{2}请{3}已通过，请假时间为{4}至{5}，共计{6}天，请知晓！*/

    }

    @Test
    public void pullSmsSendStatus() {
        System.out.println(SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOSELF));
    }



    @Test
    public void addSmsTemplate() {
    }
}