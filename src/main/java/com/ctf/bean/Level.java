package com.ctf.bean;

public class Level {

    private String level_name;//职级名称

    public Level() {
    }

    public Level(String level_name) {
        this.level_name = level_name;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    @Override
    public String toString() {
        return "Level{" +
                "level_name='" + level_name + '\'' +
                '}';
    }
}
