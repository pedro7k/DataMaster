package com.pedro.domain.dbProcess.model.vo;

import java.util.Date;

/**
 * 大量删除VO
 */
public class MuchDeleteVO {

    private int tid;

    private Date time;

    private int state;

    public MuchDeleteVO() {
    }

    public MuchDeleteVO(int tid, Date time, int state) {
        this.tid = tid;
        this.time = time;
        this.state = state;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}