package com.ctf.bean;

public class Level {

    private String level_name;//职级名称
    private int id;//职级id

    public Level() {
    }

    public Level(String level_name, int id) {
        this.level_name = level_name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Level{" +
                "level_name='" + level_name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
