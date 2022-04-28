package com.ctf.utils;

import com.ctf.dao.SMSLogDao;
import com.ctf.dao.SystemDataDao;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SendMsg_Tecent {

    private static final String SECRETID = "AKIDBAuNaXEenfFZcOs63i8IEtHOFpCI7fvu";
    private static final String SECREEKEY = "kCNHuGL9h03pKXT8WcS2LN8cplQovlKd";
    private static Credential cred;
    private static  HttpProfile httpProfile;
    private static ClientProfile clientProfile;
    private static SmsClient client;

    //是否发送给领导
    public static final String DOESSENDMSGTOLEADER = "doesSendLeaderCode";
    //是否发送给本人
    public static final String DOESSENDMSGTOSELF = "doesSendSelfCode";
    //短信应用id
    public static final String SENDMSGSDKAPPID = "1400582408";
    //短信签名
    public static final String SIGNNAME_ZBXWJYJ = "仲巴县教育局";
    //请假审批同意后请假者本人收到的短信提醒模板ID
    public static final String TEMPLATEID_TOSELFWHENASKFORLEAVE = "1225831";
    //请假审批同意后请假者领导收到的短信提醒模板ID
    public static final String TEMPLATEID_TOLEADERWHENASKFORLEAVE = "1225833";
    //销假成功后本人收到的短信提醒模板ID
    public static final String TEMPLATEID_TOSELFWHENRESUMEWORK = "1225837";
    //销假成功后绑定领导收到的短信提醒模板ID
    public static final String TEMPLATEID_TOLEADERWHENRESUMEWORK = "1225838";
    //到假前本人收到的提示短信模板ID
    public static final String TEMPLATEID_TOSELFFORALERT = "1225834";
    //到期未销假（之前）提醒短信（仲巴县）模板ID
    public static final String TEMPLATEID_ALERTTORETURN_HISTORY = "1292724";
    //当日到假未到岗提醒短信（仲巴县）ID
    public static final String TEMPLATEID_ALERTTORETURN = "1292719";

    /* 国际/港澳台短信 SenderId: 国内短信填空，默认未开通 */
    private static final String SENDERID = "";
    /* 短信号码扩展号: 默认未开通，如需开通请联系 [sms helper] */
    private static final String EXTENDCODE = "";

    static {
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
        cred = new Credential(SECRETID,SECREEKEY);
        // 实例化一个http选项，可选，没有特殊需求可以跳过
        httpProfile = new HttpProfile();
        /* 非必要步骤:
         * 实例化一个客户端配置对象，可以指定超时时间等配置 */
        clientProfile = new ClientProfile();
        /* SDK默认用TC3-HMAC-SHA256进行签名
         * 非必要请不要修改这个字段 */
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);

        /* 实例化要请求产品(以sms为例)的client对象 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量 */
        client = new SmsClient(cred, "ap-guangzhou", clientProfile);

    }

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
    /*
     * @Description ：
     * @Param smsSdkAppId 短信 SdkAppId，在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
     * @Param signName 短信签名内容，使用 UTF-8 编码，必须填写已审核通过的签名，例如：腾讯云，签名信息可前往 国内短信 或 国际/港澳台短信 的签名管理查看。 发送国内短信该参数必填。
     * @Param templateId 模板 ID，必须填写已审核通过的模板 ID。模板 ID 可前往 国内短信 或 国际/港澳台短信 的正文模板管理查看，若向境外手机号发送短信，仅支持使用国际/港澳台短信模板。
     * @Param phoneNumbers  下发手机号码，采用 E.164 标准，格式为+[国家或地区码][手机号]，单次请求最多支持200个手机号且要求全为境内手机号或全为境外手机号。
                            例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号。 注：发送国内短信格式还支持0086、86或无任何国家或地区码的11位手机号码，前缀默认为+86。
     * @Param templateParam 模板参数，若无模板参数，则设置为空。 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致。
     * @Return ：SendSmsResponse 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应
     * @Author: CTF
     * @Date ：2021/12/3 12:17
     */
    public static SendSmsResponse sendMsgByPhoneNum(String smsSdkAppId,
                                        String signName,
                                        String templateId,
                                        String[] phoneNumbers,
                                        String[] templateParam){
        SendSmsResponse res = null;
        try {
            //获取短信发送请求对象
            SendSmsRequest req = new SendSmsRequest();
            /*设置短信应用ID*/
            req.setSmsSdkAppId(smsSdkAppId);
            /*设置短信签名内容*/
            req.setSignName(signName);
            // 设置下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
            req.setPhoneNumberSet(phoneNumbers);
            //指定使用的短信模板
            req.setTemplateId(templateId);
            //传入模板中的参数
            req.setTemplateParamSet(templateParam);

            /* 通过 client 对象调用 SendSms 方法发起请求。
            注意请求方法名与请求对象是对应的返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            res = client.SendSms(req);

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        return res;
    }

    //拼凑短信内容
    public static String getSMSContent(String templateId,String[] templateParam){
        if(templateId.equals("1292724")){
            //到期未销假（之前）提醒短信（仲巴县）
            return templateParam[0]+"，您好，您在"+templateParam[1]+"申请的"+templateParam[2]+"已超出规定销假时间"+templateParam[3]+"天，请及时到岗销假！";
        }else  if(templateId.equals("1292719")){
            //当日到假未到岗提醒短信（仲巴县）
            return templateParam[0]+"，您好，您在"+templateParam[1]+"申请的"+templateParam[2]+"已到规定销假时间，请及时到岗销假！";
        }else  if(templateId.equals("1225838")){
            //销假成功后绑定领导收到的短信提醒模板
            return "尊敬的"+templateParam[0]+"，您好，您单位"+templateParam[1]+"的"+templateParam[2]+"已于"+templateParam[3]+"销假，实际请假天数为"+templateParam[4]+"天，请知悉！";
        }else  if(templateId.equals("1225837")){
            //销假成功后本人收到的短信提醒模板
            return templateParam[0]+"，您好，您于"+templateParam[1]+"销假成功，实际请假天数为"+templateParam[2]+"天，请及时到岗！";
        }else  if(templateId.equals("1225834")){
            //到假前本人收到的提示短信模板
            return "温馨提示："+templateParam[0]+"，您好，您的"+templateParam[1]+"假还有"+templateParam[2]+"天到期，请按时到岗！";
        }else  if(templateId.equals("1225833")){
            //请假审批同意后请假者领导收到的短信提醒模板
            return "尊敬的"+templateParam[0]+"，您好，您单位"+templateParam[1]+"的"+templateParam[2]+templateParam[3]
                    +"申请已通过审核，请假天数为"+templateParam[4]+"天，请假时间为"+templateParam[5]+"到"+templateParam[6]+"，请知悉！";
        }else  if(templateId.equals("1225831")){
            //请假审批同意后请假者本人收到的短信提醒模板
            return templateParam[0]+"，您好，您的"+templateParam[1]+"申请已通过审核，请假天数为"
                    +templateParam[2]+"天，请假时间为"+templateParam[3]+"到"+templateParam[4]+"，请按规定时间归假！";
        }else {
            return "Error!";
        }
    }

    /*
     * @Description ： 套餐包信息统计
     * @Param beginTime 起始时间，格式为yyyymmddhh，精确到小时，例如2021050113，表示2021年5月1号13时。 注：拉取套餐包的创建时间不小于起始时间。
     * @Param endTime 结束时间，格式为yyyymmddhh，精确到小时，例如2021050118，表示2021年5月1号18时。 注：EndTime 必须大于 BeginTime，拉取套餐包的创建时间不大于结束时间。
     * @Param smsSdkAppId 短信 SdkAppId 在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
     * @Param limit 最大上限(需要拉取的套餐包个数)。
     * @Param offset 偏移量。
     * @Return ：SmsPackagesStatisticsResponse 返回的是一个SmsPackagesStatisticsResponse的实例，与请求对象对应
     * @Author: CTF
     * @Date ：2021/12/3 12:10
     */
    public static SmsPackagesStatisticsResponse smsPackagesStatistics(String beginTime,String endTime,
                                             String smsSdkAppId,
                                             Long limit,Long offset){
        SmsPackagesStatisticsResponse resp = null;
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SmsPackagesStatisticsRequest req = new SmsPackagesStatisticsRequest();
            req.setSmsSdkAppId(smsSdkAppId);
            req.setLimit(limit);
            req.setOffset(offset);
            req.setBeginTime(beginTime);
            req.setEndTime(endTime);

            // 返回的resp是一个SmsPackagesStatisticsResponse的实例，与请求对象对应
            resp = client.SmsPackagesStatistics(req);

            // 输出json格式的字符串回包
            //System.out.println(SmsPackagesStatisticsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    /*
     * @Description ：发送短信数据统计
     * @Param beginTime 起始时间，格式为yyyymmddhh，精确到小时，例如2021050113，表示2021年5月1号13时。
     * @Param endTime 结束时间，格式为yyyymmddhh，精确到小时，例如2021050118，表示2021年5月1号18时。 注：EndTime 必须大于 BeginTime。
     * @Param smsSdkAppId 短信 SdkAppId 在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
     * @Param limit 最大上限。 注：目前固定设置为0。
     * @Param offset 偏移量。 注：目前固定设置为0。
     * @Return ：SendStatusStatisticsResponse 返回的是一个SendStatusStatisticsResponse的实例，与请求对象对应
     * @Author: CTF
     * @Date ：2021/12/3 12:02
     */
    public static SendStatusStatisticsResponse sendStatusStatistics(String beginTime,String endTime,String smsSdkAppId,
                                            Long limit,Long offset){
        SendStatusStatisticsResponse resp = null;
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendStatusStatisticsRequest req = new SendStatusStatisticsRequest();
            req.setBeginTime(beginTime);
            req.setEndTime(endTime);
            req.setSmsSdkAppId(smsSdkAppId);
            req.setLimit(limit);
            req.setOffset(offset);
            // 返回的resp是一个SendStatusStatisticsResponse的实例，与请求对象对应
            resp = client.SendStatusStatistics(req);
            // 输出json格式的字符串回包
            //System.out.println(SendStatusStatisticsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    /*
     * @Description ： 回执数据统计
     * @Param beginTime 起始时间，格式为yyyymmddhh，精确到小时，例如2021050113，表示2021年5月1号13时。
     * @Param endTime 结束时间，格式为yyyymmddhh，精确到小时，例如2021050118，表示2021年5月1号18时。 注：EndTime 必须大于 BeginTime，且相差不超过32天。
     * @Param smsSdkAppId 短信 SdkAppId 在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
     * @Param limit 最大上限。 注：目前固定设置为0。
     * @Param offset 偏移量。 注：目前固定设置为0。
     * @Return ：CallbackStatusStatisticsResponse 返回的是一个CallbackStatusStatisticsResponse的实例，与请求对象对应
     * @Author: CTF
     * @Date ：2021/12/3 12:07
     */
    public static CallbackStatusStatisticsResponse callbackStatusStatistics(String beginTime,String endTime,
                                                String smsSdkAppId,Long limit,
                                                Long offset){
        CallbackStatusStatisticsResponse resp = null;
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            CallbackStatusStatisticsRequest req = new CallbackStatusStatisticsRequest();
            req.setBeginTime(beginTime);
            req.setEndTime(endTime);
            req.setSmsSdkAppId(smsSdkAppId);
            req.setLimit(limit);
            req.setOffset(offset);
            // 返回的resp是一个CallbackStatusStatisticsResponse的实例，与请求对象对应
            resp = client.CallbackStatusStatistics(req);
            // 输出json格式的字符串回包
            //System.out.println(CallbackStatusStatisticsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    /*
     * @Description ：拉取单个号码短信下发状态
     * @Param beginTimeSTR 起始时间，格式为yyyymmddhh，精确到小时，例如2021050113，表示2021年5月1号13时。注：最大可拉取当前时期前7天的数据。将转为以秒为单位的时间戳
     * @Param endTimeSTR 结束时间，格式为yyyymmddhh，精确到小时，例如2021050118，表示2021年5月1号18时。注：最大可拉取当前时期前7天的数据。将转为以秒为单位的时间戳
     * @Param limit 拉取最大条数，最多 100。
     * @Param offset 偏移量。 注：目前固定设置为0。
     * @Param phoneNumber 下发目的手机号码，依据 E.164 标准为：+[国家（或地区）码][手机号] ，示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号。
     * @Param smsSdkAppId 短信 SdkAppId 在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
     * @Return ：PullSmsSendStatusByPhoneNumberResponse 返回的是一个PullSmsSendStatusByPhoneNumberResponse的实例，与请求对象对应
     * @Author: CTF
     * @Date ：2021/12/3 12:53
     */
    public static PullSmsSendStatusByPhoneNumberResponse pullSmsSendStatusByPhoneNumber(long beginTime,long endTime,
                                                      Long limit,Long offset,
                                                      String phoneNumber,String smsSdkAppId){
        PullSmsSendStatusByPhoneNumberResponse resp = null;
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            PullSmsSendStatusByPhoneNumberRequest req = new PullSmsSendStatusByPhoneNumberRequest();
            req.setOffset(offset);
            req.setLimit(limit);
            req.setPhoneNumber(phoneNumber);
            req.setSmsSdkAppId(smsSdkAppId);
            req.setBeginTime(beginTime);
            req.setEndTime(endTime);

            // 返回的resp是一个PullSmsSendStatusByPhoneNumberResponse的实例，与请求对象对应
            resp = client.PullSmsSendStatusByPhoneNumber(req);
            // 输出json格式的字符串回包
            //.println(PullSmsSendStatusByPhoneNumberResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e);
        }
        return resp;
    }

    public static PullSmsSendStatusByPhoneNumberResponse pullSmsSendStatusByPhoneNumber(String beginTimeSTR,String endTimeSTR,
                                                                                        Long limit,Long offset,
                                                                                        String phoneNumber,String smsSdkAppId){
        PullSmsSendStatusByPhoneNumberResponse resp = null;
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            PullSmsSendStatusByPhoneNumberRequest req = new PullSmsSendStatusByPhoneNumberRequest();
            req.setOffset(offset);
            req.setLimit(limit);
            req.setPhoneNumber(phoneNumber);
            req.setSmsSdkAppId(smsSdkAppId);

            Long sevendaySeconds = Long.valueOf(60*60*24*7);
            //处理时间字符串，转换为时间戳
            if(beginTimeSTR == "" || beginTimeSTR == null){
                //若未设置起始时间，则默认设置为以当前时间前7天为起始日
                req.setEndTime((System.currentTimeMillis()-sevendaySeconds)/1000);
            }else {
                long beginTime = 0L;
                long beginTimeDate = DateUtils.getSecondByDateStr_yyyyMMddHH(beginTimeSTR);

                if(System.currentTimeMillis()/1000 - beginTimeDate >= sevendaySeconds  ){
                    beginTime = (System.currentTimeMillis() - sevendaySeconds)/1000;
                }else {
                    beginTime = beginTimeDate;
                }
                req.setBeginTime(beginTime);
            }

            if(endTimeSTR == "" || endTimeSTR == null){
                req.setEndTime(System.currentTimeMillis()/1000);
            }else {
                long endTimeDate = DateUtils.getSecondByDateStr_yyyyMMddHH(endTimeSTR);
                if(endTimeDate >= System.currentTimeMillis()/1000){
                    req.setEndTime(System.currentTimeMillis()/1000);
                }else {
                    req.setEndTime(endTimeDate);
                }
            }

            // 返回的resp是一个PullSmsSendStatusByPhoneNumberResponse的实例，与请求对象对应
            resp = client.PullSmsSendStatusByPhoneNumber(req);
            // 输出json格式的字符串回包
            //.println(PullSmsSendStatusByPhoneNumberResponse.toJsonString(resp));
        } catch (TencentCloudSDKException | ParseException e) {
            System.out.println(e);
        }
        return resp;
    }

    /*
     * @Description ：拉取短信下发状态
     * @Param limit 拉取最大条数，最多100条。
     * @Param smsSdkAppId 短信 SdkAppId 在 短信控制台 添加应用后生成的实际 SdkAppId，例如1400006666。
     * @Return ：PullSmsSendStatusResponse 返回的是一个PullSmsSendStatusResponse的实例，与请求对象对应
     * @Author: CTF
     * @Date ：2021/12/3 15:30
     */
    public static PullSmsSendStatusResponse  pullSmsSendStatus(Long limit,String smsSdkAppId){
        PullSmsSendStatusResponse resp = null;
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            PullSmsSendStatusRequest req = new PullSmsSendStatusRequest();
            req.setLimit(limit);
            req.setSmsSdkAppId(smsSdkAppId);
            // 返回的resp是一个PullSmsSendStatusResponse的实例，与请求对象对应
            resp = client.PullSmsSendStatus(req);
            // 输出json格式的字符串回包
            //System.out.println(PullSmsSendStatusResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    //拉取单个号码短信回复状态
    /*
     * @Description ：
     * @Param beginTimeSTR 起始时间，格式为yyyymmddhh，精确到小时，例如2021050113，表示2021年5月1号13时。注：最大可拉取当前时期前7天的数据，将转为以秒为单位的时间戳
     * @Param endTimeSTR 结束时间，格式为yyyymmddhh，精确到小时，例如2021050113，表示2021年5月1号13时。注：最大可拉取当前时期前7天的数据，将转为以秒为单位的时间戳
     * @Param limit 拉取最大条数，最多 100。
     * @Param offset 偏移量。 注：目前固定设置为0。
     * @Param phoneNumber 下发目的手机号码，依据 E.164 标准为：+[国家（或地区）码][手机号] ，示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号。
     * @Param smsSdkAppId 短信 SdkAppId 在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
     * @Return ：PullSmsReplyStatusByPhoneNumberResponse 返回的是一个PullSmsReplyStatusByPhoneNumberResponse的实例，与请求对象对应
     * @Author: CTF
     * @Date ：2021/12/3 15:32
     */
    public static PullSmsReplyStatusByPhoneNumberResponse pullSmsReplyStatusByPhoneNumber(String beginTimeSTR,String endTimeSTR,
                                                                                        Long limit,Long offset,
                                                                                        String phoneNumber,String smsSdkAppId){
        PullSmsReplyStatusByPhoneNumberResponse resp = null;
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            PullSmsReplyStatusByPhoneNumberRequest req = new PullSmsReplyStatusByPhoneNumberRequest();

            req.setOffset(offset);
            req.setLimit(limit);
            req.setPhoneNumber(phoneNumber);
            req.setSmsSdkAppId(smsSdkAppId);

            Long sevendaySeconds = Long.valueOf(60*60*24*7);
            //处理时间字符串，转换为时间戳
            if(beginTimeSTR == "" || beginTimeSTR == null){
                //若未设置起始时间，则默认设置为以当前时间前7天为起始日
                req.setEndTime((System.currentTimeMillis()-sevendaySeconds)/1000);
            }else {
                long beginTime = 0L;
                Date beginTimeDate = new SimpleDateFormat("yyyyMMddHH").parse(beginTimeSTR);
                if((System.currentTimeMillis() - beginTimeDate.getTime())/1000 >= sevendaySeconds  ){
                    beginTime = (System.currentTimeMillis() - sevendaySeconds)/1000;
                }else {
                    beginTime = beginTimeDate.getTime()/1000;
                }
                req.setBeginTime(beginTime);
            }

            if(endTimeSTR == "" || endTimeSTR == null){
                req.setEndTime(System.currentTimeMillis()/1000);
            }else {
                Date endTimeDate = new SimpleDateFormat("yyyyMMddHH").parse(endTimeSTR);
                if(endTimeDate.getTime() >= System.currentTimeMillis()){
                    req.setEndTime(System.currentTimeMillis()/1000);
                }else {
                    req.setEndTime(endTimeDate.getTime()/1000);
                }
            }

            // 返回的resp是一个PullSmsReplyStatusByPhoneNumberResponse的实例，与请求对象对应
            resp = client.PullSmsReplyStatusByPhoneNumber(req);
            // 输出json格式的字符串回包
            //System.out.println(PullSmsReplyStatusByPhoneNumberResponse.toJsonString(resp));
        } catch (TencentCloudSDKException | ParseException e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    /*
     * @Description ：拉取短信回复状态
     * @Param limit 拉取最大条数，最多100条。
     * @Param smsSdkAppId 短信 SdkAppId 在 短信控制台 添加应用后生成的实际 SdkAppId，例如1400006666。
     * @Return ：PullSmsReplyStatusResponse 返回的是一个PullSmsReplyStatusResponse的实例，与请求对象对应
     * @Author: CTF
     * @Date ：2021/12/3 15:30
     */
    public static PullSmsReplyStatusResponse  pullSmsReplyStatus(Long limit,String smsSdkAppId){
        PullSmsReplyStatusResponse resp = null;
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            PullSmsReplyStatusRequest req = new PullSmsReplyStatusRequest();
            req.setLimit(0L);
            req.setSmsSdkAppId("0");
            // 返回的resp是一个PullSmsReplyStatusResponse的实例，与请求对象对应
            resp = client.PullSmsReplyStatus(req);
            // 输出json格式的字符串回包
            //System.out.println(PullSmsReplyStatusResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    //申请模板
    public static void addSmsTemplate(){
        try {
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 您可以直接查询 SDK 源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用 IDE 进行开发，可以方便地跳转查阅各个接口和数据结构的文档说明 */
            AddSmsTemplateRequest req = new AddSmsTemplateRequest();

            String templatename = "腾讯云";
            req.setTemplateName(templatename);
            /* 模板内容 */
            String templatecontent = "{1}为您的登录验证码，请于{2}分钟内填写，如非本人操作，请忽略本短信。";
            req.setTemplateContent(templatecontent);
            /* 短信类型：0表示普通短信, 1表示营销短信 */
            long smstype = 0;
            req.setSmsType(smstype);
            /* 是否国际/港澳台短信：0：表示国内短信，1：表示国际/港澳台短信。 */
            long international = 0;
            req.setInternational(international);
            /* 模板备注：例如申请原因，使用场景等 */
            String remark = "xxx";
            req.setRemark(remark);
            /* 通过 client 对象调用 AddSmsTemplate 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 AddSmsTemplateResponse 类的实例，与请求对象对应 */
            AddSmsTemplateResponse res = client.AddSmsTemplate(req);
            // 输出 JSON 格式的字符串回包
            //System.out.println(AddSmsTemplateResponse.toJsonString(res));
            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }

    private static String getCodeMsgCHN(String code){
        return "";
    }

    /*
    * 根据日期字符串设置起始时间，unix时间戳，单位为秒，起始时间设置规则为：
    * 用户选择的起始时间不得大于当前小时的前7*24小时
    * 若起始时间大于当前小时的前7*24小时，则默认起始时间为当前小时的前7*24小时
    * */
    public static long getBeginTimeByDateStr(String dateSTR) throws ParseException {
        Long sevendaySeconds = Long.valueOf(60*60*24*7);
        long dateToTime;
        Date beginTimeDate = new SimpleDateFormat("yyyyMMddHH").parse(dateSTR);
        if((System.currentTimeMillis() - beginTimeDate.getTime())/1000 >= sevendaySeconds  ){
            //如果查询时间在当前时间7*24小时之前，则将起始时间设置为当前时间的7*24小时
            dateToTime = (System.currentTimeMillis() - sevendaySeconds)/1000;
        }else {
            dateToTime = beginTimeDate.getTime()/1000;
        }
        return dateToTime;
    }

    /*
     * 根据日期字符串设置结束时间，unix时间戳，单位为秒，结束时间设置规则为：
     * 用户选择的结束时间在当前小时之后，若结束时间在当前小时之后，则默认结束时间为当前小时
     * 用户选择的结束时间在当前小时之后，若结束时间在当前小时之后，则默认结束时间为当前小时
     * */
    public static long getEndTimeByDateStr(String dateSTR) throws ParseException {
        long endTimeDate = DateUtils.getSecondByDateStr_yyyyMMddHH(dateSTR);
        if(dateSTR == "" || dateSTR == null){
            //若结束时间字符串为null或空字符串，则返回当前时间的描述
            return System.currentTimeMillis()/1000;
        }else {
            if(endTimeDate >= System.currentTimeMillis()/1000){
                return System.currentTimeMillis()/1000;
            }else {
                return endTimeDate;
            }
        }
    }


}
