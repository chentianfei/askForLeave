package com.ctf.bean;

public class LeaveType {

    private String leave_type;

    public LeaveType() {
    }

    public LeaveType(String leave_type) {
        this.leave_type = leave_type;
    }

    @Override
    public String toString() {
        return "LeaveType{" +
                "leave_type='" + leave_type + '\'' +
                '}';
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }
}
