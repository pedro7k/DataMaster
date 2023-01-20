package com.pedro.domain.score.model.vo;

import java.util.Date;

public class ThIdVO {

    int thId;

    Date nowTime;

    public ThIdVO() {
    }

    public ThIdVO(int thId, Date nowTime) {
        this.thId = thId;
        this.nowTime = nowTime;
    }

    public int getThId() {
        return thId;
    }

    public void setThId(int thId) {
        this.thId = thId;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }
}
