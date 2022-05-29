package com.ctf.service.impl;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.ctf.bean.*;
import com.ctf.dao.*;
import com.ctf.service.AskForLeaveService;
import com.ctf.utils.DateUtils;
import com.ctf.utils.SendMsg;
import com.ctf.utils.WebUtils;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class AskForLeaveServiceImpl {

    private static final String DATEFORMAT_YMD = "yyyy年MM月dd日";
    private static AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    private static PersonDao personDao = new PersonDao();
    private static PersonServiceImpl personService = new PersonServiceImpl();
    private static SystemDataDao systemDataDao = new SystemDataDao();

    private static UserDao userDao = new UserDao();
    private SMSLogDao smsLogDao = new SMSLogDao();

    //销假成功后根据请假流水号给领导发送短信
    private List<SendSmsResponse> sendMsgToLeaderBySerialnumberWhenResumeWork(int serialnumber) throws Exception {
        /*
        尊敬的${name}，您好，您单位${user_work_address}的${name2}已销假，请知悉！
        */
        List<SendSmsResponse> sendSmsResponseList = new ArrayList<>();
        //此短信模板code
        String TemplateId = SendMsg.TEMPLATEID_TOLEADERWHENRESUMEWORK;
        //此方法短信签名
        String SignName = SendMsg.SIGNNAME_XTMXWZZB;

        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryResumeWorkInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取领导列表，并解析领导手机号
        List<Leader> leaderList = personDao.queryRelatedLeader(person_id);
        if(leaderList != null){
            if(leaderList.size()!=0){
                int sendStatus = 0;
                for(Leader leader : leaderList){
                    //封装短信模板参数数据
                    Map<String,String> templateParamList = new LinkedHashMap<>();
                    templateParamList.put("name",leader.getOffice_leader_name());
                    templateParamList.put("user_work_address",person.getOffice());
                    templateParamList.put("name2",person.getName());
                    //转换为json
                    String templateParamList_json = new Gson().toJson(templateParamList);

                    //发送前保存时间，写入日志
                    Date start_time = DateUtils.timestampToDate_YMDHMS(new Date());
                    //发送短信，并获取响应对象
                    SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(
                            SignName,
                            TemplateId,
                            leader.getOffice_leader_phone(),
                            templateParamList_json
                    );
                    //通过发送后的相应对象，获取此次发送的响应状态集
                    SendSmsResponseBody body = sendSmsResponse.getBody();
                    //解析返回内容
                    String bizId = body.getBizId();
                    String code = body.getCode();
                    String message = body.getMessage();
                    String requestId = body.getRequestId();
                    /*************************************/
                    System.out.println("bizId:"+bizId);
                    System.out.println("code:"+code);
                    System.out.println("message:"+message);
                    /*************************************/
                    //发送后保存时间，写入日志
                    Date end_time = DateUtils.timestampToDate_YMDHMS(new Date());

                    //写入日志
                    String sql = "insert into sms_sendlog(PhoneNumber," +
                            "SerialNo_askforleave_thisSystem,person_id,SerialNo_sendmsg_server," +
                            "TemplateId,SignName,SmsContent,Code,Message,send_startdate," +
                            "send_enddate,operator) values(?,?,?,?,?,?,?,?," +
                            "?,?,?,?)";

                    List<Object> params = new ArrayList<>();
                    params.add(leader.getOffice_leader_phone());//PhoneNumber
                    params.add(serialnumber);//SerialNo_askforleave_thisSystem
                    params.add(person_id);//person_id
                    params.add(bizId);//bizId
                    params.add(TemplateId);//TemplateId
                    params.add(SignName);//SignName
                    params.add(SendMsg.getSMSContent(
                            TemplateId,templateParamList));//SmsContent
                    params.add(code);//Code
                    params.add(message);//Message
                    params.add(start_time);//send_startdate
                    params.add(end_time);//send_enddate
                    params.add(leaveInfo.getEnd_leave_operator());//operator

                    //写入日志
                    smsLogDao.insertALog(sql,params);
                    sendSmsResponseList.add(sendSmsResponse);
                }
            }
        }

        return sendSmsResponseList;
    }

    //销假成功后根据请假流水号给本人发送短信
    private SendSmsResponse sendMsgToSelfBySerialnumberWhenResumeWork(int serialnumber) throws Exception {
         /*
            ${name}，您好，您已于${time}销假成功，请及时到岗！
         * */
        //此短信模板code
        String TemplateId = SendMsg.TEMPLATEID_TOSELFWHENRESUMEWORK;
        //此方法短信签名
        String SignName = SendMsg.SIGNNAME_XTMXWZZB;

        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryResumeWorkInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取本人手机号
        String phoneNumber = person.getPhone();

        //封装短信模板参数数据
        Map<String,String> templateParamList = new LinkedHashMap<>();
        templateParamList.put("name",person.getName());
        templateParamList.put("time",new SimpleDateFormat("yyyy年MM月dd日").format(leaveInfo.getEnd_date()));
        //转换为json
        String templateParamList_json = new Gson().toJson(templateParamList);

        //发送前保存时间，写入日志
        Date start_time = DateUtils.timestampToDate_YMDHMS(new Date());
        //发送短信，并获取响应对象
        SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(
                SignName,TemplateId,phoneNumber,templateParamList_json);

        //通过发送后的相应对象，获取此次发送的响应状态集
        SendSmsResponseBody body = sendSmsResponse.getBody();
        //解析返回内容
        String bizId = body.getBizId();
        String code = body.getCode();
        String message = body.getMessage();
        String requestId = body.getRequestId();

        //发送后保存时间，写入日志
        Date end_time = DateUtils.timestampToDate_YMDHMS(new Date());
        //写入日志
        String sql = "insert into sms_sendlog(PhoneNumber," +
                "SerialNo_askforleave_thisSystem,person_id,SerialNo_sendmsg_server," +
                "TemplateId,SignName,SmsContent,Code,Message,send_startdate," +
                "send_enddate,operator) values(?,?,?,?,?,?,?,?," +
                "?,?,?,?)";

        List<Object> params = new ArrayList<>();
        params.add(person.getPhone());//PhoneNumber
        params.add(serialnumber);//SerialNo_askforleave_thisSystem
        params.add(person_id);//person_id
        params.add(bizId);//bizId
        params.add(TemplateId);//TemplateId
        params.add(SignName);//SignName
        params.add(SendMsg.getSMSContent(
                TemplateId,templateParamList));//SmsContent
        params.add(code);//Code
        params.add(message);//Message
        params.add(start_time);//send_startdate
        params.add(end_time);//send_enddate
        params.add(leaveInfo.getEnd_leave_operator());//operator

        //写入日志
        smsLogDao.insertALog(sql,params);
        /*************************************/
        System.out.println("bizId:"+bizId);
        System.out.println("code:"+code);
        System.out.println("message:"+message);
        /*************************************/
        return sendSmsResponse;
    }

    //****即将到假时给本人发送提醒短信
    public SendSmsResponse sendMsgToSelfBySerialnumberForAlert(int serialnumber) throws Exception {
        /*
        ${name}，您好，您的请假时间即将到期，请假时间为${time1}到${time2},请注意及时归假
         * */
        //此短信模板code
        String TemplateId = SendMsg.TEMPLATEID_TOSELFFORALERT;
        //此方法短信签名
        String SignName = SendMsg.SIGNNAME_XTMXWZZB;

        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryALeaveInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();
        //起始时间
        String start_date = new SimpleDateFormat("yyyy-MM-dd").format(leaveInfo.getStart_date());
        //预计结束时间
        String end_date_maybe = new SimpleDateFormat("yyyy-MM-dd").format(leaveInfo.getEnd_date_maybe());

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);
        //根据人员编号获取本人手机号
        String phoneNumber = person.getPhone();

        //封装短信模板参数数据
        Map<String,String> templateParamList = new LinkedHashMap<>();
        templateParamList.put("name",person.getName());
        templateParamList.put("time1",start_date);
        templateParamList.put("time2",end_date_maybe);
        //转换为json
        String templateParamList_json = new Gson().toJson(templateParamList);

        //发送前保存时间，写入日志
        Date start_time = DateUtils.timestampToDate_YMDHMS(new Date());
        //发送短信，并获取响应对象
        SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(
                SignName,TemplateId
                ,phoneNumber,templateParamList_json);
        //通过发送后的相应对象，获取此次发送的响应状态集
        SendSmsResponseBody body = sendSmsResponse.getBody();
        //解析返回内容
        String bizId = body.getBizId();
        String code = body.getCode();
        String message = body.getMessage();
        String requestId = body.getRequestId();
        //发送后保存时间，写入日志
        Date end_time = DateUtils.timestampToDate_YMDHMS(new Date());

        //写入日志
        String sql = "insert into sms_sendlog(SerialNo_sendmsg_thisSystem,PhoneNumber," +
                "SerialNo_askforleave_thisSystem,person_id,SerialNo_sendmsg_server," +
                "TemplateId,SignName,SmsContent,Code,Message,send_startdate," +
                "send_enddate,operator) values(null,?,?,?,?,?,?,?,?," +
                "?,?,?,?)";

        List<Object> params = new ArrayList<>();
        params.add(person.getPhone());//PhoneNumber
        params.add(serialnumber);//SerialNo_askforleave_thisSystem
        params.add(person_id);//person_id
        params.add(bizId);//bizId
        params.add(TemplateId);//TemplateId
        params.add(SignName);//SignName
        params.add(SendMsg.getSMSContent(
                TemplateId,templateParamList));//SmsContent
        params.add(code);//Code
        params.add(message);//Message
        params.add(start_time);//send_startdate
        params.add(end_time);//send_enddate
        params.add(leaveInfo.getStart_leave_operator());//operator

        //写入日志
        smsLogDao.insertALog(sql,params);
        /*************************************/
        System.out.println("bizId:"+bizId);
        System.out.println("code:"+code);
        System.out.println("message:"+message);
        /*************************************/
        return sendSmsResponse;
    }

    //请假审批后根据请假流水号给领导发送短信
    private List<SendSmsResponse> sendMsgToLeaderBySerialnumberWhenAskForLeave(int serialnumber) throws Exception {
        /*
        尊敬的${name}，您好，您单位${user_work_address}的${name2}请${kind}假申请已通过审核，
        请假天数为${day}天，请假时间为${time1}到${time2}，事由：${reason}，请知悉！
         * */
        List<SendSmsResponse> sendSmsResponseList = new ArrayList<>();
        //此短信模板code
        String TemplateId = SendMsg.TEMPLATEID_TOLEADERWHENASKFORLEAVE;
        //此方法短信签名
        String SignName = SendMsg.SIGNNAME_XTMXWZZB;

        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryALeaveInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取领导列表，并解析领导手机号
        List<Leader> leaderList = personDao.queryRelatedLeader(person_id);
        if(leaderList != null){
            if(leaderList.size()!=0){
                int sendStatus = 0;
                for(Leader leader : leaderList){

                    //起始时间
                    String start_date = new SimpleDateFormat("yyyy-MM-dd").format(leaveInfo.getStart_date());
                    //预计结束时间
                    String end_date_maybe = new SimpleDateFormat("yyyy-MM-dd").format(leaveInfo.getEnd_date_maybe());
                    //预计请假天数
                    Integer leave_days_projected = leaveInfo.getLeave_days_projected();
                    String leave_days_projected_str = leave_days_projected.toString();

                    //封装短信模板参数数据
                    Map<String,String> templateParamList = new LinkedHashMap<>();
                    templateParamList.put("name",leader.getOffice_leader_name());
                    templateParamList.put("user_work_address",person.getOffice());
                    templateParamList.put("name2",person.getName());
                    templateParamList.put("kind",leaveInfo.getLeave_type());
                    templateParamList.put("day",leave_days_projected_str);
                    templateParamList.put("time1",start_date);
                    templateParamList.put("time2",end_date_maybe);
                    templateParamList.put("reason",leaveInfo.getLeave_reason());
                    //转换为json
                    String templateParamList_json = new Gson().toJson(templateParamList);

                    //发送前保存时间，写入日志
                    Date start_time = DateUtils.timestampToDate_YMDHMS(new Date());
                    //发送短信，并获取响应对象
                    SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(
                            SignName,
                            TemplateId,
                            leader.getOffice_leader_phone(),
                            templateParamList_json);
                    //通过发送后的相应对象，获取此次发送的响应状态集
                    SendSmsResponseBody body = sendSmsResponse.getBody();
                    //解析返回内容
                    String bizId = body.getBizId();
                    String code = body.getCode();
                    String message = body.getMessage();
                    String requestId = body.getRequestId();
                    //发送后保存时间，写入日志
                    Date end_time = DateUtils.timestampToDate_YMDHMS(new Date());

                    //写入日志
                    String sql = "insert into sms_sendlog(SerialNo_sendmsg_thisSystem,PhoneNumber," +
                            "SerialNo_askforleave_thisSystem,person_id,SerialNo_sendmsg_server," +
                            "TemplateId,SignName,SmsContent,Code,Message,send_startdate," +
                            "send_enddate,operator) values(null,?,?,?,?,?,?,?,?," +
                            "?,?,?,?)";

                    List<Object> params = new ArrayList<>();
                    params.add(leader.getOffice_leader_phone());//PhoneNumber
                    params.add(serialnumber);//SerialNo_askforleave_thisSystem
                    params.add(person_id);//person_id
                    if(code.equals("Error!")){
                        params.add("Error!");//bizId
                    }else {
                        params.add(bizId);//bizId
                    }
                    params.add(TemplateId);//TemplateId
                    params.add(SignName);//SignName
                    params.add(SendMsg.getSMSContent(
                            TemplateId,templateParamList));//SmsContent
                    params.add(code);//Code
                    params.add(message);//Message
                    params.add(start_time);//send_startdate
                    params.add(end_time);//send_enddate
                    params.add(leaveInfo.getStart_leave_operator());//operator

                    //写入日志
                    smsLogDao.insertALog(sql,params);
                    sendSmsResponseList.add(sendSmsResponse);
                    /*************************************/
                    System.out.println("bizId:"+bizId);
                    System.out.println("code:"+code);
                    System.out.println("message:"+message);
                    /*************************************/
                }
            }
        }

        return sendSmsResponseList;
    }

    //请假审批后根据请假流水号给本人发送短信
    private SendSmsResponse sendMsgToSelfBySerialnumberWhenAskForLeave(int serialnumber) throws Exception {
       /*
        aliyun:${name}，您好，您的${kind}假申请已通过审核，请假天数为${day}天，
                请假时间为${time1}到${time2}，请按规定时间归假！
         */

        //此短信模板code
        String TemplateId = SendMsg.TEMPLATEID_TOSELFWHENASKFORLEAVE;
        //此方法短信签名
        String SignName = SendMsg.SIGNNAME_XTMXWZZB;

        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryALeaveInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取本人手机号
        String phoneNumber = person.getPhone();

        //起始时间
        String start_date = new SimpleDateFormat("yyyy-MM-dd").format(leaveInfo.getStart_date());
        //预计结束时间
        String end_date_maybe = new SimpleDateFormat("yyyy-MM-dd").format(leaveInfo.getEnd_date_maybe());
        //预计请假天数
        Integer leave_days_projected = leaveInfo.getLeave_days_projected();
        String leave_days_projected_str = leave_days_projected.toString();

        //封装短信模板参数数据
        Map<String,String> templateParamList = new LinkedHashMap<>();
        templateParamList.put("name",person.getName());
        templateParamList.put("kind",leaveInfo.getLeave_type());
        templateParamList.put("day",leave_days_projected_str);
        templateParamList.put("time1",start_date);
        templateParamList.put("time2",end_date_maybe);
        //转换为json
        String templateParamList_json = new Gson().toJson(templateParamList);

        //发送前保存时间，写入日志
        Date start_time = DateUtils.timestampToDate_YMDHMS(new Date());
        //发送短信，并获取响应对象
        SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(
                SignName,TemplateId, phoneNumber, templateParamList_json);

        //通过发送后的相应对象，获取此次发送的响应状态集
        SendSmsResponseBody body = sendSmsResponse.getBody();
        //解析返回内容
        String bizId = body.getBizId();
        String code = body.getCode();
        String message = body.getMessage();
        String requestId = body.getRequestId();
        //发送后保存时间，写入日志
        Date end_time = DateUtils.timestampToDate_YMDHMS(new Date());

        //写入日志
        String sql = "insert into sms_sendlog(SerialNo_sendmsg_thisSystem,PhoneNumber," +
                "SerialNo_askforleave_thisSystem,person_id,SerialNo_sendmsg_server," +
                "TemplateId,SignName,SmsContent,Code,Message,send_startdate," +
                "send_enddate,operator) values(null,?,?,?,?,?,?,?,?," +
                "?,?,?,?)";

        List<Object> params = new ArrayList<>();
        params.add(person.getPhone());//PhoneNumber
        params.add(serialnumber);//SerialNo_askforleave_thisSystem
        params.add(person_id);//person_id
        params.add(bizId);//bizId
        params.add(TemplateId);//TemplateId
        params.add(SignName);//SignName
        params.add(SendMsg.getSMSContent(
                TemplateId,templateParamList));//SmsContent
        params.add(code);//Code
        params.add(message);//Message
        params.add(start_time);//send_startdate
        params.add(end_time);//send_enddate
        params.add(leaveInfo.getStart_leave_operator());//operator

        //写入日志
        smsLogDao.insertALog(sql,params);
        /*************************************/
        System.out.println("bizId:"+bizId);
        System.out.println("code:"+code);
        System.out.println("message:"+message);
        /*************************************/
        return sendSmsResponse;
    }

   /* //根据流水号给到假未到岗人员发送提醒短信
    public SendSmsResponse sendAlertSMS(Integer serialnumber){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMAT_YMD);

        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryALeaveInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取本人手机号
        String[] phoneNumberSet = new String[]{person.getPhone()};

        //封装短信模板参数数据
        List<String> templateParamList = new ArrayList<>();

        Date end_date_maybe = leaveInfo.getEnd_date_maybe();
        Integer daysBetween = DateUtils.getDaysBetween(end_date_maybe, new Date());

        if(daysBetween>0){
            *//*{1}，您好，您在{2}申请的{3}已超出规定销假时间{4}天，请及时到岗销假！*//*

            //封装短信模板数据
            templateParamList.add(person.getName());
            templateParamList.add(simpleDateFormat.format(leaveInfo.getStart_date()));
            templateParamList.add(leaveInfo.getLeave_type());
            templateParamList.add(Integer.toString(daysBetween));

            String[] templateParams = WebUtils.stringListTostringArray(templateParamList);

            //发送前保存时间，写入日志
            Date start_time = DateUtils.timestampToDate_YMDHMS(new Date());
            //发送短信，并获取响应对象
            SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(SendMsg.SENDMSGSDKAPPID,
                    SendMsg.SIGNNAME_ZBXWZZB,
                    SendMsg.TEMPLATEID_ALERTTORETURN_HISTORY, phoneNumberSet, templateParams);
            //通过发射后的相应对象，获取此次发射的响应状态集数组
            SendStatus[] sendStatusSet = sendSmsResponse.getSendStatusSet();
            //由于每次只会有一个集合，故取sendStatusSet[0]即可获取此次发射的响应状态集合
            SendStatus thisSendStatus = sendStatusSet[0];
            //发送后保存时间，写入日志
            Date end_time = DateUtils.timestampToDate_YMDHMS(new Date());

            //写入日志
            String sql = "insert into sms_sendlog(SerialNo_sendmsg_thisSystem,PhoneNumber,SerialNo_askforleave_thisSystem," +
                    "person_id,SerialNo_sendmsg_server,SmsSdkAppId,TemplateId,SignName,SmsContent," +
                    "SendStatus,ErrorMsg,Fee,IsoCode,send_startdate,send_enddate,operator) values(null,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?)";

            List<Object> params = new ArrayList<>();
            params.add(phoneNumberSet[0]);//PhoneNumber
            params.add(serialnumber);//SerialNo_askforleave_thisSystem
            params.add(person_id);//person_id

            params.add(thisSendStatus.getSerialNo());//SerialNo_sendmsg_server,from res

            params.add(SendMsg.SENDMSGSDKAPPID);//SmsSdkAppId
            params.add(SendMsg.TEMPLATEID_ALERTTORETURN_HISTORY);//TemplateId
            params.add(SendMsg.SIGNNAME_ZBXWZZB);//SignName
            params.add(SendMsg.getSMSContent(SendMsg.TEMPLATEID_ALERTTORETURN_HISTORY,templateParams));//SmsContent

            params.add(thisSendStatus.getCode());//SendStatus,from res
            params.add(thisSendStatus.getMessage());//ErrorMsg,from res
            params.add(thisSendStatus.getFee());//Fee,from res
            params.add(thisSendStatus.getIsoCode());//IsoCode,from res

            params.add(start_time);//send_startdate
            params.add(end_time);//send_enddate
            params.add(leaveInfo.getStart_leave_operator());//operator
            //写入日志
            smsLogDao.insertALog(sql,params);
            return sendSmsResponse;
        }else {
            *//*{1}，您好，您在{2}申请的{3}已到规定销假时间，请及时到岗销假！*//*
            //封装短信模板数据
            templateParamList.add(person.getName());
            templateParamList.add(simpleDateFormat.format(leaveInfo.getStart_date()));
            templateParamList.add(leaveInfo.getLeave_type());

            String[] templateParams = WebUtils.stringListTostringArray(templateParamList);

            //发送前保存时间，写入日志
            Date start_time = DateUtils.timestampToDate_YMDHMS(new Date());
            //发送短信，并获取响应对象
            SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(SendMsg.SENDMSGSDKAPPID,
                    SendMsg.SIGNNAME_ZBXWZZB,
                    SendMsg.TEMPLATEID_ALERTTORETURN, phoneNumberSet, templateParams);
            //通过发射后的相应对象，获取此次发射的响应状态集数组
            SendStatus[] sendStatusSet = sendSmsResponse.getSendStatusSet();
            //由于每次只会有一个集合，故取sendStatusSet[0]即可获取此次发射的响应状态集合
            SendStatus thisSendStatus = sendStatusSet[0];
            //发送后保存时间，写入日志
            Date end_time = DateUtils.timestampToDate_YMDHMS(new Date());

            //写入日志
            String sql = "insert into sms_sendlog(SerialNo_sendmsg_thisSystem,PhoneNumber,SerialNo_askforleave_thisSystem," +
                    "person_id,SerialNo_sendmsg_server,SmsSdkAppId,TemplateId,SignName,SmsContent," +
                    "SendStatus,ErrorMsg,Fee,IsoCode,send_startdate,send_enddate,operator) values(null,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?)";

            List<Object> params = new ArrayList<>();
            params.add(phoneNumberSet[0]);//PhoneNumber
            params.add(serialnumber);//SerialNo_askforleave_thisSystem
            params.add(person_id);//person_id

            params.add(thisSendStatus.getSerialNo());//SerialNo_sendmsg_server,from res

            params.add(SendMsg.SENDMSGSDKAPPID);//SmsSdkAppId
            params.add(SendMsg.TEMPLATEID_ALERTTORETURN);//TemplateId
            params.add(SendMsg.SIGNNAME_ZBXWZZB);//SignName
            params.add(SendMsg.getSMSContent(SendMsg.TEMPLATEID_ALERTTORETURN,templateParams));//SmsContent

            params.add(thisSendStatus.getCode());//SendStatus,from res
            params.add(thisSendStatus.getMessage());//ErrorMsg,from res
            params.add(thisSendStatus.getFee());//Fee,from res
            params.add(thisSendStatus.getIsoCode());//IsoCode,from res

            params.add(start_time);//send_startdate
            params.add(end_time);//send_enddate
            params.add(leaveInfo.getStart_leave_operator());//operator
            //写入日志
            smsLogDao.insertALog(sql,params);

            return sendSmsResponse;
        }

    }*/
