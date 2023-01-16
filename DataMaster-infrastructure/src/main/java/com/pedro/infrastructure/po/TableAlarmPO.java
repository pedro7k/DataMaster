package com.pedro.infrastructure.po;

import java.sql.Date;

/**
 * 报警表PO
 */
public class TableAlarmPO {

    private int aid;

    private int rid;

    private int tid;

    private int state;

    private Date time;

    private String value;

    public TableAlarmPO() {
    }

    public TableAlarmPO(int aid, int rid, int tid, int state, Date time, String value) {
        this.aid = aid;
        this.rid = rid;
        this.tid = tid;
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
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
