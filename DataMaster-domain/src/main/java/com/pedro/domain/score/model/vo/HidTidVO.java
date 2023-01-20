package com.pedro.domain.score.model.vo;

import java.util.Date;

public class HidTidVO {

    int hid;

    int tid;

    Date nowTime;

    public HidTidVO() {
    }

    public HidTidVO(int hid, int tid, Date nowTime) {
        this.hid = hid;
        this.tid = tid;
        this.nowTime = nowTime;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }
}
