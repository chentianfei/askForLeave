package com.ctf.bean;
/**
 * @ClassName: Nation
 * @Package: com.ctf.bean
 * @Description：民族Bean类
 * @Author: CTF
 * @Date：2021/11/14 16:15
 */

public class Nation {
    //民族名称
    private String nation_name;

    public Nation() {
    }

    public Nation(String nation_name) {
        this.nation_name = nation_name;
    }

    @Override
    public String toString() {
        return "Nation{" +
                "nation_name='" + nation_name + '\'' +
                '}';
    }

    public String getNation_name() {
        return nation_name;
    }

    public void setNation_name(String nation_name) {
        this.nation_name = nation_name;
    }
}