/*---------------------------------------------------------------------------------------------------------------------*/
    //将leaveinfo对象和person对象合封装成一个map，并加入list，以list形式返回前端
    private List<HashMap<String,Object>> formatLeaveInfo(List<LeaveInfo> leaveInfoList) {
        List<HashMap<String,Object>> mapList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        for (LeaveInfo leaveInfo: leaveInfoList){
            Integer person_id = leaveInfo.getPerson_id();

            if(person_id!=null){
                Person person = personDao.queryPersonInfoByID(person_id);
                //封装回显数据
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("person_id", person.getPerson_id());
                hashMap.put("name",person.getName());
                hashMap.put("office",person.getOffice());
                hashMap.put("post",person.getPost());
                hashMap.put("phone", person.getPhone());
                hashMap.put("sex", person.getSex());
                hashMap.put("nation", person.getNation());
                hashMap.put("nativePlace", person.getNativePlace());
                hashMap.put("area_class", person.getArea_class());
                hashMap.put("level", person.getLevel());
                hashMap.put("allow_Leave_Days", person.getAllow_Leave_Days());
                if(person.getBirthDate()!=null){
                    hashMap.put("birthDate", simpleDateFormat.format(person.getBirthDate()));
                }
                hashMap.put("serialnumber",leaveInfo.getSerialnumber());
                hashMap.put("leave_type",leaveInfo.getLeave_type());
                if(leaveInfo.getStart_date()!=null){
                    hashMap.put("start_date",simpleDateFormat.format(leaveInfo.getStart_date()));
                }
                hashMap.put("leave_days_projected",leaveInfo.getLeave_days_projected());
                hashMap.put("work_leader",leaveInfo.getWork_leader());
                hashMap.put("leave_reason",leaveInfo.getLeave_reason());
                hashMap.put("approver",leaveInfo.getApprover());
                hashMap.put("depart_location",leaveInfo.getDepart_location());
                hashMap.put("arrive_location",leaveInfo.getArrive_location());
                if(leaveInfo.getEnd_date_maybe()!=null){
                    hashMap.put("end_date_maybe",simpleDateFormat.format(leaveInfo.getEnd_date_maybe()));
                }
                hashMap.put("start_leave_remark",leaveInfo.getStart_leave_remark());
                hashMap.put("start_leave_operator",leaveInfo.getStart_leave_operator());
                if(leaveInfo.getEnd_date()!=null){
                    hashMap.put("end_date",simpleDateFormat.format(leaveInfo.getEnd_date()));
                }
                hashMap.put("end_leave_remark",leaveInfo.getEnd_leave_remark());
                hashMap.put("end_leave_operator",leaveInfo.getEnd_leave_operator());
                hashMap.put("leave_days_actual",leaveInfo.getLeave_days_actual());
                mapList.add(hashMap);
            }
        }
        return mapList;
    }

    //将leaveinfo对象和person对象合封装成一个map，返回前端
    private HashMap<String,Object> getInfoRTNMap(LeaveInfo leaveInfo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        int person_id = leaveInfo.getPerson_id();
        Person person = personDao.queryPersonInfoByID(person_id);

        //封装回显数据
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("person_id", person.getPerson_id());
        hashMap.put("name",person.getName());
        hashMap.put("office",person.getOffice());
        hashMap.put("post",person.getPost());
        hashMap.put("phone", person.getPhone());
        hashMap.put("sex", person.getSex());
        hashMap.put("nation", person.getNation());
        hashMap.put("nativePlace", person.getNativePlace());
        hashMap.put("area_class", person.getArea_class());
        hashMap.put("level", person.getLevel());
        hashMap.put("allow_Leave_Days", person.getAllow_Leave_Days());
        if(person.getBirthDate()!=null){
            hashMap.put("birthDate", simpleDateFormat.format(person.getBirthDate()));
        }
        hashMap.put("serialnumber",leaveInfo.getSerialnumber());
        hashMap.put("leave_type",leaveInfo.getLeave_type());
        if(leaveInfo.getStart_date()!=null){
            hashMap.put("start_date",simpleDateFormat.format(leaveInfo.getStart_date()));
        }
        hashMap.put("leave_days_projected",leaveInfo.getLeave_days_projected());
        hashMap.put("work_leader",leaveInfo.getWork_leader());
        hashMap.put("leave_reason",leaveInfo.getLeave_reason());
        hashMap.put("office",leaveInfo.getOffice());
        hashMap.put("phone",leaveInfo.getPhone());
        hashMap.put("leave_reason",leaveInfo.getLeave_reason());
        hashMap.put("approver",leaveInfo.getApprover());
        hashMap.put("depart_location",leaveInfo.getDepart_location());
        hashMap.put("arrive_location",leaveInfo.getArrive_location());
        if(leaveInfo.getEnd_date_maybe()!=null){
            hashMap.put("end_date_maybe",simpleDateFormat.format(leaveInfo.getEnd_date_maybe()));
        }
        hashMap.put("start_leave_remark",leaveInfo.getStart_leave_remark());
        hashMap.put("start_leave_operator",leaveInfo.getStart_leave_operator());
        if(leaveInfo.getEnd_date()!=null){
            hashMap.put("end_date",simpleDateFormat.format(leaveInfo.getEnd_date()));
        }
        hashMap.put("end_leave_remark",leaveInfo.getEnd_leave_remark());
        hashMap.put("end_leave_operator",leaveInfo.getEnd_leave_operator());
        hashMap.put("leave_days_actual",leaveInfo.getLeave_days_actual());

        return hashMap;
    }

    public HashMap<String, Object> queryALeaveInfoForPrintBySerialnumber(Integer serialnumber){
        LeaveInfo leaveInfo = askForLeaveDao.queryALeaveInfoBySerialnumber(serialnumber);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        int person_id = leaveInfo.getPerson_id();
        Person person = personDao.queryPersonInfoByID(person_id);

        //封装回显数据
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("person_id", person.getPerson_id());
        hashMap.put("name",person.getName());
        hashMap.put("office",person.getOffice());
        hashMap.put("post",person.getPost());
        hashMap.put("phone", person.getPhone());
        hashMap.put("sex", person.getSex());
        hashMap.put("nation", person.getNation());
        hashMap.put("nativePlace", person.getNativePlace());

        hashMap.put("marriage_status", person.getMarriage_status());
        hashMap.put("name_spouse", person.getName_spouse());
        hashMap.put("nativeplace_spouse", person.getNativeplace_spouse());

        hashMap.put("area_class", person.getArea_class());
        hashMap.put("level", person.getLevel());
        hashMap.put("allow_Leave_Days", person.getAllow_Leave_Days());
        if(person.getBirthDate()!=null){
            hashMap.put("birthDate", simpleDateFormat.format(person.getBirthDate()));
        }
        hashMap.put("serialnumber",leaveInfo.getSerialnumber());
        hashMap.put("leave_type",leaveInfo.getLeave_type());
        if(leaveInfo.getStart_date()!=null){
            hashMap.put("start_date",simpleDateFormat.format(leaveInfo.getStart_date()));
        }
        hashMap.put("leave_days_projected",leaveInfo.getLeave_days_projected());
        hashMap.put("work_leader",leaveInfo.getWork_leader());
        hashMap.put("leave_reason",leaveInfo.getLeave_reason());
        hashMap.put("office",leaveInfo.getOffice());
        hashMap.put("phone",leaveInfo.getPhone());
        hashMap.put("leave_reason",leaveInfo.getLeave_reason());
        hashMap.put("approver",leaveInfo.getApprover());
        hashMap.put("depart_location",leaveInfo.getDepart_location());
        hashMap.put("arrive_location",leaveInfo.getArrive_location());
        if(leaveInfo.getEnd_date_maybe()!=null){
            hashMap.put("end_date_maybe",simpleDateFormat.format(leaveInfo.getEnd_date_maybe()));
        }
        hashMap.put("start_leave_remark",leaveInfo.getStart_leave_remark());
        hashMap.put("start_leave_operator",leaveInfo.getStart_leave_operator());
        if(leaveInfo.getEnd_date()!=null){
            hashMap.put("end_date",simpleDateFormat.format(leaveInfo.getEnd_date()));
        }
        hashMap.put("end_leave_remark",leaveInfo.getEnd_leave_remark());
        hashMap.put("end_leave_operator",leaveInfo.getEnd_leave_operator());
        hashMap.put("leave_days_actual",leaveInfo.getLeave_days_actual());
        hashMap.put("operate_date",simpleDateFormat.format(new Date()));

        return hashMap;
    }

    /*
     * @Description :查询所有到假未到岗人员
     * @param: pageNo
     * @param: pageSize
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @Author tianfeichen
     * @Date 2022/1/28 19:18
     **/
    public List<HashMap<String, Object>> queryAllCurrentEOLPerson(Integer pageNo,Integer pageSize) {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryAllCurrentEOLPerson(pageNo, pageSize);



        List<HashMap<String,Object>> mapList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        for (LeaveInfo leaveInfo: leaveInfoList){
            int  person_id = leaveInfo.getPerson_id();
            Person person = personDao.queryPersonInfoByID(person_id);
            //封装回显数据
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("person_id", person.getPerson_id());
            hashMap.put("name",person.getName());
            hashMap.put("office",person.getOffice());
            hashMap.put("post",person.getPost());
            hashMap.put("phone", person.getPhone());
            hashMap.put("sex", person.getSex());
            hashMap.put("nation", person.getNation());
            hashMap.put("nativePlace", person.getNativePlace());
            hashMap.put("area_class", person.getArea_class());
            hashMap.put("level", person.getLevel());
            hashMap.put("allow_Leave_Days", person.getAllow_Leave_Days());
            if(person.getBirthDate()!=null){
                hashMap.put("birthDate", simpleDateFormat.format(person.getBirthDate()));
            }
            hashMap.put("serialnumber",leaveInfo.getSerialnumber());
            hashMap.put("leave_type",leaveInfo.getLeave_type());
            if(leaveInfo.getStart_date()!=null){
                hashMap.put("start_date",simpleDateFormat.format(leaveInfo.getStart_date()));
            }
            hashMap.put("leave_days_projected",leaveInfo.getLeave_days_projected());
            hashMap.put("work_leader",leaveInfo.getWork_leader());
            hashMap.put("leave_reason",leaveInfo.getLeave_reason());
            hashMap.put("approver",leaveInfo.getApprover());
            hashMap.put("depart_location",leaveInfo.getDepart_location());
            hashMap.put("arrive_location",leaveInfo.getArrive_location());
            if(leaveInfo.getEnd_date_maybe()!=null){
                hashMap.put("end_date_maybe",simpleDateFormat.format(leaveInfo.getEnd_date_maybe()));
            }
            hashMap.put("start_leave_remark",leaveInfo.getStart_leave_remark());
            hashMap.put("start_leave_operator",leaveInfo.getStart_leave_operator());
            //新增提醒短信发送数据
            hashMap.put("send_alertsms_count",smsLogDao.queryCountOfAlertsms_history(leaveInfo.getSerialnumber()));

            mapList.add(hashMap);
        }

        return mapList;
    }

    
    /*
     * @Description ：根据条件查询所有到假未到岗人员
     * @Param map
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2022/3/4 22:33
     */
    public List<HashMap<String, Object>> querySomeAllCurrentEOLPerson(Map<String, String[]> map,Integer pageNo, Integer pageSize) throws ParseException {
        List<LeaveInfo> leaveInfoList
                = askForLeaveDao.querySomeAllCurrentEOLPerson(map, pageNo, pageSize);

        List<HashMap<String,Object>> mapList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        for (LeaveInfo leaveInfo: leaveInfoList){
            int  person_id = leaveInfo.getPerson_id();
            Person person = personDao.queryPersonInfoByID(person_id);
            //封装回显数据
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("person_id", person.getPerson_id());
            hashMap.put("name",person.getName());
            hashMap.put("office",person.getOffice());
            hashMap.put("post",person.getPost());
            hashMap.put("phone", person.getPhone());
            hashMap.put("sex", person.getSex());
            hashMap.put("nation", person.getNation());
            hashMap.put("nativePlace", person.getNativePlace());
            hashMap.put("area_class", person.getArea_class());
            hashMap.put("level", person.getLevel());
            hashMap.put("allow_Leave_Days", person.getAllow_Leave_Days());
            if(person.getBirthDate()!=null){
                hashMap.put("birthDate", simpleDateFormat.format(person.getBirthDate()));
            }
            hashMap.put("serialnumber",leaveInfo.getSerialnumber());
            hashMap.put("leave_type",leaveInfo.getLeave_type());
            if(leaveInfo.getStart_date()!=null){
                hashMap.put("start_date",simpleDateFormat.format(leaveInfo.getStart_date()));
            }
            hashMap.put("leave_days_projected",leaveInfo.getLeave_days_projected());
            hashMap.put("work_leader",leaveInfo.getWork_leader());
            hashMap.put("leave_reason",leaveInfo.getLeave_reason());
            hashMap.put("approver",leaveInfo.getApprover());
            hashMap.put("depart_location",leaveInfo.getDepart_location());
            hashMap.put("arrive_location",leaveInfo.getArrive_location());
            if(leaveInfo.getEnd_date_maybe()!=null){
                hashMap.put("end_date_maybe",simpleDateFormat.format(leaveInfo.getEnd_date_maybe()));
            }
            hashMap.put("start_leave_remark",leaveInfo.getStart_leave_remark());
            hashMap.put("start_leave_operator",leaveInfo.getStart_leave_operator());
            //新增提醒短信发送数据
//            hashMap.put("send_alertsms_count",smsLogDao.queryCountOfAlertsms_today(leaveInfo.getSerialnumber()));

            mapList.add(hashMap);
        }

        return mapList;
    }

    
    /*
     * @Description ：查询今日应到假人员信息
     * @Param
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/16 11:54
     */
    public List<HashMap<String, Object>> queryCurrentEOLPerson(Integer pageNo,Integer pageSize) {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryCurrentEOLPerson(pageNo, pageSize);

        List<HashMap<String,Object>> mapList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        for (LeaveInfo leaveInfo: leaveInfoList){
            int  person_id = leaveInfo.getPerson_id();
            Person person = personDao.queryPersonInfoByID(person_id);
            //封装回显数据
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("person_id", person.getPerson_id());
            hashMap.put("name",person.getName());
            hashMap.put("office",person.getOffice());
            hashMap.put("post",person.getPost());
            hashMap.put("phone", person.getPhone());
            hashMap.put("sex", person.getSex());
            hashMap.put("nation", person.getNation());
            hashMap.put("nativePlace", person.getNativePlace());
            hashMap.put("area_class", person.getArea_class());
            hashMap.put("level", person.getLevel());
            hashMap.put("allow_Leave_Days", person.getAllow_Leave_Days());
            if(person.getBirthDate()!=null){
                hashMap.put("birthDate", simpleDateFormat.format(person.getBirthDate()));
            }
            hashMap.put("serialnumber",leaveInfo.getSerialnumber());
            hashMap.put("leave_type",leaveInfo.getLeave_type());
            if(leaveInfo.getStart_date()!=null){
                hashMap.put("start_date",simpleDateFormat.format(leaveInfo.getStart_date()));
            }
            hashMap.put("leave_days_projected",leaveInfo.getLeave_days_projected());
            hashMap.put("work_leader",leaveInfo.getWork_leader());
            hashMap.put("leave_reason",leaveInfo.getLeave_reason());
            hashMap.put("approver",leaveInfo.getApprover());
            hashMap.put("depart_location",leaveInfo.getDepart_location());
            hashMap.put("arrive_location",leaveInfo.getArrive_location());
            if(leaveInfo.getEnd_date_maybe()!=null){
                hashMap.put("end_date_maybe",simpleDateFormat.format(leaveInfo.getEnd_date_maybe()));
            }
            hashMap.put("start_leave_remark",leaveInfo.getStart_leave_remark());
            hashMap.put("start_leave_operator",leaveInfo.getStart_leave_operator());
            //新增提醒短信发送数据
            hashMap.put("send_alertsms_count",smsLogDao.queryCountOfAlertsms_today(leaveInfo.getSerialnumber()));

            mapList.add(hashMap);
        }

        return mapList;
    }

    /*
     * @Description ：根据条件查询所有今日应到假人员信息
     * @Param map
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2022/3/4 22:33
     */
    public List<HashMap<String, Object>> querySomeCurrentEOLPerson(
            Map<String, String[]> map
            ,Integer pageNo, Integer pageSize) throws ParseException {
        List<LeaveInfo> leaveInfoList
                = askForLeaveDao.querySomeCurrentEOLPerson(map, pageNo, pageSize);

        List<HashMap<String,Object>> mapList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        for (LeaveInfo leaveInfo: leaveInfoList){
            int  person_id = leaveInfo.getPerson_id();
            Person person = personDao.queryPersonInfoByID(person_id);
            //封装回显数据
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("person_id", person.getPerson_id());
            hashMap.put("name",person.getName());
            hashMap.put("office",person.getOffice());
            hashMap.put("post",person.getPost());
            hashMap.put("phone", person.getPhone());
            hashMap.put("sex", person.getSex());
            hashMap.put("nation", person.getNation());
            hashMap.put("nativePlace", person.getNativePlace());
            hashMap.put("area_class", person.getArea_class());
            hashMap.put("level", person.getLevel());
            hashMap.put("allow_Leave_Days", person.getAllow_Leave_Days());
            if(person.getBirthDate()!=null){
                hashMap.put("birthDate", simpleDateFormat.format(person.getBirthDate()));
            }
            hashMap.put("serialnumber",leaveInfo.getSerialnumber());
            hashMap.put("leave_type",leaveInfo.getLeave_type());
            if(leaveInfo.getStart_date()!=null){
                hashMap.put("start_date",simpleDateFormat.format(leaveInfo.getStart_date()));
            }
            hashMap.put("leave_days_projected",leaveInfo.getLeave_days_projected());
            hashMap.put("work_leader",leaveInfo.getWork_leader());
            hashMap.put("leave_reason",leaveInfo.getLeave_reason());
            hashMap.put("approver",leaveInfo.getApprover());
            hashMap.put("depart_location",leaveInfo.getDepart_location());
            hashMap.put("arrive_location",leaveInfo.getArrive_location());
            if(leaveInfo.getEnd_date_maybe()!=null){
                hashMap.put("end_date_maybe",simpleDateFormat.format(leaveInfo.getEnd_date_maybe()));
            }
            hashMap.put("start_leave_remark",leaveInfo.getStart_leave_remark());
            hashMap.put("start_leave_operator",leaveInfo.getStart_leave_operator());
            //新增提醒短信发送数据
            /*hashMap.put("send_alertsms_count",smsLogDao.queryCountOfAlertsms_today(leaveInfo.getSerialnumber()));*/

            mapList.add(hashMap);
        }

        return mapList;
    }

    
    /*
     * @Description ：按人员编号查询本年度某种假期的详细请假数据
     * @Param person_id
     * @Param leave_type
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<LeaveInfo>
     * @Author: CTF
     * @Date ：2021/12/10 0:51
     */
    public List<LeaveInfo> queryOnesHistoryInfoOfOneLeaveTypeByPersonID(Integer person_id, String leave_type, Integer pageNo, Integer pageSize) {
        return askForLeaveDao.queryOnesHistoryInfoOfOneLeaveTypeByPersonID(person_id,
                leave_type,pageNo,pageSize);
    }

    
    /*
     * @Description ：按人员编号查询本年度某种假期统计数据
     * @Param person_id
     * @Param leave_type
     * @Return ：LeaveInfoCount
     * @Author: CTF
     * @Date ：2021/12/10 0:51
     */
    public LeaveInfoCount queryThisYearOneLeaveInfoCountByPersonID(Integer person_id, String leave_type) {
        return askForLeaveDao.queryThisYearOneLeaveInfoCount(person_id,leave_type);
    }

    
    /*
     * @Description ：按人员编号查询近期累计在岗天数
     * @Param person_id
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/10 0:51
     */
    public int queryRecentWorkDaysByPersonID(Integer person_id) {
        return askForLeaveDao.queryRecentWorkDaysByPersonID(person_id);
    }

    
    /*
     * @Description ：查询本年度请假信息统计数据
     * @Param person_id
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/9 23:47
     */
    public List<LeaveInfoCount> queryThisYearLeaveInfoCountByPersonID(Integer person_id, Integer pageNo, Integer pageSize) {
        return askForLeaveDao.queryThisYearLeaveInfoCount(person_id,pageNo,pageSize);
    }

    
    /*
     * @Description ：根据人员编号person_id查询本年度历史请假记录
     * @Param person_id
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/9 23:00
     */
    public List<HashMap<String,Object>> queryOnesHistoryInfoByPersonID(Integer person_id,Integer pageNo,Integer pageSize) {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryOnesHistoryInfoByPersonID(person_id, pageNo, pageSize);
        List<HashMap<String,Object>> hashMapList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        for(LeaveInfo leaveInfo : leaveInfoList){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("leave_type",leaveInfo.getLeave_type());
            hashMap.put("actualDays",leaveInfo.getLeave_days_actual());
            hashMap.put("startDate",simpleDateFormat.format(leaveInfo.getStart_date()));
            hashMap.put("endDate", simpleDateFormat.format(leaveInfo.getEnd_date()));
            hashMapList.add(hashMap);
        }
        return hashMapList;
    }

    /*
     * @Description :查询一个人所在单位的正在请假情况统计数据
     * @param: person_id 人员编号
     * @param: pageNo
     * @param: pageSize
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @Author tianfeichen
     * @Date 2022/5/22 22:16
     **/
    public HashMap<String,Object> queryOfficeLeaveInfoByPersonId(Integer person_id,Integer pageNo,Integer pageSize) {
        Person person = personDao.queryPersonInfoByID(person_id);
        //获取此人本单位当前请假情况
        List<OfficeLeaveInfoCount> leaveInfoList = askForLeaveDao.queryOfficeLeaveInfoByPersonIdGroupPersonId(person_id);
        //获取此人本单位的人员情况
        List<Person> personList = personDao.queryPersonByOffice(person.getOffice());

        int personCounts = personList.size();
        int leavingCounts = leaveInfoList.size();
        double workingPercent = 0.0;
        if(personCounts==0){
            workingPercent = 0.0;
        }else {
            workingPercent = ((double)(personCounts-leavingCounts)/personCounts)*100;
        }
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("personCounts",personCounts);
        hashMap.put("leavingCounts",leavingCounts);
        hashMap.put("workingPercent",String.format("%.2f",workingPercent)+"%");
        return hashMap;
    }
    /*
     * @Description :查询一个人所在单位的正在请假情况详情
     * @param: person_id 人员编号
     * @param: pageNo
     * @param: pageSize
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @Author tianfeichen
     * @Date 2022/5/22 22:16
     **/
    public  List<HashMap<String, Object>> queryOfficeCurrentLeaveInfoByPersonId(Integer person_id, Integer pageNo, Integer pageSize) {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryOfficeLeaveInfoByPersonId(person_id,pageNo,pageSize);
        return formatLeaveInfo(leaveInfoList);
    }

    
    /*
     * @Description ：根据流水号删除一条历史记录，并执行相关操作
     * @Param serialnumber
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/5 16:21
     */
    public int deleteAHistoryInfoBySerialnumber(int serialnumber,String delete_reason,
                                  String delete_operator) throws Exception {

        return deleteAResumeWorkInfoBySerialnumber(serialnumber,delete_reason,"已废除",delete_operator);
    }


    /*
     * @Description ：根据流水号删除一条请假信息，并执行相关操作
     * @Param serialnumber
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/5 16:21
     */
    public int deleteALeaveInfoBySerialnumber(int serialnumber,String delete_reason,String status, String delete_operator) throws Exception {
        //查找主表中相关数据
        LeaveInfo oldLeaveInfo = askForLeaveDao.queryALeaveInfoBySerialnumber(serialnumber);
        //删除历史记录表approval表中的数据
        int deleteCode = askForLeaveDao.deleteALeaveInfoBySerialnumber(serialnumber);
        //设置状态
        oldLeaveInfo.setStatus(status);
        //更新备份表数据
        int backupsInfoUpdateCode = askForLeaveDao.updateBackupLeaveInfo(oldLeaveInfo);
        //记录日志
        LeaveInfo leaveInfo = new LeaveInfo();
        leaveInfo.setSerialnumber(serialnumber);
        leaveInfo.setStart_leave_operator(delete_operator);
        int operateLogAddCode = askForLeaveDao.addAnOperateLogOfAskforleave(leaveInfo,"删除","删除事务,原因："+delete_reason);

        if(deleteCode!=1){
            System.out.println("deleteLeaveInfoCode:"+deleteCode);
            throw new Exception("数据库数据删除失败");
        }
        if(operateLogAddCode!=1){
            System.out.println("operateLeaveInfoLogAddCode:"+operateLogAddCode);
            throw new Exception("日志写入失败");
        }
        if(backupsInfoUpdateCode!=1){
            System.out.println("backupsLeaveInfoUpdateCode:"+backupsInfoUpdateCode);
            throw new Exception("数据库备份数据删除失败");
        }
        return 1;
    }

    /*
     * @Description : 根据序列号删除一条销假记录
     * @param: serialnumber
     * @param: delete_reason
     * @param: status
     * @param: delete_operator
     * @return int
     * @Author tianfeichen
     * @Date 2022/5/22 18:15
     **/
    public int deleteAResumeWorkInfoBySerialnumber(int serialnumber,String delete_reason,String status, String delete_operator) throws Exception {
        //查找主表中相关数据
        LeaveInfo oldResumeWorkInfo = askForLeaveDao.queryResumeWorkInfoBySerialnumber(serialnumber);

        //删除销假记录表主表resume_work表中的数据
        int deleteCode = askForLeaveDao.deleteAResumeWorkInfoBySerialnumber(serialnumber);

        //设置状态
        oldResumeWorkInfo.setStatus(status);

        //更新备份表数据
        int backupsInfoUpdateCode = askForLeaveDao.updateBackupResumeWorkInfo(oldResumeWorkInfo);
        //记录日志
        int operateLogAddCode = askForLeaveDao.addAnOperateLogOfResumeWork(serialnumber,delete_operator,
                "删除","删除事务,原因："+delete_reason);

        if(deleteCode!=1){
            System.out.println("deleteResumeWorkInfoCode:"+deleteCode);
            throw new Exception("数据库数据删除失败");
        }
        if(operateLogAddCode!=1){
            System.out.println("operateResumeWorkLogAddCode:"+operateLogAddCode);
            throw new Exception("日志写入失败");
        }
        if(backupsInfoUpdateCode!=1){
            System.out.println("backupsResumeWorkInfoUpdateCode:"+backupsInfoUpdateCode);
            throw new Exception("数据库备份数据删除失败");
        }
        return 1;
    }

    //批量删除请假记录
    public int batchDeleteALeaveInfoBySerialnumber(List<Integer> serialnumberList,String delete_reason, String delete_operator){
        int deleteCount = 0;
        for(Integer serialnumber : serialnumberList){
            try {
                deleteALeaveInfoBySerialnumber(serialnumber,delete_reason,"已废除",delete_operator);
                deleteCount++;
            } catch (Exception e) {
                deleteCount--;
            }
        }
        return deleteCount;
    }

    //批量删除销假记录
    public int batchDeleteHistoryInfoBySerialnumber(List<Integer> serialnumberList,String delete_reason, String delete_operator){
        int deleteCount = 0;
        for(Integer serialnumber : serialnumberList){
            try {
                deleteAResumeWorkInfoBySerialnumber(serialnumber,delete_reason,"已废除",delete_operator);
                deleteCount++;
            } catch (Exception e) {
                deleteCount--;
            }
        }
        return deleteCount;
    }

    //批量执行销假操作
    public int batchResumeWork(String[] serialnumberList,String end_leave_remark,
                               String end_date,  String end_leave_operator){

        int excuteCount = 0;
        for(String serialnumber : serialnumberList){
            try {
                resumeWork(serialnumber,end_leave_remark,end_date,end_leave_operator);
                excuteCount++;
            } catch (Exception e) {
                excuteCount--;
            }
        }
        return excuteCount;
    }

    //根据人员编号查询此人的请假记录
    public List<LeaveInfo> queryLeaveInfoByPersonId(Integer personID){
        return askForLeaveDao.queryLeaveInfoByPersonId(personID);
    }

    /*
     * @Description ：根据流水号查询一条历史记录，与人员基础数据合并后返回
     * @Param serialnumber 请假流水号
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/5 15:37
     */
    public HashMap<String, Object> queryAHistoryInfo(int serialnumber) {
        LeaveInfo leaveInfo = askForLeaveDao.queryAHistoryInfo(serialnumber);
        return getInfoRTNMap(leaveInfo);
    }

    
    /*
     * @Description ：按条件查询历史请假记录_不分页
     * @Param map
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/5 12:28
     */
    public List<HashMap<String, Object>> querySomeHistoryInfo(Map<String, String[]> map, Integer pageNo, Integer pageSize) throws ParseException {
        List<LeaveInfo> historyInfoList = askForLeaveDao.querySomeHistoryInfo(map,pageNo,pageSize);
        return formatLeaveInfo(historyInfoList);
    }

    /*
     * @Description ：查询全部历史请假记录
     * @Param
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 21:50
     */
    public List<HashMap<String, Object>> queryALLHistoryInfo(User user,Integer pageNo, Integer pageSize) {
        List<LeaveInfo> historyInfoList = askForLeaveDao.queryALLHistoryInfo(user,pageNo,pageSize);
        return formatLeaveInfo(historyInfoList);
    }

    
    /*
     * @Description ：处理销假业务
     * @Param serialnumberSTR
     * @Param end_leave_remarkSTR
     * @Param end_dateSTR
     * @Param end_leave_operator
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/4 21:55
     */
    public  Map<String, Object>  resumeWork(String serialnumberSTR, String end_leave_remarkSTR,
                          String end_dateSTR, String end_leave_operator) throws ParseException {
        //记录操作
        HashMap<String,Object> hashMap = new HashMap<>();

        //1.解析字符串数据
        //流水号
        Integer serialnumber = null;
        if(serialnumberSTR!=null){
            serialnumber = Integer.parseInt(serialnumberSTR);
        }
        // 获取请假数据，封装为LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryALeaveInfoBySerialnumber(serialnumber);
        //销假日期
        Date end_date = new SimpleDateFormat("yyyy年MM月dd日").parse(end_dateSTR);
        //计算实际请假天数_开始
        Date start_date =leaveInfo.getStart_date();
        Integer leaveDaysActual = DateUtils.getDaysBetweenPlusOne(start_date,end_date);
        //计算实际请假天数_结束
        leaveInfo.setLeave_days_actual(leaveDaysActual);
        leaveInfo.setEnd_leave_remark(end_leave_remarkSTR);
        leaveInfo.setEnd_date(end_date);
        leaveInfo.setEnd_leave_operator(end_leave_operator);

        //2.数据库业务
        //2.1 将该条数据数据插入已销假记录表中
        int insertMainResumeworkInfoCode = askForLeaveDao.insertAResumeWorkInfo(
                serialnumber,
                leaveInfo.getPerson_id(),
                leaveDaysActual,
                end_leave_remarkSTR,
                end_date,
                end_leave_operator);

        if(insertMainResumeworkInfoCode==1){
            //插入销假记录成功
            //2.2 将该条数据数据插入已销假记录备份表中
            int insertBackupsResumeworkInfoCode = askForLeaveDao.insertAResumeWorkBackupInfo(serialnumber,
                    leaveInfo.getPerson_id(),
                    leaveDaysActual,
                    end_leave_remarkSTR,
                    end_date,
                    end_leave_operator);
            if(insertBackupsResumeworkInfoCode==1){
                //2.3 记录日志
                int insertLogCode = askForLeaveDao.addAnOperateLogOfResumeWork(serialnumber,
                        end_leave_operator,
                        "新增",
                        "新增一条销假记录，新增一条备份记录");
                //2.4 删除请假记录主表即approval表中的数据，修改请假记录备份表即approval_backups中数据，写入日志
                try {
                    int deleteApprovalSqlCode = deleteALeaveInfoBySerialnumber(serialnumber,"已销假","已销假",end_leave_operator);
                } catch (Exception e) {
                    //封装回显数据
                    hashMap.put("msg","请假数据删除失败"+e.getMessage());
                    hashMap.put("code",-5);
                    hashMap.put("leave_info",null);
                }

                //3.短信发送业务
                //3.1 查询发送对象代码
                int doesSendSelfCode = SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOSELF);
                int doesSendLeaderCode = SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOLEADER);

                if(doesSendLeaderCode == 1){
                    //3.1.1 若显示需要给领导发送短信，则调用sendMsgToLeaderBySerialnumberWhenResumeWork(int serialnumber)
                    try {
                        sendMsgToLeaderBySerialnumberWhenResumeWork(serialnumber);
                    } catch (Exception e) {
                        //封装回显数据
                        hashMap.put("msg","插入主表成功，备份成功，短信发送失败："+e.getMessage());
                        hashMap.put("code",1);
                        hashMap.put("leave_info",leaveInfo);
                    }
                }else {
                    hashMap.put("msg","操作成功，无需发送短信");
                    //将存入成功的LeaveInfo返回前端
                    hashMap.put("code",1);
                    hashMap.put("leave_info",leaveInfo);
                }

                if(doesSendSelfCode == 1){
                    //3.1.2 若显示需要给本人发送短信，则调用sendMsgToSelfBySerialnumberWhenResumeWork(int serialnumber)
                    try {
                        sendMsgToSelfBySerialnumberWhenResumeWork(serialnumber);
                    } catch (Exception e) {
                        //封装回显数据
                        hashMap.put("msg","插入主表成功，备份成功，短信发送失败："+e.getMessage());
                        hashMap.put("code",1);
                        hashMap.put("leave_info",leaveInfo);
                    }
                }else {
                    hashMap.put("msg","操作成功，无需发送短信");
                    //将存入成功的LeaveInfo返回前端
                    hashMap.put("code",1);
                    hashMap.put("leave_info",leaveInfo);
                }
                //封装回显数据
                hashMap.put("msg","插入主表成功，备份成功，短信发送成功");
                hashMap.put("code",1);
                hashMap.put("leave_info",leaveInfo);
            }
            else {
                hashMap.put("msg","插入主表成功，备份失败");
                hashMap.put("code",-2);
                hashMap.put("leave_info",null);
            }
        }else {
            hashMap.put("msg","操作失败");
            hashMap.put("code",-3);
            hashMap.put("leave_info",null);
        }
        return hashMap;
    }

    
    /*
     * @Description ：查询库中所有待销假信息
     * @Param
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 16:05
     */
    public List<HashMap<String, Object>> queryALLResumeWorkInfo(Integer pageNo, Integer pageSize) {
        List<LeaveInfo> resumeWorkList = askForLeaveDao.queryALLResumeWorkInfo(pageNo,pageSize);
        return formatLeaveInfo(resumeWorkList);
    }

    //修改请假数据
    public int updateLeaveInfo(LeaveInfo newLeaveInfo) throws Exception {
        //获取数据库中原始数据
        LeaveInfo oldLeaveInfo = askForLeaveDao.queryALeaveInfoBySerialnumber(newLeaveInfo.getSerialnumber());

        /*计算预计到岗时间并设置*/
        //获取请假开始时间
        Date start_date = newLeaveInfo.getStart_date();
        //获取预计请假天数
        Integer leave_days_projected = newLeaveInfo.getLeave_days_projected();
        //获得预计到岗时间
        Date end_date_maybe = DateUtils.addAndSubtractDays(start_date, leave_days_projected);
        //设置
        newLeaveInfo.setEnd_date_maybe(end_date_maybe);
        /*计算预计到岗时间并设置*/

        //设置人员姓名
        newLeaveInfo.setName(oldLeaveInfo.getName());
        //设置人员编号
        newLeaveInfo.setPerson_id(oldLeaveInfo.getPerson_id());
        //设置人员办公室信息
        newLeaveInfo.setOffice(oldLeaveInfo.getOffice());
        //设置人员联系方式
        newLeaveInfo.setPhone(oldLeaveInfo.getPhone());
        //设置事务状态
        newLeaveInfo.setStatus("使用中");
        //设置打印次数
        newLeaveInfo.setDoes_print(oldLeaveInfo.getDoes_print());

        //执行主数据更新操作
        int mainLeaveInfoUpdateCode = askForLeaveDao.updateMainLeaveInfo(newLeaveInfo);
        //执行备份数据更新操作
        int backupsLeaveInfoUpdateCode = askForLeaveDao.updateBackupLeaveInfo(newLeaveInfo);

        if(mainLeaveInfoUpdateCode!=1){
            throw new Exception("请假信息更新失败");
        }
        if(backupsLeaveInfoUpdateCode!=1){
            throw new Exception("请假备份信息更新失败");
        }

        String operateLog = getChangeLog(newLeaveInfo,oldLeaveInfo);
        if(!operateLog.trim().equals("")){
            //有修改的地方才写入日志
            int operateLogOfAskforleaveUpdateCode
                    = askForLeaveDao.addAnOperateLogOfAskforleave(newLeaveInfo,"修改",operateLog);
            if(operateLogOfAskforleaveUpdateCode!=1){
                throw new Exception("日志写入失败");
            }
        }

        return 1;
    }

    //修改请假备份数据
    public int updateLeaveBackupsInfo(LeaveInfo newLeaveInfo,String updateLocation) throws Exception {
        //获取数据库中原始数据
        LeaveInfo oldLeaveInfo = askForLeaveDao.queryALeaveInfoBacupsBySerialnumber(newLeaveInfo.getSerialnumber());

        /*计算预计到岗时间并设置*/
        //获取请假开始时间
        Date start_date = newLeaveInfo.getStart_date();
        //获取预计请假天数
        Integer leave_days_projected = newLeaveInfo.getLeave_days_projected();
        //获得预计到岗时间
        Date end_date_maybe = DateUtils.addAndSubtractDays(start_date, leave_days_projected);
        /*计算预计到岗时间并设置*/

        //设置预计到岗时间(不用设置新的时间，直接保存)
        newLeaveInfo.setEnd_date_maybe(oldLeaveInfo.getEnd_date_maybe());
        //设置人员姓名
        newLeaveInfo.setName(oldLeaveInfo.getName());
        //设置人员编号
        newLeaveInfo.setPerson_id(oldLeaveInfo.getPerson_id());
        //设置人员办公室信息
        newLeaveInfo.setOffice(oldLeaveInfo.getOffice());
        //设置人员联系方式
        newLeaveInfo.setPhone(oldLeaveInfo.getPhone());
        //设置事务状态
        newLeaveInfo.setStatus(oldLeaveInfo.getStatus());
        //设置打印次数
        newLeaveInfo.setDoes_print(oldLeaveInfo.getDoes_print());

        //执行备份数据更新操作
        int backupsLeaveInfoUpdateCode = askForLeaveDao.updateBackupLeaveInfo(newLeaveInfo);

        if(backupsLeaveInfoUpdateCode!=1){
            throw new Exception("请假备份信息更新失败");
        }

        //有修改的地方才写入日志
        int operateLogOfAskforleaveUpdateCode
                = askForLeaveDao.addAnOperateLogOfAskforleave(newLeaveInfo,
                "修改",
                "在"+updateLocation+"处修改："+oldLeaveInfo+"-"+newLeaveInfo);
        if(operateLogOfAskforleaveUpdateCode!=1){
            throw new Exception("日志写入失败");
        }

        return 1;
    }

    //修改销假数据
    public int updateResumeWorkInfo(LeaveInfo newLeaveInfo) throws Exception {
        //获取数据库中原始数据
        LeaveInfo oldLeaveInfo = askForLeaveDao.queryResumeWorkInfoBySerialnumber(newLeaveInfo.getSerialnumber());

        /*计算实际请假并设置*/
        //获取请假开始时间
        Date start_date = newLeaveInfo.getStart_date();
        //获取请假结束时间
        Date end_date = newLeaveInfo.getEnd_date();
        //获取实际请假天数
        Integer leave_days_actual = DateUtils.getDaysBetweenPlusOne(start_date, end_date);
        //设置
        newLeaveInfo.setLeave_days_actual(leave_days_actual);
        /*计算预计到岗时间并设置*/

        //设置事务状态
        newLeaveInfo.setStatus("使用中");

        //执行主数据更新操作
        int mainLeaveInfoUpdateCode = askForLeaveDao.updateMainResumeWorkInfo(newLeaveInfo);
        //执行备份数据更新操作
        int backupsLeaveInfoUpdateCode = askForLeaveDao.updateBackupResumeWorkInfo(newLeaveInfo);

        if(mainLeaveInfoUpdateCode!=1){
            throw new Exception("销假信息更新失败");
        }
        if(backupsLeaveInfoUpdateCode!=1){
            throw new Exception("销假备份信息更新失败");
        }

        String operateLog = getResumeWorkChangeLog(newLeaveInfo,oldLeaveInfo);
        if(!operateLog.trim().equals("")){
            //有修改的地方才写入日志
            int operateLogOfAskforleaveUpdateCode
                    = askForLeaveDao.addAnOperateLogOfResumeWork(newLeaveInfo.getSerialnumber(),
                    newLeaveInfo.getStart_leave_operator(),"修改",operateLog);
            if(operateLogOfAskforleaveUpdateCode!=1){
                throw new Exception("日志写入失败");
            }
        }

        return 1;
    }

    //修改历史数据
    public int updateAHistoryInfoBySerialnumber(LeaveInfo newLeaveInfo) throws Exception{
        LeaveInfo oldResumeWorkInfo = askForLeaveDao.queryResumeWorkInfoBySerialnumber(newLeaveInfo.getSerialnumber());
        LeaveInfo oldLeavaInfoBackupsInfo = askForLeaveDao.queryALeaveInfoBacupsBySerialnumber(newLeaveInfo.getSerialnumber());

        /*-----修改销假数据-----*/
        newLeaveInfo.setEnd_leave_operator(newLeaveInfo.getStart_leave_operator());
        int updateResumeWorkInfoCode = updateResumeWorkInfo(newLeaveInfo);
        /*-----修改销假数据-----*/

        /*-----修改请假备份数据-----*/
        int updateBackupLeaveInfoCode =  updateLeaveBackupsInfo(newLeaveInfo,"历史请假记录页面");
        System.out.println("updateBackupLeaveInfoCode:"+updateBackupLeaveInfoCode);
        /*-----修改请假备份数据-----*/

        return 1;
    }

    private String getResumeWorkChangeLog(LeaveInfo newLeaveInfo,LeaveInfo oldLeaveInfo){
        StringBuilder stringBuilder = new StringBuilder("");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date newLeaveInfo_end_date = newLeaveInfo.getEnd_date();
        Date oldLeaveInfo_end_date = oldLeaveInfo.getEnd_date();
        if(newLeaveInfo_end_date!=null){
            if(oldLeaveInfo_end_date!=null){
                String oldLeaveInfo_start_date_format = simpleDateFormat.format(oldLeaveInfo_end_date);
                String newLeaveInfo_start_date_format = simpleDateFormat.format(newLeaveInfo_end_date);
                //两个都不为空
                if(!newLeaveInfo_start_date_format.trim().equals(oldLeaveInfo_start_date_format.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将请假开始日期从【"+oldLeaveInfo_start_date_format+"】修改为【"+newLeaveInfo_start_date_format+"】;");
                }
            }else {
                //一个为空，一个不为空
                String newLeaveInfo_start_date_format = simpleDateFormat.format(newLeaveInfo_end_date);
                stringBuilder.append("将请假开始日期从【"+oldLeaveInfo_end_date+"】修改为【"+newLeaveInfo_start_date_format+"】;");
            }
        }
        else {
            if(oldLeaveInfo_end_date!=null){
                //一个为空，一个不为空
                String oldLeaveInfo_start_date_format = simpleDateFormat.format(oldLeaveInfo_end_date);
                stringBuilder.append("将请假开始日期从【"+oldLeaveInfo_start_date_format+"】修改为【"+newLeaveInfo_end_date+"】;");
            }
        }

        String newLeaveInfo_end_leave_remark = newLeaveInfo.getEnd_leave_remark();
        String oldLeaveInfo_end_leave_remark = oldLeaveInfo.getEnd_leave_remark();
        if(newLeaveInfo_end_leave_remark!=null){
            if(oldLeaveInfo_end_leave_remark!=null){
                //两个都不为空
                if(!newLeaveInfo_end_leave_remark.trim().equals(oldLeaveInfo_end_leave_remark.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将请假备注从【"+oldLeaveInfo_end_leave_remark+"】修改为【"+newLeaveInfo_end_leave_remark+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将请假备注从【"+oldLeaveInfo_end_leave_remark+"】修改为【"+newLeaveInfo_end_leave_remark+"】;");
            }
        }
        else {
            if(oldLeaveInfo_end_leave_remark!=null){
                //一个为空，一个不为空
                stringBuilder.append("将请假备注从【"+oldLeaveInfo_end_leave_remark+"】修改为【"+newLeaveInfo_end_leave_remark+"】;");
            }
        }

        String newLeaveInfo_end_leave_operator = newLeaveInfo.getEnd_leave_operator();
        String oldLeaveInfo_end_leave_operator = oldLeaveInfo.getEnd_leave_operator();
        if(newLeaveInfo_end_leave_operator!=null){
            if(oldLeaveInfo_end_leave_remark!=null){
                //两个都不为空
                if(!newLeaveInfo_end_leave_operator.trim().equals(oldLeaveInfo_end_leave_operator.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将销假备注从【"+oldLeaveInfo_end_leave_operator+"】修改为【"+newLeaveInfo_end_leave_operator+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将销假备注从【"+oldLeaveInfo_end_leave_operator+"】修改为【"+newLeaveInfo_end_leave_operator+"】;");
            }
        }
        else {
            if(oldLeaveInfo_end_leave_operator!=null){
                //一个为空，一个不为空
                stringBuilder.append("将销假备注从【"+oldLeaveInfo_end_leave_operator+"】修改为【"+newLeaveInfo_end_leave_operator+"】;");
            }
        }

        Integer newLeaveInfo_leave_days_actual = newLeaveInfo.getLeave_days_actual();
        Integer oldLeaveInfo_leave_days_actual = oldLeaveInfo.getLeave_days_actual();
        if(newLeaveInfo_leave_days_actual!=null){
            if(oldLeaveInfo_leave_days_actual!=null){
                if(newLeaveInfo_leave_days_actual.compareTo(oldLeaveInfo_leave_days_actual)!=0){
                    stringBuilder.append("将实际请假天数从【"+oldLeaveInfo_leave_days_actual+"】修改为【"+newLeaveInfo_leave_days_actual+"】;");
                }
            }else {
                stringBuilder.append("将实际请假天数从【"+oldLeaveInfo_leave_days_actual+"】修改为【"+newLeaveInfo_leave_days_actual+"】;");
            }
        }else {
            if(oldLeaveInfo_leave_days_actual!=null){
                stringBuilder.append("将实际请假天数从【"+oldLeaveInfo_leave_days_actual+"】修改为【"+newLeaveInfo_leave_days_actual+"】;");
            }
        }

        return stringBuilder.toString();
    }

    private String getChangeLog(LeaveInfo newLeaveInfo,LeaveInfo oldLeaveInfo){
        StringBuilder stringBuilder = new StringBuilder("");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String newLeaveInfo_leave_type = newLeaveInfo.getLeave_type();
        String oldLeaveInfo_leave_type = oldLeaveInfo.getLeave_type();
        if(newLeaveInfo_leave_type!=null){
            if(oldLeaveInfo_leave_type!=null){
                //两个都不为空
                if(!newLeaveInfo_leave_type.trim().equals(oldLeaveInfo_leave_type.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将请假类型从【"+oldLeaveInfo_leave_type+"】修改为【"+newLeaveInfo_leave_type+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将请假类型从【"+oldLeaveInfo_leave_type+"】修改为【"+newLeaveInfo_leave_type+"】;");
            }
        }
        else {
            if(oldLeaveInfo_leave_type!=null){
                //一个为空，一个不为空
                stringBuilder.append("将请假类型从【"+oldLeaveInfo_leave_type+"】修改为【"+newLeaveInfo_leave_type+"】;");
            }
        }

        Date newLeaveInfo_start_date = newLeaveInfo.getStart_date();
        Date oldLeaveInfo_start_date = oldLeaveInfo.getStart_date();
        if(newLeaveInfo_start_date!=null){
            if(oldLeaveInfo_start_date!=null){
                String oldLeaveInfo_start_date_format = simpleDateFormat.format(oldLeaveInfo_start_date);
                String newLeaveInfo_start_date_format = simpleDateFormat.format(newLeaveInfo_start_date);
                //两个都不为空
                if(!newLeaveInfo_start_date_format.trim().equals(oldLeaveInfo_start_date_format.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将请假开始日期从【"+oldLeaveInfo_start_date_format+"】修改为【"+newLeaveInfo_start_date_format+"】;");
                }
            }else {
                //一个为空，一个不为空
                String newLeaveInfo_start_date_format = simpleDateFormat.format(newLeaveInfo_start_date);
                stringBuilder.append("将请假开始日期从【"+oldLeaveInfo_start_date+"】修改为【"+newLeaveInfo_start_date_format+"】;");
            }
        }
        else {
            if(oldLeaveInfo_start_date!=null){
                //一个为空，一个不为空
                String oldLeaveInfo_start_date_format = simpleDateFormat.format(oldLeaveInfo_start_date);
                stringBuilder.append("将请假开始日期从【"+oldLeaveInfo_start_date_format+"】修改为【"+newLeaveInfo_start_date+"】;");
            }
        }

        Date newLeaveInfo_end_date_maybe = newLeaveInfo.getEnd_date_maybe();
        Date oldLeaveInfo_end_date_maybe = oldLeaveInfo.getEnd_date_maybe();
        if(newLeaveInfo_end_date_maybe!=null){
            if(oldLeaveInfo_end_date_maybe!=null){
                String newLeaveInfo_end_date_maybe_format = simpleDateFormat.format(newLeaveInfo_end_date_maybe);
                String oldLeaveInfo_end_date_maybe_format = simpleDateFormat.format(oldLeaveInfo_end_date_maybe);
                //两个都不为空
                if(!newLeaveInfo_end_date_maybe_format.trim().equals(oldLeaveInfo_end_date_maybe_format.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将预计到假日期从【"+oldLeaveInfo_end_date_maybe_format+"】修改为【"+newLeaveInfo_end_date_maybe_format+"】;");
                }
            }else {
                //一个为空，一个不为空
                String newLeaveInfo_end_date_maybe_format = simpleDateFormat.format(newLeaveInfo_end_date_maybe);
                stringBuilder.append("将预计到假日期从【"+oldLeaveInfo_end_date_maybe+"】修改为【"+newLeaveInfo_end_date_maybe_format+"】;");
            }
        }
        else {
            if(oldLeaveInfo_end_date_maybe!=null){
                //一个为空，一个不为空
                String oldLeaveInfo_end_date_maybe_format = simpleDateFormat.format(oldLeaveInfo_end_date_maybe);
                stringBuilder.append("将预计到假日期从【"+oldLeaveInfo_end_date_maybe_format+"】修改为【"+newLeaveInfo_end_date_maybe+"】;");
            }
        }

        Date newLeaveInfo_end_date = newLeaveInfo.getEnd_date();
        Date oldLeaveInfo_end_date = oldLeaveInfo.getEnd_date();
        if(newLeaveInfo_end_date!=null){
            if(newLeaveInfo_end_date!=null){
                String newLeaveInfo_end_date_format = simpleDateFormat.format(newLeaveInfo_end_date);
                String oldLeaveInfo_end_date_format = simpleDateFormat.format(oldLeaveInfo_end_date);
                //两个都不为空
                if(!newLeaveInfo_end_date_format.trim().equals(oldLeaveInfo_end_date_format.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将到假日期从【"+oldLeaveInfo_end_date_format+"】修改为【"+newLeaveInfo_end_date_format+"】;");
                }
            }else {
                //一个为空，一个不为空
                String newLeaveInfo_end_date_format = simpleDateFormat.format(newLeaveInfo_end_date);
                stringBuilder.append("将到假日期从【"+newLeaveInfo_end_date+"】修改为【"+newLeaveInfo_end_date_format+"】;");
            }
        }
        else {
            if(newLeaveInfo_end_date!=null){
                //一个为空，一个不为空
                String oldLeaveInfo_end_date_format = simpleDateFormat.format(oldLeaveInfo_end_date);
                stringBuilder.append("将到假日期从【"+oldLeaveInfo_end_date_format+"】修改为【"+newLeaveInfo_end_date+"】;");
            }
        }

        Integer newLeaveInfo_leave_days_projected = newLeaveInfo.getLeave_days_projected();
        Integer oldLeaveInfo_leave_days_projected = oldLeaveInfo.getLeave_days_projected();
        if(newLeaveInfo_leave_days_projected!=null){
            if(oldLeaveInfo_leave_days_projected!=null){
                if(newLeaveInfo_leave_days_projected.compareTo(oldLeaveInfo_leave_days_projected)!=0){
                    stringBuilder.append("将预计请假天数从【"+oldLeaveInfo_leave_days_projected+"】修改为【"+newLeaveInfo_leave_days_projected+"】;");
                }
            }else {
                stringBuilder.append("将预计请假天数从【"+oldLeaveInfo_leave_days_projected+"】修改为【"+newLeaveInfo_leave_days_projected+"】;");
            }
        }else {
            stringBuilder.append("将预计请假天数从【"+oldLeaveInfo_leave_days_projected+"】修改为【"+newLeaveInfo_leave_days_projected+"】;");
        }


        String newLeaveInfo_work_leader = newLeaveInfo.getWork_leader();
        String oldLeaveInfo_work_leader = oldLeaveInfo.getWork_leader();
        if(newLeaveInfo_work_leader!=null){
            if(oldLeaveInfo_work_leader!=null){
                //两个都不为空
                if(!newLeaveInfo_work_leader.trim().equals(oldLeaveInfo_work_leader.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将不在岗期间主持工作领导从【"+oldLeaveInfo_work_leader+"】修改为【"+newLeaveInfo_work_leader+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将不在岗期间主持工作领导从【"+oldLeaveInfo_work_leader+"】修改为【"+newLeaveInfo_work_leader+"】;");
            }
        }
        else {
            if(oldLeaveInfo_work_leader!=null){
                //一个为空，一个不为空
                stringBuilder.append("将不在岗期间主持工作领导从【"+oldLeaveInfo_work_leader+"】修改为【"+newLeaveInfo_work_leader+"】;");
            }
        }

        String newLeaveInfo_approver = newLeaveInfo.getApprover();
        String oldLeaveInfo_approver = oldLeaveInfo.getApprover();
        if(newLeaveInfo_approver!=null){
            if(oldLeaveInfo_approver!=null){
                //两个都不为空
                if(!newLeaveInfo_approver.trim().equals(oldLeaveInfo_approver.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将批准人从【"+oldLeaveInfo_approver+"】修改为【"+newLeaveInfo_approver+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将批准人从【"+oldLeaveInfo_approver+"】修改为【"+newLeaveInfo_approver+"】;");
            }
        }
        else {
            if(oldLeaveInfo_approver!=null){
                //一个为空，一个不为空
                stringBuilder.append("将批准人从【"+oldLeaveInfo_approver+"】修改为【"+newLeaveInfo_approver+"】;");
            }
        }

        String newLeaveInfo_start_leave_remark = newLeaveInfo.getStart_leave_remark();
        String oldLeaveInfo_start_leave_remark = oldLeaveInfo.getStart_leave_remark();
        if(newLeaveInfo_start_leave_remark!=null){
            if(oldLeaveInfo_start_leave_remark!=null){
                //两个都不为空
                if(!newLeaveInfo_start_leave_remark.trim().equals(oldLeaveInfo_start_leave_remark.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将请假备注从【"+oldLeaveInfo_start_leave_remark+"】修改为【"+newLeaveInfo_start_leave_remark+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将请假备注从【"+oldLeaveInfo_start_leave_remark+"】修改为【"+newLeaveInfo_start_leave_remark+"】;");
            }
        }
        else {
            if(oldLeaveInfo_start_leave_remark!=null){
                //一个为空，一个不为空
                stringBuilder.append("将请假备注从【"+oldLeaveInfo_start_leave_remark+"】修改为【"+newLeaveInfo_start_leave_remark+"】;");
            }
        }

        String newLeaveInfo_arrive_location = newLeaveInfo.getArrive_location();
        String oldLeaveInfo_arrive_location = oldLeaveInfo.getArrive_location();
        if(newLeaveInfo_arrive_location!=null){
            if(oldLeaveInfo_arrive_location!=null){
                //两个都不为空
                if(!newLeaveInfo_arrive_location.trim().equals(oldLeaveInfo_arrive_location.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将出发地从【"+oldLeaveInfo_arrive_location+"】修改为【"+newLeaveInfo_arrive_location+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将出发地从【"+oldLeaveInfo_arrive_location+"】修改为【"+newLeaveInfo_arrive_location+"】;");
            }
        }
        else {
            if(oldLeaveInfo_arrive_location!=null){
                //一个为空，一个不为空
                stringBuilder.append("将出发地从【"+oldLeaveInfo_arrive_location+"】修改为【"+newLeaveInfo_arrive_location+"】;");
            }
        }

        String newLeaveInfo_depart_location = newLeaveInfo.getDepart_location();
        String oldLeaveInfo_depart_location = oldLeaveInfo.getDepart_location();
        if(newLeaveInfo_depart_location!=null){
            if(oldLeaveInfo_depart_location!=null){
                //两个都不为空
                if(!newLeaveInfo_depart_location.trim().equals(oldLeaveInfo_depart_location.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将到达地从【"+oldLeaveInfo_depart_location+"】修改为【"+newLeaveInfo_depart_location+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将到达地从【"+oldLeaveInfo_depart_location+"】修改为【"+newLeaveInfo_depart_location+"】;");
            }
        }
        else {
            if(oldLeaveInfo_depart_location!=null){
                //一个为空，一个不为空
                stringBuilder.append("将到达地从【"+oldLeaveInfo_depart_location+"】修改为【"+newLeaveInfo_depart_location+"】;");
            }
        }




        String newLeaveInfo_end_leave_remark = newLeaveInfo.getEnd_leave_remark();
        String oldLeaveInfo_end_leave_remark = oldLeaveInfo.getEnd_leave_remark();
        if(newLeaveInfo_end_leave_remark!=null){
            if(oldLeaveInfo_end_leave_remark!=null){
                //两个都不为空
                if(!newLeaveInfo_end_leave_remark.trim().equals(oldLeaveInfo_end_leave_remark.trim())){
                    //两者内容不同时，增加修改语句
                    stringBuilder.append("将销假备注从【"+oldLeaveInfo_end_leave_remark+"】修改为【"+newLeaveInfo_end_leave_remark+"】;");
                }
            }else {
                //一个为空，一个不为空
                stringBuilder.append("将销假备注从【"+oldLeaveInfo_end_leave_remark+"】修改为【"+newLeaveInfo_end_leave_remark+"】;");
            }
        }
        else {
            if(oldLeaveInfo_end_leave_remark!=null){
                //一个为空，一个不为空
                stringBuilder.append("将销假备注从【"+oldLeaveInfo_end_leave_remark+"】修改为【"+newLeaveInfo_end_leave_remark+"】;");
            }
        }

        Integer newLeaveInfo_leave_days_actual = newLeaveInfo.getLeave_days_actual();
        Integer oldLeaveInfo_leave_days_actual = oldLeaveInfo.getLeave_days_actual();
        if(newLeaveInfo_leave_days_actual!=null){
            if(oldLeaveInfo_leave_days_actual!=null){
                if(newLeaveInfo_leave_days_actual.compareTo(oldLeaveInfo_leave_days_actual)!=0){
                    stringBuilder.append("将实际请假天数从【"+oldLeaveInfo_leave_days_actual+"】修改为【"+newLeaveInfo_leave_days_actual+"】;");
                }
            }else {
                stringBuilder.append("将实际请假天数从【"+oldLeaveInfo_leave_days_actual+"】修改为【"+newLeaveInfo_leave_days_actual+"】;");
            }
        }else {
            if(oldLeaveInfo_leave_days_actual!=null){
                stringBuilder.append("将实际请假天数从【"+oldLeaveInfo_leave_days_actual+"】修改为【"+newLeaveInfo_leave_days_actual+"】;");
            }
        }


        return stringBuilder.toString();
    }

    /*
     * @Description ：按条件查询待销假信息_不分页
     * @Param map
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 16:06
     */
    public List<HashMap<String, Object>> querySomeResumeWorkInfo(Map<String, String[]> map, Integer pageNo, Integer pageSize) throws ParseException {
        List<LeaveInfo> resumeWorkList =  askForLeaveDao.querySomeResumeWorkInfo(map,pageNo,pageSize);
        return formatLeaveInfo(resumeWorkList);
    }

    public List<HashMap<String,Object>> queryPersonInfoByIdRTNMap(int serialnumber) throws ParseException {
        int person_id = askForLeaveDao.queryALeaveInfoBySerialnumber(serialnumber).getPerson_id();
        return personService.queryPersonInfoByIdRTNMap(person_id);
    }
    
    public List<HashMap<String, Object>> querySomeLeaveInfos(Map<String, String[]> map,Integer pageNo, Integer pageSize) throws ParseException {
        List<LeaveInfo> leaveInfoList =  askForLeaveDao.querySomeLeaveInfos(map,pageNo,pageSize);
        return formatLeaveInfo(leaveInfoList);
    }

    
    public List<HashMap<String,Object>> queryAllLeaveInfo(Integer pageNo, Integer pageSize) {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryAllLeaveInfo(pageNo,pageSize);
        return formatLeaveInfo(leaveInfoList);
    }

    
    /*
     * @Description : 处理请假业务
     * @param: leaveInfo
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author tianfeichen
     * @Date 2022/5/15 12:22
     **/
    public Map<String,Object> addLeaveInfo(LeaveInfo leaveInfo)  {
        //计算预计到假时间，并为leaveInfo绑定此项数据
        Date start_date = leaveInfo.getStart_date();
        int leave_days_projected = leaveInfo.getLeave_days_projected();
        Date end_date_maybe = DateUtils.addAndSubtractDays(start_date,leave_days_projected);
        leaveInfo.setEnd_date_maybe(end_date_maybe);
        //设置回执单打印次数
        leaveInfo.setDoes_print(0);
        //生成编号
        int serialnumber = getSerialnumber();
        System.out.println(serialnumber);
        leaveInfo.setSerialnumber(serialnumber);

        //插入待销假表，请假记录表，请假记录操作日志
        int addLeaveInfoDao = askForLeaveDao.addLeaveInfoDao(leaveInfo);
        HashMap<String,Object> hashMap = new HashMap<>();
        if(addLeaveInfoDao==1){
            //插入备份数据表
            int backupsDataOfAskforleave = askForLeaveDao.addABackupsDataOfAskforleave(leaveInfo);
            if(backupsDataOfAskforleave==1){
                //备份成功
                int anOperateLogOfAskforleave
                        = askForLeaveDao.addAnOperateLogOfAskforleave(leaveInfo,"新增"
                ,"成功新增一条待销假数据，新增一条备份数据");

                if(anOperateLogOfAskforleave==1){
                    String sendMSGStatus = null;
                    //发送短信
                    //1.根据发送短信指向（doesSendLeader、doesSendSelf）的代码，给本人和领导发送短信
                    //获取当前发送短信的对象代码
                    int doesSendSelfCode = SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOSELF);
                    int doesSendLeaderCode = SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOLEADER);
                    //1.1判断是否给本人发短信
                    if(doesSendSelfCode == 1){
                        try {
                            sendMsgToSelfBySerialnumberWhenAskForLeave(serialnumber);
                        } catch (Exception e) {
                            hashMap.put("msg",e.getMessage());
                            hashMap.put("code",-3);
                            hashMap.put("leave_info",null);
                        }
                    }else {
                        hashMap.put("msg","操作成功，无需发送短信");
                        //将存入成功的LeaveInfo返回前端
                        hashMap.put("code",1);
                        hashMap.put("leave_info",leaveInfo);
                    }

                    //1.2判断是否给领导发短信
                    if(doesSendLeaderCode == 1){
                        try {
                            sendMsgToLeaderBySerialnumberWhenAskForLeave(serialnumber);
                        } catch (Exception e) {
                            hashMap.put("msg",e.getMessage());
                            hashMap.put("code",-4);
                            hashMap.put("leave_info",null);
                        }
                    }else {
                        hashMap.put("msg","操作成功，无需发送短信");
                        //将存入成功的LeaveInfo返回前端
                        hashMap.put("code",1);
                        hashMap.put("leave_info",leaveInfo);
                    }
                    hashMap.put("msg","操作成功，已发送短信");
                    //将存入成功的LeaveInfo返回前端
                    hashMap.put("code",1);
                    hashMap.put("leave_info",leaveInfo);
                }

            }
            else {
                int anOperateLogOfAskforleave
                        = askForLeaveDao.addAnOperateLogOfAskforleave(leaveInfo,"新增"
                        ,"成功新增一条待销假数据，备份数据添加失败");
                hashMap.put("msg","未备份成功，短信发送失败");
                hashMap.put("code",-2);
                hashMap.put("leave_info",null);
            }

        }
        else {
            askForLeaveDao.addAnOperateLogOfAskforleave(leaveInfo,"新增"
                    ,"待销假数据添加失败，备份数据添加失败");
            hashMap.put("msg","操作失败");
            hashMap.put("code",-1);
            hashMap.put("leave_info",null);
        }
        return hashMap;
    }

    //系统序列号生成方法
    public int getSerialnumber(){
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd"); // 获取时间格式 时间的完整格式为yyyyMMddHHmmss
        String uid_pfix = format.format(new Date()); // 组合流水号前一部分，NO+时间字符串，如：NO20160126
        int size = askForLeaveDao.queryLeaveInfoCountAtOneDay(new Date())+1;
        String newString = String.format("%03d", size);
        String serialNumberStr = uid_pfix + newString;
        return Integer.parseInt(serialNumberStr);
    }

}
