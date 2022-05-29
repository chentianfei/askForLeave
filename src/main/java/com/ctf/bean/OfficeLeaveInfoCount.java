package com.ctf.bean;

public class OfficeLeaveInfoCount {
    //此人请假的熟练IG
    private String COUNT;
    //人员编号
    private int person_id;

    public OfficeLeaveInfoCount() {
    }

    public OfficeLeaveInfoCount(String COUNT, int person_id) {
        this.COUNT = COUNT;
        this.person_id = person_id;
    }

    @Override
    public String toString() {
        return "OfficeLeaveInfoCount{" +
                "COUNT='" + COUNT + '\'' +
                ", person_id=" + person_id +
                '}';
    }

    public String getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(String COUNT) {
        this.COUNT = COUNT;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
