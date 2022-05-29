package com.ctf.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.QuerySendStatisticsRequest;
import com.aliyun.dysmsapi20170525.models.QuerySendStatisticsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.ctf.dao.SMSLogDao;
import com.ctf.dao.SystemDataDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SendMsg {

    //是否发送给领导
    public static final String DOESSENDMSGTOLEADER = "doesSendLeaderCode";
    //是否发送给本人
    public static final String DOESSENDMSGTOSELF = "doesSendSelfCode";

    //短信签名
    public static final String SIGNNAME_XTMXWZZB = "谢通门县委组织部";

    //请假审批同意后请假者本人收到的短信提醒模板ID
    public static final String TEMPLATEID_TOSELFWHENASKFORLEAVE = "SMS_125023145";
    //请假审批同意后请假者领导收到的短信提醒模板ID
    public static final String TEMPLATEID_TOLEADERWHENASKFORLEAVE = "SMS_211245926";
    //到假前本人收到的提示短信模板ID
    public static final String TEMPLATEID_TOSELFFORALERT = "SMS_133260961";
    //到期未销假（之前）提醒短信（谢通门县）模板ID
    public static final String TEMPLATEID_ALERTTORETURN_HISTORY = "SMS_235481059";
    //当日到假未到岗提醒短信（谢通门县）ID
    public static final String TEMPLATEID_ALERTTORETURN = "SMS_235481060";
    //销假成功后本人收到的短信提醒模板ID
    public static final String TEMPLATEID_TOSELFWHENRESUMEWORK = "SMS_156466190";
    //销假成功后绑定领导收到的短信提醒模板ID
    public static final String TEMPLATEID_TOLEADERWHENRESUMEWORK = "SMS_134323450";


    //根据发送对象标志查询是否发送的代码
    public static int querySendMsgObjCode(String objName){
        //获取当前发送短信的对象代码
        Map<String, Integer> stringIntegerMap = new SystemDataDao().querySendTargetCode();
        int sendMsgObjCode = -1;
        for(Map.Entry<String, Integer> code : stringIntegerMap.entrySet()){
            if(code.getKey().equals(objName)){
                sendMsgObjCode = code.getValue();
            }
        }
        return sendMsgObjCode;
    }

    //根据电话号码发送短信功能
    public static SendSmsResponse sendMsgByPhoneNum(String signName,
                                                    String templateId,
                                                    String phoneNumbers,
                                                    String templateParam) throws Exception {
        //获取阿里云client
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId("LTAIrznzfGI8Nw8W")
                // 您的AccessKey Secret
                .setAccessKeySecret("URvG5IqMoPIbxGcvq3uArvGm3lwFBp");
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumbers)
                .setSignName(signName)
                .setTemplateCode(templateId)
                .setTemplateParam(templateParam);

        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        return sendSmsResponse;
    }

    //获取发送数据统计信息
    public static QuerySendStatisticsResponse querySendStaticsBeforeToday(String start_date,String end_date,Integer pageNo,Integer pageSize) throws Exception {

        //获取阿里云client
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId("LTAIrznzfGI8Nw8W")
                // 您的AccessKey Secret
                .setAccessKeySecret("URvG5IqMoPIbxGcvq3uArvGm3lwFBp");
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);

        QuerySendStatisticsRequest querySendStatisticsRequest = new QuerySendStatisticsRequest()
                .setIsGlobe(1)
                .setStartDate(start_date)
                .setEndDate(end_date)
                .setPageIndex(pageNo)
                .setPageSize(pageSize);
        RuntimeOptions runtime = new RuntimeOptions();
        // 复制代码运行请自行打印 API 的返回值
        QuerySendStatisticsResponse querySendStatisticsResponse
                = client.querySendStatisticsWithOptions(querySendStatisticsRequest, runtime);
        return querySendStatisticsResponse;
    }

    //拼凑短信内容
    public static String getSMSContent(String templateId,Map<String,String> templateParamList){
        String[] strings = ConvertorALSM.Map2Array(templateParamList);

        if(templateId.equals("SMS_134323450")){
            //销假成功后根据请假流水号给领导发送短信
            /*尊敬的${name}，您好，您单位${user_work_address}的${name2}已销假，请知悉！*/
            return "尊敬的"+strings[0]+"，您好，您单位"+strings[1]+"的"+strings[2]+"已销假，请知悉！";
        }
        else  if(templateId.equals("SMS_156466190")){
            //销假成功后根据请假流水号给本人发送短信
            /*  ${name}，您好，您已于${time}销假成功，请及时到岗！*/
            return  strings[0]+"，您好，您已于"+strings[1]+"销假成功，请及时到岗！";
        }
        else  if(templateId.equals("SMS_133260961")){
            //即将到假时给本人发送提醒短信
            /*${name}，您好，您的请假时间即将到期，请假时间为${time1}到${time2},请注意及时归假*/
            return strings[0]+"，您好，您的请假时间即将到期，请假时间为"+strings[1]+"到"+strings[1]+",请注意及时归假";
        }
        else  if(templateId.equals("SMS_211245926")){
            //请假审批后根据请假流水号给领导发送短信
           /*  尊敬的${name}，您好，您单位${user_work_address}的${name2}请${kind}假申请已通过审核，
           请假天数为${day}天，请假时间为${time1}到${time2}，事由：${reason}，请知悉！*/
            return   "尊敬的"+strings[0]+"，您好，您单位"+strings[1]+"的"+strings[2]+"请"+strings[3]+"假申请已通过审核，" +
                    "请假天数为"+strings[4]+"天，请假时间为"+strings[5]+"到"+strings[6]+"，事由："+strings[7]+"，请知悉！";
        }
        else  if(templateId.equals("SMS_125023145")){
            //请假审批后根据请假流水号给本人发送短信
           /*${name}，您好，您的${kind}假申请已通过审核，请假天数为${day}天，
                请假时间为${time1}到${time2}，请按规定时间归假！*/
            return  strings[0]+"，您好，您的"+strings[1]+"假申请已通过审核，请假天数为"+strings[2]+"天，" +
                    "请假时间为"+strings[3]+"到"+strings[4]+"，请按规定时间归假！";
        }else {
            return "Error!";
        }
    }

    private static String getCodeMsgCHN(String code){
        return "";
    }


}
