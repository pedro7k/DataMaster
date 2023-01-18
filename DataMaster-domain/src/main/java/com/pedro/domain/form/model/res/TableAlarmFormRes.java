package com.pedro.domain.form.model.res;

/**
 * 报警页表单展示结果
 */
public class TableAlarmFormRes {

    private int aid;

    private int rid;

    private String columnName;

    private String alarmType;

    private String alarmValue;

    private String time;

    private int ruleWeight;

    private String state;

    public TableAlarmFormRes() {
    }

    public TableAlarmFormRes(int aid, int rid, String columnName, String alarmType, String alarmValue, String time, int ruleWeight, String state) {
        this.aid = aid;
        this.rid = rid;
        this.columnName = columnName;
        this.alarmType = alarmType;
        this.alarmValue = alarmValue;
        this.time = time;
        this.ruleWeight = ruleWeight;
        this.state = state;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRuleWeight() {
        return ruleWeight;
    }

    public void setRuleWeight(int ruleWeight) {
        this.ruleWeight = ruleWeight;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
