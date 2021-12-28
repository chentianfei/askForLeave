package com.ctf.quartz;

import com.ctf.bean.LeaveInfo;
import com.ctf.dao.AskForLeaveDao;
import com.ctf.dao.PersonDao;
import com.ctf.dao.SystemDataDao;
import com.ctf.service.impl.AskForLeaveServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;


public class SendMsgJob implements Job {
    private SystemDataDao systemDataDao = new SystemDataDao();
    private AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    private PersonDao personDao = new PersonDao();
    private AskForLeaveServiceImpl askForLeaveService = new AskForLeaveServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(SendMsgJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date currentDate = new Date();
        //查询需要提前多少天提醒请假者
        int smsAlertDays = systemDataDao.querySmsAlertDays();
        //查询当期日期前指定天数的待销假信息
        List<LeaveInfo> leaveInfoList = askForLeaveDao.queryResumeWorkInfoByDate(smsAlertDays);
        //遍历待销假信息，获取“提示短信”的内容
        for (LeaveInfo leaveInfo : leaveInfoList){
           askForLeaveService.sendMsgToSelfBySerialnumberForAlert(leaveInfo.getSerialnumber());
        }
    }
}