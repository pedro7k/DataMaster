package com.pedro.common.enums;

/**
 * 报警状态枚举
 */
public enum AlarmStateEnum {

    WAITING_PROCESS(1,"等待处理"),
    UNDER_PROCESS(2,"正在处理"),
    FINISH_PROCESS(3,"处理完成");

    /**
     * 报警状态
     */
    private int state;

    /**
     * 说明
     */
    private String msg;

    AlarmStateEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }
}
