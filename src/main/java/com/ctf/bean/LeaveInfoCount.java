package com.ctf.bean;

public class LeaveInfoCount {
    //假期类型
    private String leave_type;
    //本年度请假次数
    private int time;
    //本年度该类型假期请假总天数
    private int totalDays;

    public LeaveInfoCount() {
    }

    public LeaveInfoCount(String leave_type, int time, int totalDays) {
        this.leave_type = leave_type;
        this.time = time;
        this.totalDays = totalDays;
    }

    @Override
    public String toString() {
        return "LeaveInfoCount{" +
                "leave_type='" + leave_type + '\'' +
                ", time=" + time +
                ", totalDays=" + totalDays +
                '}';
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }
}
