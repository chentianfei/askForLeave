package com.ctf.dao;

import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;

public class SMSLogDao extends BaseDao{

    //插入短信记录
    public int insertLog(SendSmsResponse sendSmsResponse){
        SendStatus[] sendStatusSet = sendSmsResponse.getSendStatusSet();
        for(SendStatus sendStatus : sendStatusSet){

        }
        return 1;
    }

}
