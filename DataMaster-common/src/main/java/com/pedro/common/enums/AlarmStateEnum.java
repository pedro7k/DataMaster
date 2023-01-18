package com.pedro.common.enums;

import com.pedro.common.exceptions.ServiceException;

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

    public static String castTypeToString(int typeKey) {
        // 遍历查找
        for (AlarmStateEnum enumItem : AlarmStateEnum.values()) {
            if (typeKey == enumItem.state) {
                return enumItem.msg;
            }
        }

        throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
    }

    public static int castTypeToInt(String typeString) {
        // 遍历查找
        for (AlarmStateEnum enumItem : AlarmStateEnum.values()) {
            if (typeString.equals(enumItem.msg)) {
                return enumItem.state;
            }
        }

        throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
    }
}
