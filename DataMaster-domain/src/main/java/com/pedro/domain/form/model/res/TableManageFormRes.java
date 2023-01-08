package com.pedro.domain.form.model.res;

/**
 * 表单管理VO
 */
public class TableManageFormRes {

    int tid;

    String name;

    Double score;

    int alarmTimes;

    int weight;

    public TableManageFormRes() {
    }

    public TableManageFormRes(int tid, String name, Double score, int alarmTimes, int weight) {
        this.tid = tid;
        this.name = name;
        this.score = score;
        this.alarmTimes = alarmTimes;
        this.weight = weight;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getAlarmTimes() {
        return alarmTimes;
    }

    public void setAlarmTimes(int alarmTimes) {
        this.alarmTimes = alarmTimes;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
