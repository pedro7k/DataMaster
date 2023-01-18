package com.pedro.domain.form.model.vo;

import java.util.Date;

/**
 * 报警表表单VO
 */
public class TableAlarmFormVO {

    private int aid;

    private int rid;

    private int state;

    private Date time;

    private String value;

    public TableAlarmFormVO() {
    }

    public TableAlarmFormVO(int aid, int rid, int state, Date time, String value) {
        this.aid = aid;
        this.rid = rid;
        this.state = state;
        this.time = time;
        this.value = value;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
