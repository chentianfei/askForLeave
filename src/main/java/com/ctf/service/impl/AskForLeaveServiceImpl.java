package com.ctf.service.impl;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.LeaveInfoCount;
import com.ctf.bean.Person;
import com.ctf.bean.User;
import com.ctf.dao.*;
import com.ctf.service.AskForLeaveService;
import com.ctf.utils.DateUtils;
import com.ctf.utils.SendMsg;
import com.ctf.utils.WebUtils;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AskForLeaveServiceImpl implements AskForLeaveService {

    private static final String DATEFORMAT_YMD = "yyyy年MM月dd日";
    private static AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    private static PersonDao personDao = new PersonDao();
    private static PersonServiceImpl personService = new PersonServiceImpl();
    private static SystemDataDao systemDataDao = new SystemDataDao();
    private static UserDao userDao = new UserDao();
    private SMSLogDao smsLogDao = new SMSLogDao();

    //销假成功后根据请假流水号给领导发送短信
    private List<SendSmsResponse> sendMsgToLeaderBySerialnumberWhenResumeWork(int serialnumber){
        List<SendSmsResponse> sendSmsResponseList = new ArrayList<>();
        /*
        尊敬的{1}，您好，您单位{2}的{3}已于{4}销假，实际请假天数为{5}天，请知悉！
         * */
        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryHistoryInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取领导列表，并解析领导手机号
        List<Person> leaderList = personDao.queryRelatedLeader(person_id);
        if(leaderList != null){
            if(leaderList.size()!=0){
                for(Person leader : leaderList){
                    //封装短信模板参数数据
                    List<String> templateParamList = new ArrayList<>();
                    templateParamList.add(leader.getName());
                    templateParamList.add(leader.getOffice());
                    templateParamList.add(person.getName());
                    templateParamList.add(new SimpleDateFormat(DATEFORMAT_YMD).format(leaveInfo.getEnd_date()));
                    templateParamList.add(Integer.toString(leaveInfo.getLeave_days_actual()));

                    String[] templateParam = WebUtils.stringListTostringArray(templateParamList);

                    //发送短信，并获取响应对象
                    SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(
                            SendMsg.SENDMSGSDKAPPID, SendMsg.SIGNNAME_ZBXWZZB,
                            SendMsg.TEMPLATEID_TOLEADERWHENRESUMEWORK,
                            new String[]{leader.getPhone()},
                            templateParam
                    );

                    sendSmsResponseList.add(sendSmsResponse);
                }
            }
        }

        return sendSmsResponseList;
    }

    //销假成功后根据请假流水号给本人发送短信
    private SendSmsResponse sendMsgToSelfBySerialnumberWhenResumeWork(int serialnumber){
         /*
        {1}，您好，您于{2}销假成功，实际请假天数为{3}天，请及时到岗！
         * */
        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryHistoryInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取本人手机号
        String[] phoneNumberSet = new String[]{person.getPhone()};

        //封装短信模板参数数据
        List<String> templateParamList = new ArrayList<>();
        templateParamList.add(person.getName());
        templateParamList.add(new SimpleDateFormat(DATEFORMAT_YMD).format(leaveInfo.getEnd_date()));
        templateParamList.add(Integer.toString(leaveInfo.getLeave_days_actual()));

        String[] templateParam = WebUtils.stringListTostringArray(templateParamList);

        //发送短信，并获取响应对象
        SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(SendMsg.SENDMSGSDKAPPID,SendMsg.SIGNNAME_ZBXWZZB,
                SendMsg.TEMPLATEID_TOSELFWHENRESUMEWORK,phoneNumberSet,templateParam);

        return sendSmsResponse;
    }

    //****即将到假时给本人发送提醒短信
    public SendSmsResponse sendMsgToSelfBySerialnumberForAlert(int serialnumber){
        /*
         温馨提示：{1}，您好，您的{2}还有{3}天到期，请按时到岗！
         * */
        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryLeaveInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取本人手机号
        String[] phoneNumberSet = new String[]{person.getPhone()};

        //封装短信模板参数数据
        String[] templateParam = new String[]{
                person.getName(),
                leaveInfo.getLeave_type(),
                Integer.toString(systemDataDao.querySmsAlertDays())
        };

        //发送短信，并获取响应对象
        SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(
                SendMsg.SENDMSGSDKAPPID,
                SendMsg.SIGNNAME_ZBXWZZB,
                SendMsg.TEMPLATEID_TOSELFFORALERT,phoneNumberSet,templateParam);

        return sendSmsResponse;
    }

    //请假审批后根据请假流水号给领导发送短信
    private List<SendSmsResponse> sendMsgToLeaderBySerialnumberWhenAskForLeave(int serialnumber){

        List<SendSmsResponse> sendSmsResponseList = new ArrayList<>();
        /*
         *尊敬的{1}，您好，您单位{2}的{3} {4}申请已通过审核，
         * 请假天数为{5}天，请假时间为{6}到{7}，请知悉！
         * */
        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryLeaveInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取领导列表，并解析领导手机号
        List<Person> leaderList = personDao.queryRelatedLeader(person_id);

        if(leaderList != null){
            if(leaderList.size()!=0){
                for(Person leader : leaderList){
                    //封装短信模板参数数据
                    List<String> templateParamList = new ArrayList<>();
                    templateParamList.add(leader.getName());
                    templateParamList.add(leader.getOffice());
                    templateParamList.add(person.getName());
                    templateParamList.add(leaveInfo.getLeave_type());
                    templateParamList.add(Integer.toString(leaveInfo.getLeave_days_projected()));
                    templateParamList.add(new SimpleDateFormat(DATEFORMAT_YMD).format(leaveInfo.getStart_date()));
                    templateParamList.add(new SimpleDateFormat(DATEFORMAT_YMD).format(leaveInfo.getEnd_date_maybe()));
                    String[] templateParam = WebUtils.stringListTostringArray(templateParamList);

                    //发送短信，并获取响应对象
                   SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(SendMsg.SENDMSGSDKAPPID,
                           SendMsg.SIGNNAME_ZBXWZZB,
                            SendMsg.TEMPLATEID_TOLEADERWHENASKFORLEAVE, new String[]{leader.getPhone()},
                            templateParam);
                   sendSmsResponseList.add(sendSmsResponse);
                }
            }
        }

        return sendSmsResponseList;

    }

    //请假审批后根据请假流水号给本人发送短信
    private SendSmsResponse sendMsgToSelfBySerialnumberWhenAskForLeave(int serialnumber){
        /*
        {1}，您好，您的{2}申请已通过审核，请假天数为{3}天，请假时间为{4}到{5}，请按规定时间归假！
         */

        //根据流水号解析请假信息，返回LeaveInfo对象
        LeaveInfo leaveInfo = askForLeaveDao.queryLeaveInfoBySerialnumber(serialnumber);
        Integer person_id = leaveInfo.getPerson_id();

        //根据人员编号获取该人员Person对象
        Person person = personDao.queryPersonInfoByID(person_id);

        //根据人员编号获取本人手机号
        String[] phoneNumberSet = new String[]{person.getPhone()};

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMAT_YMD);
        //封装短信模板参数数据
        List<String> templateParamList = new ArrayList<>();
        templateParamList.add(person.getName());
        templateParamList.add(leaveInfo.getLeave_type());
        templateParamList.add(Integer.toString(leaveInfo.getLeave_days_projected()));
        templateParamList.add(simpleDateFormat.format(leaveInfo.getStart_date()));
        templateParamList.add(simpleDateFormat.format(leaveInfo.getEnd_date_maybe()));

        String[] templateParams = WebUtils.stringListTostringArray(templateParamList);

        //发送短信，并获取响应对象
        SendSmsResponse sendSmsResponse = SendMsg.sendMsgByPhoneNum(SendMsg.SENDMSGSDKAPPID,
                SendMsg.SIGNNAME_ZBXWZZB,
                SendMsg.TEMPLATEID_TOSELFWHENASKFORLEAVE,phoneNumberSet,templateParams);

        return sendSmsResponse;
    }

    //将leaveinfo对象和person对象合封装成一个map，并加入list，以list形式返回前端
    private List<HashMap<String,Object>> getLeaveInfo(List<LeaveInfo> leaveInfoList) {
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
            hashMap.put("leader", person.getLeader());
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
            hashMap.put("approval_status",leaveInfo.getApproval_status());
            hashMap.put("approval_reason",leaveInfo.getApproval_reason());
            if(leaveInfo.getEnd_date()!=null){
                hashMap.put("end_date",simpleDateFormat.format(leaveInfo.getEnd_date()));
            }
            hashMap.put("end_leave_remark",leaveInfo.getEnd_leave_remark());
            hashMap.put("end_leave_operator",leaveInfo.getEnd_leave_operator());
            hashMap.put("leave_days_actual",leaveInfo.getLeave_days_actual());
            mapList.add(hashMap);
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
        if(person.getLeader()!=null){

            hashMap.put("leader", person.getLeader());
        }else {
            hashMap.put("leader", new ArrayList<>());
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
        hashMap.put("approval_status",leaveInfo.getApproval_status());
        hashMap.put("approval_reason",leaveInfo.getApproval_reason());
        if(leaveInfo.getEnd_date()!=null){
            hashMap.put("end_date",simpleDateFormat.format(leaveInfo.getEnd_date()));
        }
        hashMap.put("end_leave_remark",leaveInfo.getEnd_leave_remark());
        hashMap.put("end_leave_operator",leaveInfo.getEnd_leave_operator());
        hashMap.put("leave_days_actual",leaveInfo.getLeave_days_actual());

        return hashMap;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    /*
     * @Description ：根据流水号删除一条历史记录，并执行相关操作
     * @Param serialnumber
     * @Return ：int
     * @Author: CTF
     * @Date ：2021/12/5 16:21
     */
    public int deleteAHistoryInfo(int serialnumber,String delete_reason,
                                  Date delete_date,String delete_operator) {
        //删除历史记录表history_info表中的数据
        int deleteCode = askForLeaveDao.deleteAHistoryInfoBySerialnumber(serialnumber);
        //修改历史记录备份表history_info_backups对应序列号的数据
        int updateCode = askForLeaveDao.updateHistoryInfoBackupsBySerialnumber(serialnumber,delete_date,delete_operator,delete_reason);
        if(deleteCode==1  && updateCode==1){
            return 1;
        }
        return -1;
    }

    @Override
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

    @Override
    /*
     * @Description ：按条件查询历史请假记录_不分页
     * @Param map
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/5 12:28
     */
    public List<HashMap<String, Object>> querySomeHistoryInfo(Map<String, String[]> map) throws ParseException {
        List<LeaveInfo> historyInfoList =  askForLeaveDao.querySomeHistoryInfo(map);
        return getLeaveInfo(historyInfoList);
    }

    @Override
    /*
     * @Description ：按条件查询历史请假记录分页
     * @Param map
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/5 12:28
     */
    public List<HashMap<String, Object>> querySomeHistoryInfoLimit(Map<String, String[]> map, Integer pageNo, Integer pageSize) throws ParseException {
        List<LeaveInfo> historyInfoList = askForLeaveDao.querySomeHistoryInfoLimit(map,pageNo,pageSize);
        return getLeaveInfo(historyInfoList);
    }

    @Override
    /*
     * @Description ：分页查询库中所有历史请假记录信息
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 21:51
     */
    public List<HashMap<String, Object>> queryALLHistoryInfoLimit(int pageNo, int pageSize, User user) {
        List<LeaveInfo> historyInfoList = askForLeaveDao.queryALLHistoryInfoLimit(pageNo,pageSize,user);
        return getLeaveInfo(historyInfoList);
    }

    @Override
    /*
     * @Description ：查询全部历史请假记录
     * @Param
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 21:50
     */
    public List<HashMap<String, Object>> queryALLHistoryInfo(User user) {
        List<LeaveInfo> historyInfoList = askForLeaveDao.queryALLHistoryInfo(user);
        return getLeaveInfo(historyInfoList);
    }

    @Override
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
    public int resumeWork(String serialnumberSTR, String end_leave_remarkSTR,
                          String end_dateSTR, String end_leave_operator) throws ParseException {
        //1.解析字符串数据
        //流水号
        Integer serialnumber = Integer.parseInt(serialnumberSTR);
        //销假日期
        Date end_date = new SimpleDateFormat("yyyy年MM月dd").parse(end_dateSTR);
        //计算实际请假天数
        LeaveInfo leaveInfo = askForLeaveDao.queryResumeWorkInfoBySerialnumber(serialnumber);
        Date start_date =leaveInfo.getStart_date();
        Integer leaveDaysActual = DateUtils.getDaysBetweenPlusOne(start_date,end_date);

        //2.数据库业务
        //2.1 将该条数据数据插入历史记录表和历史记录备份表中
        int insertSqlCode = askForLeaveDao.insertAHistoryInfo(serialnumber,leaveDaysActual,end_leave_remarkSTR,
                end_date,end_leave_operator);

        if(insertSqlCode == 2){
            //2.1.1 插入成功的情况下删除resume_work表中该条数据
            int deleteSqlCode = askForLeaveDao.deleteAResumeWorkInfoBySerialnumber(serialnumber);
            if(deleteSqlCode != 1){
                // 2.1.1.1 删除失败的情况下返回代码-2
                return -2;
            }
        }else{
            //2.1.2 没有插入成功返回代码数据库操作结果代码
            return insertSqlCode;
        }

        //3.短信发送业务
        //3.1 查询发送对象代码
        int doesSendSelfCode = SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOSELF);
        int doesSendLeaderCode = SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOLEADER);

        if(doesSendLeaderCode == 1){
            //3.1.1 若显示需要给领导发送短信，则调用sendMsgToLeaderBySerialnumberWhenResumeWork(int serialnumber)
            sendMsgToLeaderBySerialnumberWhenResumeWork(serialnumber);
        }

        if(doesSendSelfCode == 1){
            //3.1.2 若显示需要给本人发送短信，则调用sendMsgToSelfBySerialnumberWhenResumeWork(int serialnumber)
            sendMsgToSelfBySerialnumberWhenResumeWork(serialnumber);
        }
        //3.2 完成上述操作后返回代码1
        return 1;
    }

    @Override
    /*
     * @Description ：查询库中所有待销假信息
     * @Param
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 16:05
     */
    public List<HashMap<String, Object>> queryALLResumeWorkInfo() {
        List<LeaveInfo> resumeWorkList = askForLeaveDao.queryALLResumeWorkInfo();
        return getLeaveInfo(resumeWorkList);
    }

    @Override
    /*
     * @Description ：分页查询库中所有待销假信息
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 16:05
     */
    public List<HashMap<String, Object>> queryALLResumeWorkInfoLimit(int pageNo, int pageSize) {
        List<LeaveInfo> resumeWorkList = askForLeaveDao.queryALLResumeWorkInfoLimit(pageNo,pageSize);
        return getLeaveInfo(resumeWorkList);
    }

    @Override
    /*
     * @Description ：按条件查询待销假信息_不分页
     * @Param map
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 16:06
     */
    public List<HashMap<String, Object>> querySomeResumeWorkInfo(Map<String, String[]> map) throws ParseException {
        List<LeaveInfo> resumeWorkList =  askForLeaveDao.querySomeResumeWorkInfo(map);
        return getLeaveInfo(resumeWorkList);
    }

    @Override
    /*
     * @Description ：按条件查询待销假信息_分页
     * @Param map
     * @Param pageNo
     * @Param pageSize
     * @Return ：List<HashMap<String,Object>>
     * @Author: CTF
     * @Date ：2021/12/4 16:06
     */
    public List<HashMap<String, Object>> querySomeResumeWorkInfosLimit(Map<String, String[]> map, Integer pageNo, Integer pageSize) throws ParseException {
        List<LeaveInfo> resumeWorkList = askForLeaveDao.querySomeResumeWorkInfosLimit(map,pageNo,pageSize);
        return getLeaveInfo(resumeWorkList);
    }

    @Override
    public int agreeLeave(int serialnumber) {
        //若同意
        //1.向待销假表中插入数据
        if(askForLeaveDao.insertAResumeWorkInfo(serialnumber) == 1){
            //2.修改请假审批表中的审批状态为同意
            int updateCounts =askForLeaveDao.updateApprovalStatusAgree(serialnumber);
            //3.根据发送短信指向（doesSendLeader、doesSendSelf）的代码，给本人和领导发送短信
            //获取当前发送短信的对象代码
            int doesSendSelfCode = SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOSELF);
            int doesSendLeaderCode = SendMsg.querySendMsgObjCode(SendMsg.DOESSENDMSGTOLEADER);

            //3.1判断是否给本人发短信
            if(doesSendSelfCode == 1){
                sendMsgToSelfBySerialnumberWhenAskForLeave(serialnumber);
                //日志记录
            }

            //3.2判断是否给领导发短信
            if(doesSendLeaderCode == 1){
                sendMsgToLeaderBySerialnumberWhenAskForLeave(serialnumber);
                //日志记录
            }
        }else {
            return -4;
        }

        return 1;
    }

    @Override
    public int notAgreeLeave(int serialnumber,String approval_reason) {
        //若不同意：修改请假审批表中的审批状态为不同意
        return askForLeaveDao.updateApprovalStatusNotAgree(serialnumber,approval_reason);
    }

    @Override
    public List<HashMap<String,Object>> queryPersonInfoByIdRTNMap(int serialnumber) throws ParseException {
        int person_id = askForLeaveDao.queryPersonIdBySerialnumber(serialnumber).intValue();
        return personService.queryPersonInfoByIdRTNMap(person_id);
    }

    @Override
    public List<HashMap<String, Object>> querySomeLeaveInfosLimit(Map<String, String[]> map,
                                                                  Integer pageNo,Integer pageSize) throws ParseException {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.querySomeLeaveInfosLimit(map,pageNo,pageSize);
        return getLeaveInfo(leaveInfoList);
    }

    @Override
    public List<HashMap<String, Object>> querySomeLeaveInfos(Map<String, String[]> map) throws ParseException {
        List<LeaveInfo> leaveInfoList =  askForLeaveDao.querySomeLeaveInfos(map);
        return getLeaveInfo(leaveInfoList);
    }

    @Override
    public List<HashMap<String, Object>> queryAllLeaveInfoLimit(int pageNo, int pageSize) {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryAllLeaveInfoLimit(pageNo,pageSize);
        return getLeaveInfo(leaveInfoList);
    }

    @Override
    public List<HashMap<String,Object>> queryAllLeaveInfo() {
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryAllLeaveInfo();
        return getLeaveInfo(leaveInfoList);
    }

    @Override
    public Integer addLeaveInfo(LeaveInfo leaveInfo) {
        //计算预计到假时间，并为leaveInfo绑定此项数据
        Date start_date = leaveInfo.getStart_date();
        int leave_days_projected = leaveInfo.getLeave_days_projected();
        Date end_date_maybe = DateUtils.addAndSubtractDays(start_date,leave_days_projected);

        leaveInfo.setEnd_date_maybe(end_date_maybe);

        leaveInfo.setApproval_status("待审批");
        leaveInfo.setApproval_reason("");

        return askForLeaveDao.addLeaveInfoDao(leaveInfo);
    }

}