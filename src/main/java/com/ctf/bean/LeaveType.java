package com.ctf.bean;

public class LeaveType {

    private String leave_type;
    private int id;

    public LeaveType() {
    }

    public LeaveType(String leave_type, int id) {
        this.leave_type = leave_type;
        this.id = id;
    }

    @Override
    public String toString() {
        return "LeaveType{" +
                "leave_type='" + leave_type + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }
}
