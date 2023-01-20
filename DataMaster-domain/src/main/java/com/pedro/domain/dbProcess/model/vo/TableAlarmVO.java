package com.pedro.domain.dbProcess.model.vo;

import java.util.Date;

/**
 * 报警信息VO
 */
public class TableAlarmVO {

    int rid;

    int tid;

    int state;

    Date time;

    Double value;

    public TableAlarmVO() {
    }

    public TableAlarmVO(int rid, int tid, int state, Date time, Double value) {
        this.rid = rid;
        this.tid = tid;
        this.state = state;
        this.time = time;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TableAlarmVO{" +
                "rid=" + rid +
                ", tid=" + tid +
                ", state=" + state +
                ", time=" + time +
                ", value=" + value +
                '}';
    }
}
