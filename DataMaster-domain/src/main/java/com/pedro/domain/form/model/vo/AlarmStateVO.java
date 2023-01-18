package com.pedro.domain.form.model.vo;

/**
 * 报警状态VO
 */
public class AlarmStateVO {

    int aid;

    int state;

    public AlarmStateVO() {
    }

    public AlarmStateVO(int aid, int state) {
        this.aid = aid;
        this.state = state;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
