package com.pedro.domain.dbProcess.model.vo;

import java.util.Date;

/**
 * 大量删除VO
 */
public class MuchDeleteVO {

    private int tid;

    private Date time;

    public MuchDeleteVO() {
    }

    public MuchDeleteVO(int tid, Date time) {
        this.tid = tid;
        this.time = time;
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
}
