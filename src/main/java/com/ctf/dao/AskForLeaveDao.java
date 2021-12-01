package com.ctf.dao;

import com.ctf.bean.LeaveInfo;
import com.ctf.bean.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AskForLeaveDao extends BaseDao {
    PersonDao personDao = new PersonDao();

    //插入请假记录
    public int addLeaveInfoDao(LeaveInfo leaveInfo){
        String sql = "insert into approval(`name`,person_id," +
                "leave_type,start_date,leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator,approval_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String sql2 = "insert into approval_backups(`name`,person_id," +
                "leave_type,start_date,leave_days_projected,work_leader,leave_reason," +
                "approver,depart_location,arrive_location,start_leave_remark,end_date_maybe," +
                "start_leave_operator,approval_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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

        int insertCount = update(sql,parmas.toArray());

        //保存备份数据
        //update(sql2,parmas.toArray());

        return insertCount;
    }



    //按人员编号查询本年度请假情况统计数据
    //按人员编号查询本年度请假详细数据
    //按人员编号查询近期累计在岗天数
    //查询所有请假记录（展示请假审核用）
    public List<LeaveInfo> queryAllLeaveInfo() {
        String sql = "SELECT * FROM approval";
        return queryForList(LeaveInfo.class,sql);
    }

    //分页查询所有请假记录（展示请假审核用）
    public List<LeaveInfo> queryAllLeaveInfoLimit(int pageNo, int pageSize) {
        //分页参数：起始值
        Integer start = (pageNo-1)*pageSize;
        //分页参数：结束值
        Integer end = pageSize;

        String sql = "SELECT * FROM approval limit ?,?";
        return queryForList(LeaveInfo.class,sql,start,end);
    }

    //按条件查询请假记录（搜索请假审核用）
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
                        name = "%"+m.getValue()[0].trim()+"%";
                        personInfoSQL.append(" and name like ?");
                        personInfoSQLparmas.add("%"+m.getValue()[0].trim()+"%");
                    }
                    break;
                //籍贯
                case "nativePlace":
                    if(!m.getValue()[0].trim().isEmpty()){
                        personInfoSQL.append(" and nativePlace = ?");
                        personInfoSQLparmas.add(m.getValue()[0].trim());
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
                case "level area_class":
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

        final Object o = queryForSingleValue(personInfoSQL.toString(), personInfoSQLparmas.toArray());

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
                        leaveInfoSQL.append(" and leave_type in(?)");
                        String[] leave_typeInfos = m.getValue()[0].trim().split(",");
                        StringBuilder leave_type = new StringBuilder("");
                        for(String leave_typeInfo:leave_typeInfos){
                            leave_type.append(leave_typeInfo);
                        }
                        leaveInfoSQLparmas.add(leave_type);
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

        System.out.println(leaveInfoSQL);
        System.out.println(leaveInfoSQLparmas);

        System.out.println(queryForList(LeaveInfo.class,leaveInfoSQL.toString(),leaveInfoSQLparmas.toArray()));

        return queryForList(LeaveInfo.class,leaveInfoSQL.toString(),leaveInfoSQLparmas.toArray());
    }



    //查询所有待销假记录（展示销假使用）
    //按条件查询待销假记录（搜索销假使用）
    //查询指定日期的待销假记录（待销假提示用）
    //查询所有历史请假记录（展示历史请假记录用）
    //按条件查询历史请假记录（搜索历史请假记录用）
    //按假期种类和人员编号查询并统计本年度此人此类假期总天数与总次数
    //删除历史请假记录
    //查询并返回当前某种假期可请假天数
}
