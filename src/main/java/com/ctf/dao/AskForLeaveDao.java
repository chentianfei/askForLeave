package com.ctf.dao;

import com.ctf.bean.*;
import com.ctf.utils.DateUtils;
import com.ctf.utils.SendMsg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AskForLeaveDao extends BaseDao {
    private PersonDao personDao = new PersonDao();
    private UserDao userDao = new UserDao();

    //插入请假记录
    public int addLeaveInfoDao(LeaveInfo leaveInfo){
        String mainSQL = "insert into approval(serialnumber,`name`,office,phone,person_id," +
                "leave_type,start_date,leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator,does_print) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(leaveInfo.getSerialnumber());
        parmas.add(leaveInfo.getName());
        parmas.add(leaveInfo.getOffice());
        parmas.add(leaveInfo.getPhone());
        parmas.add(leaveInfo.getPerson_id());
        parmas.add(leaveInfo.getLeave_type());
        parmas.add(leaveInfo.getStart_date());
        parmas.add(leaveInfo.getLeave_days_projected());
        parmas.add(leaveInfo.getWork_leader());
        parmas.add(leaveInfo.getLeave_reason());
        parmas.add(leaveInfo.getApprover());
        parmas.add(leaveInfo.getDepart_location());
        parmas.add(leaveInfo.getArrive_location());
        parmas.add(leaveInfo.getStart_leave_remark());
        parmas.add(leaveInfo.getEnd_date_maybe());
        parmas.add(leaveInfo.getStart_leave_operator());
        parmas.add(leaveInfo.getDoes_print());

        //插入待销假表
        return update(mainSQL,parmas.toArray());
    }

    //新增请假备份数据
    public int addABackupsDataOfAskforleave(LeaveInfo leaveInfo){
        //插入备份表
        String backupsSQL = "insert into approval_backups(serialnumber,name,office,phone,person_id," +
                "leave_type,start_date,leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator,does_print,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(leaveInfo.getSerialnumber());
        parmas.add(leaveInfo.getName());
        parmas.add(leaveInfo.getOffice());
        parmas.add(leaveInfo.getPhone());
        parmas.add(leaveInfo.getPerson_id());
        parmas.add(leaveInfo.getLeave_type());
        parmas.add(leaveInfo.getStart_date());
        parmas.add(leaveInfo.getLeave_days_projected());
        parmas.add(leaveInfo.getWork_leader());
        parmas.add(leaveInfo.getLeave_reason());
        parmas.add(leaveInfo.getApprover());
        parmas.add(leaveInfo.getDepart_location());
        parmas.add(leaveInfo.getArrive_location());
        parmas.add(leaveInfo.getStart_leave_remark());
        parmas.add(leaveInfo.getEnd_date_maybe());
        parmas.add(leaveInfo.getStart_leave_operator());
        parmas.add(leaveInfo.getDoes_print());
        parmas.add("使用中");

        int backupsOperate = update(backupsSQL,parmas.toArray());

        return backupsOperate;
    }

    //新增请假操作日志
    public int addAnOperateLogOfAskforleave(LeaveInfo leaveInfo,String operate_type,String operate_log){
        //插入操作日志表
        String logSQL = "insert into approval_operate_log(serialnumber,operator,operate_date,operate_type" +
                ",operate_log,id) values(?,?,?,?,?,null)";
        List<Object> logParmas = new ArrayList<Object>();
        logParmas.add(leaveInfo.getSerialnumber());
        logParmas.add(leaveInfo.getStart_leave_operator());
        logParmas.add(new Date());
        logParmas.add(operate_type);
        logParmas.add(operate_log);
        int operateLog = update(logSQL,logParmas.toArray());
        return operateLog;
    }

    //根据流水号删除一条销假记录主表数据
    public int deleteALeaveInfoBySerialnumber(int serialnumber){
        String sql = "delete from approval where serialnumber = ?";
        return update(sql,serialnumber);
    }

    //查询所有请假记录
    public List<LeaveInfo> queryAllLeaveInfo(Integer pageNo, Integer pageSize) {
        String sql_str = "SELECT * FROM approval order by serialnumber desc";
        StringBuilder sql = new StringBuilder(sql_str);
        List<Object> params = new ArrayList<>();

        //判断是否分页
        if(pageNo!=null && pageSize!=null){
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            sql.append(" limit ?,?");
            params.add(start);
            params.add(end);
        }


        return queryForList(LeaveInfo.class,sql.toString(),params.toArray());
    }

    //修改待销假主表中请假数据
    public int updateBackupLeaveInfo(LeaveInfo newLeaveInfo){
        String sql = "update approval_backups set leave_type=?,start_date=?,leave_days_projected=?,work_leader=?,leave_reason=?," +
                "approver=?,depart_location=?,arrive_location=?,start_leave_remark=?,end_date_maybe=?," +
                "start_leave_operator=?,does_print=?,status=? " +
                "where serialnumber=?";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(newLeaveInfo.getLeave_type());
        parmas.add(newLeaveInfo.getStart_date());
        parmas.add(newLeaveInfo.getLeave_days_projected());
        parmas.add(newLeaveInfo.getWork_leader());
        parmas.add(newLeaveInfo.getLeave_reason());
        parmas.add(newLeaveInfo.getApprover());
        parmas.add(newLeaveInfo.getDepart_location());
        parmas.add(newLeaveInfo.getArrive_location());
        parmas.add(newLeaveInfo.getStart_leave_remark());
        parmas.add(newLeaveInfo.getEnd_date_maybe());
        parmas.add(newLeaveInfo.getStart_leave_operator());
        parmas.add(newLeaveInfo.getDoes_print());
        parmas.add(newLeaveInfo.getStatus());
        parmas.add(newLeaveInfo.getSerialnumber());

        return update(sql,parmas.toArray());
    }

    //修改待销假备份表中请假数据
    public int updateMainLeaveInfo(LeaveInfo newLeaveInfo){
        String sql = "update approval set leave_type=?,start_date=?,leave_days_projected=?,work_leader=?,leave_reason=?," +
                "approver=?,depart_location=?,arrive_location=?,start_leave_remark=?,end_date_maybe=?," +
                "start_leave_operator=?,does_print=? " +
                "where serialnumber=?";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(newLeaveInfo.getLeave_type());
        parmas.add(newLeaveInfo.getStart_date());
        parmas.add(newLeaveInfo.getLeave_days_projected());
        parmas.add(newLeaveInfo.getWork_leader());
        parmas.add(newLeaveInfo.getLeave_reason());
        parmas.add(newLeaveInfo.getApprover());
        parmas.add(newLeaveInfo.getDepart_location());
        parmas.add(newLeaveInfo.getArrive_location());
        parmas.add(newLeaveInfo.getStart_leave_remark());
        parmas.add(newLeaveInfo.getEnd_date_maybe());
        parmas.add(newLeaveInfo.getStart_leave_operator());
        parmas.add(newLeaveInfo.getDoes_print());
        parmas.add(newLeaveInfo.getSerialnumber());

        return update(sql,parmas.toArray());
    }

    //根据序列号查询一条请假数据
    public LeaveInfo queryALeaveInfoBySerialnumber(Integer serialnumber){
        String sql = "SELECT * FROM approval where serialnumber = ?";
        return queryForOne(LeaveInfo.class,sql,serialnumber);
    }

    //根据序列号查询一条请假备份数据
    public LeaveInfo queryALeaveInfoBacupsBySerialnumber(Integer serialnumber){
        String sql = "SELECT * FROM approval_backups where serialnumber = ?";
        return queryForOne(LeaveInfo.class,sql,serialnumber);
    }

    //查询指定日期请假数量（用于生成编号）
    public int queryLeaveInfoCountAtOneDay(Date date) {

        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        //当前日期的前一天
        String yesterday = simpleDateFormat.format(DateUtils.addAndSubtractDays(date, -2));
        //当前日期的后一天
        String tomorrow = simpleDateFormat.format(DateUtils.addAndSubtractDays(date, 2));

        String sql = "SELECT * FROM approval_operate_log where operate_type='新增' " +
                "and (operate_date>'"+yesterday+" 23:59:59' and operate_date<'"+tomorrow+" 00:00:00')";

        return queryForList(LeaveInfo.class,sql).size();
    }

    //按条件查询请假记录（搜索请假审核用）
    public List<LeaveInfo> querySomeLeaveInfos(Map<String, String[]> map,Integer pageNo, Integer pageSize) throws ParseException {
        /*根据人员基本信息查询出符合条件的person_id
        在approval表中查询符合这些person_id的leave_info
        在这些已查询出来的leave_info中根据请假信息过滤有用信息并作分页显示
        其中name作模糊查询
        */

        //String类型的数据，若前端有数据填写处，但是用户可能没填写数据，则该数据在trim后isEmpty == true，或该数据==""
        //String类型的数据，若前端没有数据填写处，则该数据== null
        //非String类型的数据，无论前端有无数据填写处，只要没有数据传过来，都是==null

        //遍历并解析map数据
        //初始化日期数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date_maybe_max = null;
        Date start_date_min = null;
        Date start_date_max = null;
        Date end_date_maybe_min = null;

        //初始化人员基本信息数据
        StringBuilder personInfoSQL = new StringBuilder("select * from person_info where 1=1 ");
        String name;
        String nativePlace;
        String phone;
        String level;
        String area_class;
        String office;
        List<Object> personInfoSQLparmas = new ArrayList<Object>();
        //获取符合条件的人员id：person_id
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //姓名
                case "name":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and name like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //籍贯
                case "nativePlace":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nativePlace like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //工作单位
                case "office":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and office = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //类区
                case "area_class":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and area_class = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //职级
                case "level":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and level = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //联系电话
                case "phone":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and phone = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
            }
        }

        //根据人员基本信息获取符合条件的人员id集合：personIdList
        List<Person> personList = queryForList(Person.class, personInfoSQL.toString(), personInfoSQLparmas.toArray());
        List<Integer> personIdList = new ArrayList<>();
        for(Person person : personList){
            personIdList.add(person.getPerson_id());
        }

        //执行请假信息查询
        StringBuilder leaveInfoSQL = new StringBuilder("select * from approval where 1=1 ");
        //用于保存可变参数
        List<Object> leaveInfoSQLparmas = new ArrayList<Object>();
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //人员编号:in(?)
                //请假起始地
                case "depart_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and depart_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假到达地
                case "arrive_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and arrive_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假批准者
                case "approver":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and approver like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假类型
                case "leave_type":
                    if(!m.getValue()[0].trim().isEmpty()){
                        String[] leave_typeInfos = m.getValue()[0].trim().split(",");
                        StringBuilder leave_type = new StringBuilder();
                        int index = 0;
                        for(String leave_typeInfo:leave_typeInfos){
                            if(index == leave_typeInfos.length - 1){
                                leave_type.append("'"+leave_typeInfo+"'");
                            }else {
                                leave_type.append("'"+leave_typeInfo+"',");
                            }
                            index++;
                        }
                        leaveInfoSQL.append(" and leave_type in("+leave_type+")");
                    }
                    break;
                //请假预计到岗时间最大值
                case "end_date_maybe_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_maybe_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假预计到岗时间最小值
                case "end_date_maybe_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_maybe_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最大值
                case "start_date_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最小值
                case "start_date_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
            }

        }

        //处理预计到岗时间
        if(end_date_maybe_max == null && end_date_maybe_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and end_date_maybe >= ?");
            leaveInfoSQLparmas.add(end_date_maybe_min);
        }else if(end_date_maybe_max != null && end_date_maybe_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and end_date_maybe <= ?");
            leaveInfoSQLparmas.add(end_date_maybe_max);
        }else if(end_date_maybe_max != null && end_date_maybe_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (end_date_maybe between ? and ?)");
            leaveInfoSQLparmas.add(end_date_maybe_min);
            leaveInfoSQLparmas.add(end_date_maybe_max);
        }

        //处理请假起始日期到岗时间
        if(start_date_max == null && start_date_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and start_date >= ?");
            leaveInfoSQLparmas.add(start_date_min);
        }else if(start_date_max != null && start_date_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and start_date <= ?");
            leaveInfoSQLparmas.add(start_date_max);
        }else if(start_date_max != null && start_date_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (start_date between ? and ?)");
            leaveInfoSQLparmas.add(start_date_min);
            leaveInfoSQLparmas.add(start_date_max);
        }

        //处理人员编号
        if(personIdList.size() != 0){
            //有人员的信息筛选过程，即选择了人员的基本信息
            StringBuilder person_ids = new StringBuilder();
            int index = 0;
            for(Object o : personIdList){
                if(index == personIdList.size() - 1){
                    person_ids.append("'"+o+"'");
                }else {
                    person_ids.append("'"+o+"',");
                }
                index++;
            }
            leaveInfoSQL.append(" and person_id in("+person_ids+")");
        }else if(leaveInfoSQLparmas.size() == 0){
            leaveInfoSQL.append(" and person_id = -1 ");
        }

        //设置排序规则
        leaveInfoSQL.append(" order by serialnumber desc");

        //判断是否分页
        if(pageNo!=null && pageSize!=null){
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            leaveInfoSQL.append(" limit ?,?");
            leaveInfoSQLparmas.add(start);
            leaveInfoSQLparmas.add(end);
        }

        return queryForList(LeaveInfo.class,leaveInfoSQL.toString(),leaveInfoSQLparmas.toArray());
    }

    //查询所有待销假记录（展示待销假信息使用）
    public List<LeaveInfo> queryALLResumeWorkInfo(Integer pageNo, Integer pageSize) {
        String sql = "SELECT * FROM approval order by end_date_maybe desc";
        StringBuilder stringBuilder = new StringBuilder(sql);
        ArrayList<Object> params = new ArrayList<>();
        if(pageNo!=null && pageSize!=null){
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;
            stringBuilder.append(" limit ?,?");
            params.add(start);
            params.add(end);
        }
        return queryForList(LeaveInfo.class,stringBuilder.toString(),params.toArray());
    }

    //按条件查询待销假记录_统计数量用（搜索待销假信息审核用）
    public List<LeaveInfo> querySomeResumeWorkInfo(Map<String, String[]> map, Integer pageNo, Integer pageSize) throws ParseException {
        /*根据人员基本信息查询出符合条件的person_id
        在approval表中查询符合这些person_id的leave_info
        在这些已查询出来的leave_info中根据请假信息过滤有用信息并作分页显示
        其中name作模糊查询
        */

        //String类型的数据，若前端有数据填写处，但是用户可能没填写数据，则该数据在trim后isEmpty == true，或该数据==""
        //String类型的数据，若前端没有数据填写处，则该数据== null
        //非String类型的数据，无论前端有无数据填写处，只要没有数据传过来，都是==null

        //遍历并解析map数据
        //初始化日期数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date_maybe_max = null;
        Date start_date_min = null;
        Date start_date_max = null;
        Date end_date_maybe_min = null;

        //初始化人员基本信息数据
        StringBuilder personInfoSQL = new StringBuilder("select * from person_info where 1=1 ");
        String name;
        String nativePlace;
        String phone;
        String level;
        String area_class;
        String office;
        List<Object> personInfoSQLparmas = new ArrayList<Object>();
        //获取符合条件的人员id：person_id
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //姓名
                case "name":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and name like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //籍贯
                case "nativePlace":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nativePlace like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //工作单位
                case "office":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and office = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //类区
                case "area_class":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and area_class = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //职级
                case "level":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and level = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //联系电话
                case "phone":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and phone = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
            }
        }

        //根据人员基本信息获取符合条件的人员id集合：personIdList
        List<Person> personList = queryForList(Person.class, personInfoSQL.toString(), personInfoSQLparmas.toArray());
        //根据人员基本信息获取符合条件的人员id集合：personIdList
        List<Integer> personIdList = new ArrayList<>();
        for(Person person : personList){
            personIdList.add(person.getPerson_id());
        }

        //执行请假信息查询
        StringBuilder leaveInfoSQL = new StringBuilder("select * from approval where 1=1 ");
        //用于保存可变参数
        List<Object> leaveInfoSQLparmas = new ArrayList<Object>();
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //人员编号:in(?)
                //请假起始地
                case "depart_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and depart_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假到达地
                case "arrive_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and arrive_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假批准者
                case "approver":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and approver like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假类型
                case "leave_type":
                    if(!m.getValue()[0].trim().isEmpty()){
                        String[] leave_typeInfos = m.getValue()[0].trim().split(",");
                        StringBuilder leave_type = new StringBuilder();
                        int index = 0;
                        for(String leave_typeInfo:leave_typeInfos){
                            if(index == leave_typeInfos.length - 1){
                                leave_type.append("'"+leave_typeInfo+"'");
                            }else {
                                leave_type.append("'"+leave_typeInfo+"',");
                            }
                            index++;
                        }
                        leaveInfoSQL.append(" and leave_type in("+leave_type+")");
                    }
                    break;
                //请假预计到岗时间最大值
                case "end_date_maybe_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_maybe_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假预计到岗时间最小值
                case "end_date_maybe_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_maybe_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最大值
                case "start_date_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最小值
                case "start_date_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
            }

        }

        //处理预计到岗时间
        if(end_date_maybe_max == null && end_date_maybe_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and end_date_maybe >= ?");
            leaveInfoSQLparmas.add(end_date_maybe_min);
        }else if(end_date_maybe_max != null && end_date_maybe_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and end_date_maybe <= ?");
            leaveInfoSQLparmas.add(end_date_maybe_max);
        }else if(end_date_maybe_max != null && end_date_maybe_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (end_date_maybe between ? and ?)");
            leaveInfoSQLparmas.add(end_date_maybe_min);
            leaveInfoSQLparmas.add(end_date_maybe_max);
        }

        //处理请假起始日期到岗时间
        if(start_date_max == null && start_date_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and start_date >= ?");
            leaveInfoSQLparmas.add(start_date_min);
        }else if(start_date_max != null && start_date_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and start_date <= ?");
            leaveInfoSQLparmas.add(start_date_max);
        }else if(start_date_max != null && start_date_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (start_date between ? and ?)");
            leaveInfoSQLparmas.add(start_date_min);
            leaveInfoSQLparmas.add(start_date_max);
        }

        //处理人员编号
        if(personIdList.size() != 0){
            //有人员的信息筛选过程，即选择了人员的基本信息
            StringBuilder person_ids = new StringBuilder();
            int index = 0;
            for(Object o : personIdList){
                if(index == personIdList.size() - 1){
                    person_ids.append("'"+o+"'");
                }else {
                    person_ids.append("'"+o+"',");
                }
                index++;
            }
            leaveInfoSQL.append(" and person_id in("+person_ids+")");
        }else if(leaveInfoSQLparmas.size() == 0){
            leaveInfoSQL.append(" and person_id = -1 ");
        }

        leaveInfoSQL.append(" order by end_date_maybe desc");

        //判断是否分页
        if(pageNo!=null && pageSize!=null){
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            leaveInfoSQL.append(" limit ?,?");
            leaveInfoSQLparmas.add(start);
            leaveInfoSQLparmas.add(end);
        }

        return queryForList(LeaveInfo.class,leaveInfoSQL.toString(),leaveInfoSQLparmas.toArray());
    }

    //查询今日应到假人员信息
    public List<LeaveInfo> queryCurrentEOLPerson(Integer pageNo, Integer pageSize) {
        String sql_str = "SELECT * FROM approval where end_date_maybe = ? order by serialnumber desc ";
        StringBuilder sql = new StringBuilder(sql_str);
        List<Object> params = new ArrayList<>();

        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        String end_date_maybe = simpleDateFormat.format(new Date());
        params.add(end_date_maybe);

        //判断是否分页
        if(pageNo!=null && pageSize!=null){
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            sql.append(" limit ?,?");
            params.add(start);
            params.add(end);
        }


        return queryForList(LeaveInfo.class,sql.toString(),params.toArray());
    }

    //查询所有到假未到岗人员
    public List<LeaveInfo> queryAllCurrentEOLPerson(Integer pageNo,Integer pageSize) {
        //获取当前时间
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        StringBuilder sql = new StringBuilder("select * from approval where end_date_maybe < '"+ date +
                "' order by serialnumber desc");
        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        //判断是否需要分页
        if(pageNo!=null && pageSize!=null){
            //分页查询
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            sql.append( " limit ?,?");
            parmas.add(start);
            parmas.add(end);
        }

        return queryForList(LeaveInfo.class,sql.toString(),parmas.toArray());
    }

    //按条件查询今日应到假人员信息
    public List<LeaveInfo> querySomeCurrentEOLPerson(Map<String, String[]> map,Integer pageNo, Integer pageSize) throws ParseException {
        //有人的属性时，先查询并获取人员编号集合，人员编号整体作为查询的条件之一
        //没有人的属性时，直接根据请假信息查询

        //查询前设置
        //本方法日期转换格式
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        //初始化日期数据
        Date start_date_min = null;
        Date start_date_max = null;
        /*----------------------------------------------------------------------------------------*/
        //查询请假信息的基础语句
        StringBuilder leaveInfoSQL = new StringBuilder("select * from approval where end_date_maybe = ? ");
        //用于保存可变参数
        List<Object> leaveInfoSQLparmas = new ArrayList<Object>();
        //设置初始参数：以当日为基本查询条件
        String end_date_maybe = simpleDateFormat.format(new Date());
        leaveInfoSQLparmas.add(end_date_maybe);

        //查询人员信息的基础语句
        StringBuilder personInfoSQL = new StringBuilder("select * from person_info where 1=1 ");
        String name;
        String nativePlace;
        String phone;
        String level;
        String area_class;
        String office;
        List<Object> personInfoSQLparmas = new ArrayList<Object>();
        /*----------------------------------------------------------------------------------------*/
        //遍历并解析map数据
        //1.获取人的信息
        //获取符合条件的人员id：person_id
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //姓名
                case "name":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and name like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //籍贯
                case "nativePlace":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nativePlace like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //工作单位
                case "office":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and office = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //类区
                case "area_class":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and area_class = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //职级
                case "level":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and level = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //联系电话
                case "phone":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and phone = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
            }
        }
        //根据人员基本信息获取符合条件的人员id集合：personIdList
        List<Person> personList = queryForList(Person.class, personInfoSQL.toString(), personInfoSQLparmas.toArray());
        List<Integer> personIdList = new ArrayList<>();
        for(Person person : personList){
            personIdList.add(person.getPerson_id());
        }

        //2.获取请假信息
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //人员编号:in(?)
                //请假起始地
                case "depart_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and depart_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假到达地
                case "arrive_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and arrive_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假批准者
                case "approver":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and approver like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假类型
                case "leave_type":
                    if(!m.getValue()[0].trim().isEmpty()){
                        String[] leave_typeInfos = m.getValue()[0].trim().split(",");
                        StringBuilder leave_type = new StringBuilder();
                        int index = 0;
                        for(String leave_typeInfo:leave_typeInfos){
                            if(index == leave_typeInfos.length - 1){
                                leave_type.append("'"+leave_typeInfo+"'");
                            }else {
                                leave_type.append("'"+leave_typeInfo+"',");
                            }
                            index++;
                        }
                        leaveInfoSQL.append(" and leave_type in("+leave_type+")");
                    }
                    break;
                //请假开始时间最大值
                case "start_date_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最小值
                case "start_date_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
            }

        }
        //处理请假起始日期
        if(start_date_max == null && start_date_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and start_date >= ?");
            leaveInfoSQLparmas.add(start_date_min);
        }
        else if(start_date_max != null && start_date_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and start_date <= ?");
            leaveInfoSQLparmas.add(start_date_max);
        }
        else if(start_date_max != null && start_date_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (start_date between ? and ?)");
            leaveInfoSQLparmas.add(start_date_min);
            leaveInfoSQLparmas.add(start_date_max);
        }
        //无需处理预计到岗时间，预计到岗时间固定为当天

        //处理人员编号
        if(personIdList.size() != 0){
            //有人员的信息筛选过程，即选择了人员的基本信息
            StringBuilder person_ids = new StringBuilder();
            int index = 0;
            for(Object o : personIdList){
                if(index == personIdList.size() - 1){
                    person_ids.append("'"+o+"'");
                }else {
                    person_ids.append("'"+o+"',");
                }
                index++;
            }
            leaveInfoSQL.append(" and person_id in("+person_ids+")");
        }
        else{
            leaveInfoSQL.append(" and person_id = -1 ");
        }

        //设置排序规则
        leaveInfoSQL.append(" order by end_date_maybe asc ");

        //判断是否分页
        if(pageNo!=null && pageSize!=null){
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            leaveInfoSQL.append(" limit ?,?");
            leaveInfoSQLparmas.add(start);
            leaveInfoSQLparmas.add(end);
        }

        return queryForList(LeaveInfo.class,
                leaveInfoSQL.toString(),
                leaveInfoSQLparmas.toArray());
    }

    //按条件查询所有到假未到岗人员
    public List<LeaveInfo> querySomeAllCurrentEOLPerson(Map<String, String[]> map,Integer pageNo,Integer pageSize) throws ParseException {
        //有人的属性时，先查询并获取人员编号集合，人员编号整体作为查询的条件之一
        //没有人的属性时，直接根据请假信息查询

        //查询前设置
        //本方法日期转换格式
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        //初始化日期数据
        Date start_date_min = null;
        Date start_date_max = null;
        Date end_date_maybe_min = null;
        Date end_date_maybe_max = null;
        /*----------------------------------------------------------------------------------------*/
        //查询请假信息的基础语句
        StringBuilder leaveInfoSQL = new StringBuilder("select * from approval " +
                "where end_date_maybe < ? ");

        //用于保存可变参数
        List<Object> leaveInfoSQLparmas = new ArrayList<Object>();
        //设置初始参数：以当日为基本查询条件
        String end_date_maybe = simpleDateFormat.format(new Date());
        leaveInfoSQLparmas.add(end_date_maybe);

        //查询人员信息的基础语句
        StringBuilder personInfoSQL = new StringBuilder("select * from person_info where 1=1 ");
        String name;
        String nativePlace;
        String phone;
        String level;
        String area_class;
        String office;
        List<Object> personInfoSQLparmas = new ArrayList<Object>();
        /*----------------------------------------------------------------------------------------*/
        //遍历并解析map数据
        //1.获取人的信息
        //获取符合条件的人员id：person_id
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //姓名
                case "name":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and name like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //籍贯
                case "nativePlace":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nativePlace like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //工作单位
                case "office":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and office = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //类区
                case "area_class":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and area_class = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //职级
                case "level":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and level = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //联系电话
                case "phone":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and phone = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
            }
        }
        //根据人员基本信息获取符合条件的人员id集合：personIdList
        List<Person> personList = queryForList(Person.class, personInfoSQL.toString(), personInfoSQLparmas.toArray());
        List<Integer> personIdList = new ArrayList<>();
        for(Person person : personList){
            personIdList.add(person.getPerson_id());
        }

        //2.获取请假信息
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //人员编号:in(?)
                //请假起始地
                case "depart_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and depart_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假到达地
                case "arrive_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and arrive_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假批准者
                case "approver":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and approver like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假类型
                case "leave_type":
                    if(!m.getValue()[0].trim().isEmpty()){
                        String[] leave_typeInfos = m.getValue()[0].trim().split(",");
                        StringBuilder leave_type = new StringBuilder();
                        int index = 0;
                        for(String leave_typeInfo:leave_typeInfos){
                            if(index == leave_typeInfos.length - 1){
                                leave_type.append("'"+leave_typeInfo+"'");
                            }else {
                                leave_type.append("'"+leave_typeInfo+"',");
                            }
                            index++;
                        }
                        leaveInfoSQL.append(" and leave_type in("+leave_type+")");
                    }
                    break;
                //请假预计到岗时间最大值
                case "end_date_maybe_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_maybe_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假预计到岗时间最小值
                case "end_date_maybe_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_maybe_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最大值
                case "start_date_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最小值
                case "start_date_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
            }

        }
        //处理请假起始日期
        if(start_date_max == null && start_date_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and start_date >= ?");
            leaveInfoSQLparmas.add(start_date_min);
        }
        else if(start_date_max != null && start_date_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and start_date <= ?");
            leaveInfoSQLparmas.add(start_date_max);
        }
        else if(start_date_max != null && start_date_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (start_date between ? and ?)");
            leaveInfoSQLparmas.add(start_date_min);
            leaveInfoSQLparmas.add(start_date_max);
        }
        //处理预计到岗时间
        if(end_date_maybe_max == null && end_date_maybe_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and end_date_maybe >= ?");
            leaveInfoSQLparmas.add(end_date_maybe_min);
        }
        else if(end_date_maybe_max != null && end_date_maybe_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and end_date_maybe <= ?");
            leaveInfoSQLparmas.add(end_date_maybe_max);
        }
        else if(end_date_maybe_max != null && end_date_maybe_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (end_date_maybe between ? and ?)");
            leaveInfoSQLparmas.add(end_date_maybe_min);
            leaveInfoSQLparmas.add(end_date_maybe_max);
        }

        //处理人员编号
        if(personIdList.size() != 0){
            //有人员的信息筛选过程，即选择了人员的基本信息
            StringBuilder person_ids = new StringBuilder();
            int index = 0;
            for(Object o : personIdList){
                if(index == personIdList.size() - 1){
                    person_ids.append("'"+o+"'");
                }else {
                    person_ids.append("'"+o+"',");
                }
                index++;
            }
            leaveInfoSQL.append(" and person_id in("+person_ids+")");
        }
        else{
            leaveInfoSQL.append(" and person_id = -1 ");
        }

        //设置排序规则
        leaveInfoSQL.append(" order by end_date_maybe asc ");

        //判断是否分页
        if(pageNo!=null && pageSize!=null){
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            leaveInfoSQL.append(" limit ?,?");
            leaveInfoSQLparmas.add(start);
            leaveInfoSQLparmas.add(end);
        }

        return queryForList(LeaveInfo.class,
                leaveInfoSQL.toString(),
                leaveInfoSQLparmas.toArray());
    }

    //插入一条已销假数据
    public int insertAResumeWorkInfo(Integer serialnumber,
                                     Integer person_id,
                                     Integer leaveDaysActual,
                                     String end_leave_remarkSTR,
                                     Date end_date,
                                     String end_leave_operator){

        String  insertAResumeWorkInfosql = "insert into resume_work(serialnumber,person_id,end_date," +
                "end_leave_remark,end_leave_operator,leave_days_actual) values(?,?,?,?,?,?)";

        int code = update(insertAResumeWorkInfosql,
                serialnumber,
                person_id,
                end_date,
                end_leave_remarkSTR,
                end_leave_operator,
                leaveDaysActual);

        return code;
    }

    //插入一条已销假数据备份数据
    public int insertAResumeWorkBackupInfo(Integer serialnumber,
                                           Integer person_id,
                                           Integer leaveDaysActual,
                                           String end_leave_remarkSTR,
                                           Date end_date,
                                           String end_leave_operator){

        String  insertAResumeWorkBackupInfosql = "insert into resume_work_backups(serialnumber,person_id,end_date," +
                "end_leave_remark,end_leave_operator,leave_days_actual,status) values(?,?,?,?,?,?,?)";

        int code = update(insertAResumeWorkBackupInfosql,
                serialnumber,
                person_id,
                end_date,
                end_leave_remarkSTR,
                end_leave_operator,
                leaveDaysActual,
                "使用中");

        return code;
    }

    //插入一条已销假数据操作日志
    public int addAnOperateLogOfResumeWork(Integer serialnumber,
                                               String operatorName,
                                               String operate_type,
                                               String operate_log){

        String  insertAResumeWorkOperateLogInfosql =
                "insert into resume_work_operate_log(serialnumber,operator,operate_date," +
                "operate_type,operate_log) values(?,?,?,?,?)";

        int code = update(insertAResumeWorkOperateLogInfosql,
                serialnumber,
                operatorName,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                operate_type,
                operate_log);

        return code;
    }

    //修改已销假主表中请假数据
    public int updateMainResumeWorkInfo(LeaveInfo newLeaveInfo){
        String sql = "update resume_work set end_date=?,end_leave_remark=?,end_leave_operator=?,leave_days_actual=? " +
                "where serialnumber=?";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(newLeaveInfo.getEnd_date());
        parmas.add(newLeaveInfo.getEnd_leave_remark());
        parmas.add(newLeaveInfo.getEnd_leave_operator());
        parmas.add(newLeaveInfo.getLeave_days_actual());
        parmas.add(newLeaveInfo.getSerialnumber());

        return update(sql,parmas.toArray());
    }

    //修改已销假备份表中请假数据
    public int updateBackupResumeWorkInfo(LeaveInfo newLeaveInfo){
        String sql = "update resume_work_backups set end_date=?,end_leave_remark=?,end_leave_operator=?,leave_days_actual=?," +
                "status=? " +
                "where serialnumber=?";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(newLeaveInfo.getEnd_date());
        parmas.add(newLeaveInfo.getEnd_leave_remark());
        parmas.add(newLeaveInfo.getEnd_leave_operator());
        parmas.add(newLeaveInfo.getLeave_days_actual());
        parmas.add(newLeaveInfo.getStatus());
        parmas.add(newLeaveInfo.getSerialnumber());

        return update(sql,parmas.toArray());
    }

    //根据流水号查询已销假（历史请假记录）表resume_work中的数据
    public LeaveInfo queryResumeWorkInfoBySerialnumber(int serialnumber){
        String sql = "select * from resume_work where serialnumber = ?";
        return queryForOne(LeaveInfo.class,sql,serialnumber);
    }

    //查询所有历史请假记录（已销假数据）（展示历史请假记录用）
    public List<LeaveInfo>  queryALLHistoryInfo(User user,Integer pageNo, Integer pageSize){
        //初始化sql语句
        StringBuilder sql = new StringBuilder("select * from resume_work r,approval_backups a where a.serialnumber=r.serialnumber ");
        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        //判断用户角色
        Role role = userDao.queryRoleInfoByUserId(user.getId());

        if(role != null){
            if(!role.getRole_name().equals("超级管理员") && !role.getRole_name().equals("组织部")){
                //不是超级管理员，根据用户所在单位查询相关记录
                //获取用户单位下的所有person_id
                List<Person> personList = personDao.queryPersonByOffice(user.getOffice());
                List<Integer> personIdList = new ArrayList<>();
                for(Person person: personList){
                    personIdList.add(person.getPerson_id());
                }

                if(personIdList!=null){
                    if(personIdList.size()!=0){
                        //遍历组成person_id集合
                        StringBuilder person_ids = new StringBuilder();
                        int index = 0;
                        for(Object personId : personIdList){
                            if(index == personIdList.size() - 1){
                                person_ids.append("'"+personId+"'");
                            }else {
                                person_ids.append("'"+personId+"',");
                            }
                            index++;
                        }

                        sql.append(" and person_id in("+ person_ids +") ");
                    }else {
                        //如果本单位没有人的话，就返回无数据
                        sql.append(" and person_id=-1 ");
                    }
                }

            }
        }

        //是超级管理员，可以查看所有记录
        sql.append(" order by r.end_date desc");

        if(pageNo!=null && pageSize!=null){
            //需要分页
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            sql.append(" limit ?,?");
            parmas.add(start);
            parmas.add(end);
        }

        return queryForList(LeaveInfo.class,sql.toString(),parmas.toArray());
    }

    //根据流水号删除一条销假记录主表数据
    public int deleteAResumeWorkInfoBySerialnumber(int serialnumber){
        String sql = "delete from resume_work where serialnumber = ?";
        return update(sql,serialnumber);
    }

    //根据序列号修改历史请假记录备份表中数据，主要用于记录删除历史记录时的信息
    public int updateHistoryInfoBackupsBySerialnumber(int serialnumber,
                                                      Date delete_date,
                                                      String delete_operator,
                                                      String delete_reason){
        String sql = "update history_info_backups " +
                "set delete_date=?,delete_operator=?,delete_reason=? "+
                "where serialnumber=?";
        return update(sql,delete_date,delete_operator,delete_reason,serialnumber);
    }

    //按条件查询所有历史请假记录
    public List<LeaveInfo> querySomeHistoryInfo(Map<String, String[]> map,Integer pageNo,Integer pageSize) throws ParseException {
        //遍历并解析map数据
        //初始化日期数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date_maybe_max = null;
        Date start_date_min = null;
        Date start_date_max = null;
        Date end_date_maybe_min = null;
        Date end_date_max = null;
        Date end_date_min = null;

        //初始化人员基本信息数据
        StringBuilder personInfoSQL = new StringBuilder("select * from person_info where 1=1 ");
        String name;
        String nativePlace;
        String phone;
        String level;
        String area_class;
        String office;
        List<Object> personInfoSQLparmas = new ArrayList<Object>();
        //获取符合条件的人员id：person_id
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //姓名
                case "name":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and name like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //籍贯
                case "nativePlace":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nativePlace like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //工作单位
                case "office":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and office = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //类区
                case "area_class":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and area_class = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //职级
                case "level":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and level = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
                //联系电话
                case "phone":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and phone = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
                    }
                    break;
            }
        }

        //根据人员基本信息获取符合条件的人员id集合：personIdList
        List<Person> personList = queryForList(Person.class, personInfoSQL.toString(), personInfoSQLparmas.toArray());
        List<Integer> personIdList = new ArrayList<>();
        for(Person person : personList){
            personIdList.add(person.getPerson_id());
        }

        //执行请假信息查询
        StringBuilder leaveInfoSQL = new StringBuilder("select * from resume_work r,approval_backups a where a.serialnumber=r.serialnumber  ");
        //用于保存可变参数
        List<Object> leaveInfoSQLparmas = new ArrayList<Object>();
        for(Map.Entry<String,String[]> m:map.entrySet()){
            switch (m.getKey()){
                //人员编号:in(?)
                //请假起始地
                case "depart_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and depart_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假到达地
                case "arrive_location":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and arrive_location like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假批准者
                case "approver":
                    if(!m.getValue()[0].trim().isEmpty()){
                        leaveInfoSQL.append(" and approver like ?");
                        leaveInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //请假类型
                case "leave_type":
                    if(!m.getValue()[0].trim().isEmpty()){
                        String[] leave_typeInfos = m.getValue()[0].trim().split(",");
                        StringBuilder leave_type = new StringBuilder();
                        int index = 0;
                        for(String leave_typeInfo:leave_typeInfos){
                            if(index == leave_typeInfos.length - 1){
                                leave_type.append("'"+leave_typeInfo+"'");
                            }else {
                                leave_type.append("'"+leave_typeInfo+"',");
                            }
                            index++;
                        }
                        leaveInfoSQL.append(" and leave_type in("+leave_type+")");
                    }
                    break;
                //请假预计到岗时间最大值
                case "end_date_maybe_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_maybe_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假预计到岗时间最小值
                case "end_date_maybe_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_maybe_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最大值
                case "start_date_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //请假开始时间最小值
                case "start_date_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        start_date_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //实际到岗时间最大值
                case "end_date_max":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_max = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
                //实际到岗时间最小值
                case "end_date_min":
                    if(!m.getValue()[0].trim().isEmpty()){
                        end_date_min = simpleDateFormat.parse(m.getValue()[0].trim());
                    }
                    break;
            }
        }

        //处理预计到岗时间
        if(end_date_maybe_max == null && end_date_maybe_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and end_date_maybe >= ?");
            leaveInfoSQLparmas.add(end_date_maybe_min);
        }
        else if(end_date_maybe_max != null && end_date_maybe_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and end_date_maybe <= ?");
            leaveInfoSQLparmas.add(end_date_maybe_max);
        }
        else if(end_date_maybe_max != null && end_date_maybe_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (end_date_maybe between ? and ?)");
            leaveInfoSQLparmas.add(end_date_maybe_min);
            leaveInfoSQLparmas.add(end_date_maybe_max);
        }

        //处理请假起始日期到岗时间
        if(start_date_max == null && start_date_min != null){
            //预计到岗日期有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and start_date >= ?");
            leaveInfoSQLparmas.add(start_date_min);
        }
        else if(start_date_max != null && start_date_min == null){
            //预计到岗日期有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and start_date <= ?");
            leaveInfoSQLparmas.add(start_date_max);
        }
        else if(start_date_max != null && start_date_min != null){
            //预计到岗日期既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (start_date between ? and ?)");
            leaveInfoSQLparmas.add(start_date_min);
            leaveInfoSQLparmas.add(start_date_max);
        }

        //处理实际到岗时间
        if(end_date_max == null && end_date_min != null){
            //实际到岗时间有最小值无最大值，即查询大于等于的情况
            leaveInfoSQL.append(" and end_date >= ?");
            leaveInfoSQLparmas.add(end_date_min);
        }
        else if(end_date_max != null && end_date_min == null){
            //实际到岗时间有最大值无最小值，即查询小于等于的情况
            leaveInfoSQL.append(" and end_date <= ?");
            leaveInfoSQLparmas.add(end_date_max);
        }
        else if(end_date_max != null && end_date_min != null){
            //实际到岗时间既有最大值又有最小值，即查询介于两者之间的情况
            leaveInfoSQL.append(" and (end_date between ? and ?)");
            leaveInfoSQLparmas.add(end_date_min);
            leaveInfoSQLparmas.add(end_date_max);
        }

        //处理人员编号
        if(personIdList.size() != 0){
            //有人员的信息筛选过程，即选择了人员的基本信息
            StringBuilder person_ids = new StringBuilder();
            int index = 0;
            for(Integer id : personIdList){
                if(index == personIdList.size() - 1){
                    person_ids.append("'"+id+"'");
                }else {
                    person_ids.append("'"+id+"',");
                }
                index++;
            }
            leaveInfoSQL.append(" and r.person_id in("+person_ids+")");
        }
        else{
            leaveInfoSQL.append(" and r.person_id = -1 ");
        }

        //设置排序规则
        leaveInfoSQL.append(" order by r.end_date desc");

        //分页操作
        if(pageSize!=null && pageNo!=null){
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;
            leaveInfoSQL.append(" limit ?,? ");
            leaveInfoSQLparmas.add(start);
            leaveInfoSQLparmas.add(end);
        }

        return queryForList(LeaveInfo.class, leaveInfoSQL.toString(), leaveInfoSQLparmas.toArray());
    }

    //根据流水号查询历史请假记录
    public LeaveInfo queryAHistoryInfo(int serialnumber){
        String sql = "select * from resume_work r,approval_backups a where a.serialnumber=r.serialnumber and r.serialnumber=?";
        return queryForOne(LeaveInfo.class,sql,serialnumber);
    }

    //查询指定日期的前后指定天数待销假记录（待销假提示用）
    public List<LeaveInfo> queryResumeWorkInfoByDate(Date theDate,int days){
        Date newDate = DateUtils.addAndSubtractDays(theDate,days+1);
        String newDateSTR = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
        String sql = "select * from approval where end_date_maybe = ?";
        return queryForList(LeaveInfo.class,sql,newDateSTR);
    }

    //查询当期日期的前后指定天数的待销假记录（待销假提示用）
    public List<LeaveInfo> queryResumeWorkInfoByDate(int days){
        return queryResumeWorkInfoByDate(new Date(),days);
    }
    //按假期种类和人员编号查询并统计本年度此人此类假期总天数与总次数


    //按人员编号查询本年度某种假期统计数据
    public LeaveInfoCount queryThisYearOneLeaveInfoCount(Integer person_id,String leave_type){
        //当前年份
        String currentYear = DateUtils.getCurrentYear();
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) time,leave_type,SUM(leave_days_actual) totalDays" +
                " FROM history_info where person_id=? and " +
                "start_date BETWEEN '"+currentYear+"-1-1' AND '"+currentYear+"-12-31' and leave_type=?" +
                "GROUP BY(leave_type)");

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(person_id);
        parmas.add(leave_type);

        return queryForOne(LeaveInfoCount.class,sql.toString(),parmas.toArray());
    }
    //按人员编号查询本年度所有类型假期统计数据
    public List<LeaveInfoCount> queryThisYearLeaveInfoCount(Integer person_id,Integer pageNo,Integer pageSize){
        //当前年份
        String currentYear = DateUtils.getCurrentYear();
        String sql_str =
                "SELECT COUNT(*) time,leave_type,SUM(leave_days_actual) totalDays "+
                    "FROM "+
                        "(select a.serialnumber,name,office,phone,a.person_id,leave_type,"+
                                "start_date,leave_days_projected,work_leader,leave_reason,approver,depart_location,"+
                                "arrive_location,start_leave_remark,end_date_maybe,start_leave_operator,does_print,"+
                                "end_date,end_leave_remark,end_leave_operator,leave_days_actual "+
                        "from approval_backups a,resume_work r "+
                        "WHERE a.serialnumber=r.serialnumber "+
                        "and  a.person_id=? "+
                        "and a.start_date BETWEEN '"+currentYear+"-1-1' AND '"+currentYear+"-12-31' "+
                        ") as s "+
                    " GROUP BY(leave_type) ";
        StringBuilder sql = new StringBuilder(sql_str);

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(person_id);

        if(pageNo!=null && pageSize!=null){
            //分页查询
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            sql.append( " limit ?,?");
            parmas.add(start);
            parmas.add(end);
        }

        return queryForList(LeaveInfoCount.class,sql.toString(),parmas.toArray());
    }
    //按人员编号查询本年度请假详细数据
    public List<LeaveInfo> queryOnesHistoryInfoByPersonID(Integer person_id,Integer pageNo,Integer pageSize){
        //当前年份
        String currentYear = DateUtils.getCurrentYear();
        StringBuilder sql = new StringBuilder("SELECT * FROM resume_work r,approval_backups a " +
                "where a.serialnumber=r.serialnumber " +
                "and a.person_id=? " +
                "and a.start_date BETWEEN '"+currentYear+"-1-1' AND '"+currentYear+"-12-31' order by end_date desc");
        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(person_id);

        if(pageNo!=null && pageSize!=null){
            //分页查询
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            sql.append( " limit ?,?");
            parmas.add(start);
            parmas.add(end);
        }

        return queryForList(LeaveInfo.class,sql.toString(),parmas.toArray());
    }
    //按人员编号查询本年度某种假期的详细请假数据
    public List<LeaveInfo> queryOnesHistoryInfoOfOneLeaveTypeByPersonID(
            Integer person_id,String leave_type,Integer pageNo,Integer pageSize){
        //当前年份
        String currentYear = DateUtils.getCurrentYear();
        StringBuilder sql = new StringBuilder("SELECT * FROM history_info where person_id=? and " +
                "start_date BETWEEN '"+currentYear+"-1-1' AND '"+currentYear+"-12-31' and leave_type=?" +
                "order by end_date desc");
        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(person_id);
        parmas.add(leave_type);

        if(pageNo!=null && pageSize!=null){
            //分页查询
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            sql.append( " limit ?,?");
            parmas.add(start);
            parmas.add(end);
        }

        return queryForList(LeaveInfo.class,sql.toString(),parmas.toArray());
    }
    //按人员编号查询近期累计在岗天数
    public int queryRecentWorkDaysByPersonID(Integer person_id){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_str = simpleDateFormat.format(new Date());

        String sql = "select datediff(?," +
                "(select end_date from history_info " +
                "where person_id=? ORDER BY end_date limit 0,1)" +
                ")";
        return Integer.parseInt(queryForANumber(sql,date_str,person_id)[0].toString());
    }
    //按人员编号查询单位请假情况
    public List<LeaveInfo> queryOfficeLeaveInfoByPersonId(Integer person_id,Integer pageNo,Integer pageSize){
        Person person = personDao.queryPersonInfoByID(person_id);
        String office_name = person.getOffice();

        String sql = "select * from approval where office=? ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(office_name);
        if(pageNo!=null && pageSize!=null){
            //分页查询
            //分页参数：起始值
            Integer start = (pageNo-1)*pageSize;
            //分页参数：结束值
            Integer end = pageSize;

            stringBuilder.append( " limit ?,?");
            parmas.add(start);
            parmas.add(end);
        }
        return queryForList(LeaveInfo.class,stringBuilder.toString(),parmas.toArray());
    }
    //按人员编号查询单位请假情况，按人的编号分组后统计数量
    public List<OfficeLeaveInfoCount> queryOfficeLeaveInfoByPersonIdGroupPersonId(Integer person_id){
        Person person = personDao.queryPersonInfoByID(person_id);
        String office_name = person.getOffice();
        String sql = "select COUNT(*),person_id from approval where office=? GROUP BY person_id;";
        return queryForList(OfficeLeaveInfoCount.class,sql,office_name);
    }
    //根据人员编号查询此人的请假记录
    public List<LeaveInfo> queryLeaveInfoByPersonId(Integer personID) {
        String sql = "SELECT * FROM approval where person_id = ?";
        return queryForList(LeaveInfo.class,sql,personID);
    }
}
