package com.ctf.dao;

import com.ctf.bean.*;
import com.ctf.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AskForLeaveDao extends BaseDao {
    private PersonDao personDao = new PersonDao();
    private UserDao userDao = new UserDao();

    //插入请假记录
    public int addLeaveInfoDao(LeaveInfo leaveInfo){
        String sql = "insert into approval(`name`,person_id," +
                "leave_type,start_date,leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator,approval_status,approval_reason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        parmas.add(leaveInfo.getName());
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
        parmas.add(leaveInfo.getApproval_status());
        parmas.add(leaveInfo.getApproval_reason());

        int insertCount = update(sql,parmas.toArray());

        //保存备份数据
        //update(sql2,parmas.toArray());

        return insertCount;
    }

    //查询所有请假记录（展示请假审核用）
    public List<LeaveInfo> queryAllLeaveInfo() {
        String sql = "SELECT * FROM approval order by serialnumber desc";
        return queryForList(LeaveInfo.class,sql);
    }

    //分页查询所有请假记录（展示请假审核用）
    public List<LeaveInfo> queryAllLeaveInfoLimit(int pageNo, int pageSize) {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;
        String sql = "SELECT * FROM approval order by serialnumber desc limit ?,? ";
        return queryForList(LeaveInfo.class,sql,start,end);
    }

    //按条件查询请假记录_统计数量用（搜索请假审核用）
    public List<LeaveInfo> querySomeLeaveInfos(Map<String, String[]> map) throws ParseException {
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
        StringBuilder personInfoSQL = new StringBuilder("select person_id from person_info where 1=1 ");
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
        List<Object> personIdList = queryForOneCol(personInfoSQL.toString(), personInfoSQLparmas.toArray());

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
        }
        else{
            leaveInfoSQL.append(" and person_id = -1 ");
        }

        leaveInfoSQL.append(" order by serialnumber desc");

        return queryForList(LeaveInfo.class,leaveInfoSQL.toString(),leaveInfoSQLparmas.toArray());
    }

    //按条件查询请假记录_分页用（搜索请假审核用）
    public List<LeaveInfo> querySomeLeaveInfosLimit(Map<String, String[]> map,Integer pageNo,Integer pageSize) throws ParseException {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;

        /*根据人员基本信息查询出符合条件的person_id
        在approval表中查询符合这些person_id的leave_info
        在这些已查询出来的leave_info中根据请假信息过滤有用信息并作分页显示
        其中name作模糊查询
        */

        //遍历并解析map数据
        //初始化日期数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date_maybe_max = null;
        Date start_date_min = null;
        Date start_date_max = null;
        Date end_date_maybe_min = null;

        //初始化人员基本信息数据
        StringBuilder personInfoSQL = new StringBuilder("select person_id from person_info where 1=1 ");
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
        List<Object> personIdList = queryForOneCol(personInfoSQL.toString(), personInfoSQLparmas.toArray());

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
        }
        else{
            leaveInfoSQL.append(" and person_id = -1 ");
        }


        //分页操作
        leaveInfoSQL.append(" order by serialnumber desc");
        leaveInfoSQL.append(" limit ?,? ");
        leaveInfoSQLparmas.add(start);
        leaveInfoSQLparmas.add(end);

        return queryForList(LeaveInfo.class, leaveInfoSQL.toString(), leaveInfoSQLparmas.toArray());
    }

    //根据请假索引号解析并返回人员编号
    public Long queryPersonIdBySerialnumber(int serialnumber){
        String sql = "select person_id from approval where serialnumber = ?";
        return (Long)queryForSingleValue(sql,serialnumber);
    }

    //根据请假索引号返回请假信息
    public LeaveInfo queryLeaveInfoBySerialnumber(int serialnumber){
        String sql = "select * from approval where serialnumber = ? ";
        return queryForOne(LeaveInfo.class,sql,serialnumber);
    }

    //查询请假审批数据中的数据并插入待销假记录表resume_work
    public int insertAResumeWorkInfo(int serialnumber){
        String  sql = "insert into resume_work(serialnumber,`name`,person_id,leave_type,start_date," +
                "leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location," +
                "start_leave_remark,end_date_maybe," +
                "start_leave_operator) SELECT serialnumber,`name`,person_id,leave_type,start_date," +
                "leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location," +
                "start_leave_remark,end_date_maybe," +
                "start_leave_operator from approval where serialnumber=?";
        return update(sql,serialnumber);
    }

    //修改请假审核状态为同意
    public int updateApprovalStatusAgree(int serialnumber){
        String sql = "update approval set approval_status='同意',approval_reason='同意' "+
                "where serialnumber=?";
        return update(sql,serialnumber);
    }

    //修改请假审核状态为不同意
    public int updateApprovalStatusNotAgree(int serialnumber,String approval_reason){
        String sql = "update approval set approval_status='不同意',approval_reason=? "+
                "where serialnumber=?";
        return update(sql,approval_reason,serialnumber);
    }

    //查询所有待销假记录（展示待销假信息使用）
    public List<LeaveInfo> queryALLResumeWorkInfo() {
        String sql = "SELECT * FROM resume_work order by end_date_maybe asc";
        return queryForList(LeaveInfo.class,sql);
    }

    //分页查询所有待销假记录（展示待销假信息审核用）
    public List<LeaveInfo> queryALLResumeWorkInfoLimit(int pageNo, int pageSize) {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;

        String sql = "SELECT * FROM resume_work order by end_date_maybe asc limit ?,? ";
        return queryForList(LeaveInfo.class,sql,start,end);
    }

    //按条件查询待销假记录_统计数量用（搜索待销假信息审核用）
    public List<LeaveInfo> querySomeResumeWorkInfo(Map<String, String[]> map) throws ParseException {
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
        StringBuilder personInfoSQL = new StringBuilder("select person_id from person_info where 1=1 ");
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
        List<Object> personIdList = queryForOneCol(personInfoSQL.toString(), personInfoSQLparmas.toArray());

        //执行请假信息查询
        StringBuilder leaveInfoSQL = new StringBuilder("select * from resume_work where 1=1 ");
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
        }
        else{
            leaveInfoSQL.append(" and person_id = -1 ");
        }

        leaveInfoSQL.append(" order by end_date_maybe asc");

        return queryForList(LeaveInfo.class,leaveInfoSQL.toString(),leaveInfoSQLparmas.toArray());
    }

    //按条件查询待销假记录_分页用（搜索待销假信息审核用）
    public List<LeaveInfo> querySomeResumeWorkInfosLimit(Map<String, String[]> map,Integer pageNo,Integer pageSize) throws ParseException {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;

        /*根据人员基本信息查询出符合条件的person_id
        在approval表中查询符合这些person_id的leave_info
        在这些已查询出来的leave_info中根据请假信息过滤有用信息并作分页显示
        其中name作模糊查询
        */

        //遍历并解析map数据
        //初始化日期数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date_maybe_max = null;
        Date start_date_min = null;
        Date start_date_max = null;
        Date end_date_maybe_min = null;

        //初始化人员基本信息数据
        StringBuilder personInfoSQL = new StringBuilder("select person_id from person_info where 1=1 ");
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
        List<Object> personIdList = queryForOneCol(personInfoSQL.toString(), personInfoSQLparmas.toArray());

        //执行请假信息查询
        StringBuilder leaveInfoSQL = new StringBuilder("select * from resume_work where 1=1 ");
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
        }
        else{
            leaveInfoSQL.append(" and person_id = -1 ");
        }


        //分页操作
        leaveInfoSQL.append(" order by end_date_maybe asc");
        leaveInfoSQL.append(" limit ?,? ");
        leaveInfoSQLparmas.add(start);
        leaveInfoSQLparmas.add(end);

        return queryForList(LeaveInfo.class, leaveInfoSQL.toString(), leaveInfoSQLparmas.toArray());
    }

    //查询今日应到假人员信息
    public List<LeaveInfo> queryCurrentEOLPerson(Integer pageNo, Integer pageSize) {
        String sql_str = "SELECT * FROM resume_work where end_date_maybe = ? " +
                "order by end_date_maybe asc ";
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
        StringBuilder sql = new StringBuilder("select * from resume_work where end_date_maybe < '"+ date +
                "' order by end_date_maybe asc");
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
        StringBuilder leaveInfoSQL = new StringBuilder("select * from resume_work where end_date_maybe = ? ");
        //用于保存可变参数
        List<Object> leaveInfoSQLparmas = new ArrayList<Object>();
        //设置初始参数：以当日为基本查询条件
        String end_date_maybe = simpleDateFormat.format(new Date());
        leaveInfoSQLparmas.add(end_date_maybe);

        //查询人员信息的基础语句
        StringBuilder personInfoSQL = new StringBuilder("select person_id from person_info where 1=1 ");
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
        List<Object> personIdList = queryForOneCol(personInfoSQL.toString(), personInfoSQLparmas.toArray());

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
        StringBuilder leaveInfoSQL = new StringBuilder("select * from resume_work " +
                "where end_date_maybe < ? ");

        //用于保存可变参数
        List<Object> leaveInfoSQLparmas = new ArrayList<Object>();
        //设置初始参数：以当日为基本查询条件
        String end_date_maybe = simpleDateFormat.format(new Date());
        leaveInfoSQLparmas.add(end_date_maybe);

        //查询人员信息的基础语句
        StringBuilder personInfoSQL = new StringBuilder("select person_id from person_info where 1=1 ");
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
        List<Object> personIdList = queryForOneCol(personInfoSQL.toString(), personInfoSQLparmas.toArray());

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

    //查询待销假记录表resume_work中指定数据并插入已销假数据表（历史请假记录）history_info及其备份表history_info_backups
    public int insertAHistoryInfo(Integer serialnumber,Integer leaveDaysActual,String end_leave_remarkSTR,
                                  Date end_date, String end_leave_operator){
        String  insertAHistoryInfosql = "insert into history_info(serialnumber,`name`,person_id,leave_type," +
                "start_date,leave_days_projected,work_leader,leave_reason,approver," +
                "depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator," +
                "end_date,end_leave_remark,end_leave_operator,leave_days_actual) " +
                "SELECT serialnumber,`name`,person_id,leave_type,start_date,leave_days_projected," +
                "work_leader,leave_reason,approver,depart_location,arrive_location," +
                "start_leave_remark,end_date_maybe," +
                "start_leave_operator,?,?,?,? from resume_work where serialnumber=?";

        String  insertAHistoryBackupInfosql = "insert into history_info_backups(serialnumber,`name`,person_id,leave_type," +
                "start_date,leave_days_projected,work_leader,leave_reason,approver," +
                "depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator," +
                "end_date,end_leave_remark,end_leave_operator,leave_days_actual) " +
                "SELECT serialnumber,`name`,person_id,leave_type,start_date,leave_days_projected," +
                "work_leader,leave_reason,approver,depart_location,arrive_location," +
                "start_leave_remark,end_date_maybe," +
                "start_leave_operator,?,?,?,? from resume_work where serialnumber=?";

        int code_historyInfo = update(insertAHistoryInfosql,
                end_date,end_leave_remarkSTR,
                end_leave_operator,leaveDaysActual,serialnumber);

        int code_historyInfoBackup = update(insertAHistoryBackupInfosql,end_date,end_leave_remarkSTR,
                end_leave_operator,leaveDaysActual,serialnumber);

        if(code_historyInfo != 1){
            return -1;
        }
        if(code_historyInfoBackup != 1){
            return -2;
        }

        return code_historyInfo + code_historyInfoBackup;

    }

    //根据流水号删除待销假表resume_work中的指定数据
    public int deleteAResumeWorkInfoBySerialnumber(int serialnumber){
        String sql = "delete from resume_work where serialnumber = ?";
        return update(sql,serialnumber);
    }

    //根据流水号查询待销假表resume_work中的数据
    public LeaveInfo queryResumeWorkInfoBySerialnumber(int serialnumber){
        String sql = "select * from resume_work where serialnumber = ?";
        return queryForOne(LeaveInfo.class,sql,serialnumber);
    }

    //根据流水号查询已销假数据表（历史请假记录）history_info中数据
    public LeaveInfo queryHistoryInfoBySerialnumber(int serialnumber){
        String sql = "select * from history_info where serialnumber = ?";
        return queryForOne(LeaveInfo.class,sql,serialnumber);
    }

    //查询所有历史请假记录（展示历史请假记录用）
    public List<LeaveInfo>  queryALLHistoryInfo(User user){
        //初始化sql语句
        StringBuilder sql = new StringBuilder("SELECT * FROM history_info where 1=1 ");
        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        //判断用户角色
        Role role = userDao.queryRoleInfoByUserId(user.getId());

        if(role != null){
            if(!role.getRole_name().equals("超级管理员") && !role.getRole_name().equals("组织部")){
                //不是超级管理员，根据用户所在单位查询相关记录
                //获取用户单位下的所有person_id
                List<Object> personIdList = personDao.queryAllPersonIdByOffice(user.getOffice());
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
        sql.append(" order by serialnumber desc");

        return queryForList(LeaveInfo.class,sql.toString(),parmas.toArray());
    }

    //分页查询所有历史请假记录（展示历史请假记录用）
    public List<LeaveInfo>  queryALLHistoryInfoLimit(int pageNo, int pageSize, User user) {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;

        //初始化sql语句
        StringBuilder sql = new StringBuilder("SELECT * FROM history_info where 1=1 ");
        //用于保存可变参数
        List<Object> parmas = new ArrayList<Object>();
        //判断用户角色
        Role role = userDao.queryRoleInfoByUserId(user.getId());

        if(role != null){
            if(!role.getRole_name().equals("超级管理员") && !role.getRole_name().equals("组织部")){
                //不是超级管理员，根据用户所在单位查询相关记录
                //获取用户单位下的所有person_id
                List<Object> personIdList = personDao.queryAllPersonIdByOffice(user.getOffice());
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
        sql.append(" order by end_date desc limit ?,?");
        parmas.add(start);
        parmas.add(end);

        return queryForList(LeaveInfo.class,sql.toString(),parmas.toArray());
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
        StringBuilder personInfoSQL = new StringBuilder("select person_id from person_info where 1=1 ");
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
        List<Object> personIdList = queryForOneCol(personInfoSQL.toString(), personInfoSQLparmas.toArray());

        //执行请假信息查询
        StringBuilder leaveInfoSQL = new StringBuilder("select * from history_info where 1=1 ");
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
        leaveInfoSQL.append(" order by serialnumber desc");

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
        String sql = "SELECT * FROM history_info where serialnumber=?";
        return queryForOne(LeaveInfo.class,sql,serialnumber);
    }

    //查询指定日期的前后指定天数待销假记录（待销假提示用）
    public List<LeaveInfo> queryResumeWorkInfoByDate(Date theDate,int days){
        Date newDate = DateUtils.addAndSubtractDays(theDate,days+1);
        String newDateSTR = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
        String sql = "select * from resume_work where end_date_maybe = ?";
        return queryForList(LeaveInfo.class,sql,newDateSTR);
    }

    //查询当期日期的前后指定天数的待销假记录（待销假提示用）
    public List<LeaveInfo> queryResumeWorkInfoByDate(int days){
        return queryResumeWorkInfoByDate(new Date(),days);
    }
    //按假期种类和人员编号查询并统计本年度此人此类假期总天数与总次数

    //根据流水号删除一条历史请假记录
    public int deleteAHistoryInfoBySerialnumber(int serialnumber){
        String sql = "delete from history_info where serialnumber = ?";
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
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) time,leave_type,SUM(leave_days_actual) totalDays" +
                " FROM history_info where person_id=? and " +
                "start_date BETWEEN '"+currentYear+"-1-1' AND '"+currentYear+"-12-31' GROUP BY(leave_type)");

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
        StringBuilder sql = new StringBuilder("SELECT * FROM history_info where person_id=? and " +
                "start_date BETWEEN '"+currentYear+"-1-1' AND '"+currentYear+"-12-31' order by end_date desc");
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
}
