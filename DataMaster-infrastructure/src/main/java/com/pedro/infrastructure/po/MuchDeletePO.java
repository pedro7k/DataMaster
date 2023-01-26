package com.pedro.infrastructure.po;

import java.util.Date;

/**
 * 大量删除PO
 */
public class MuchDeletePO {

    private int mid;

    private int tid;

    private Date time;

    private Integer state;

    public MuchDeletePO() {
    }

    public MuchDeletePO(int mid, int tid, Date time, int state) {
        this.mid = mid;
        this.tid = tid;
        this.time = time;
        this.state = state;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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
