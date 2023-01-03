package com.pedro.common.res;

// ServiceExceptionEnum.java

/**
 * 返回状态枚举
 */
public enum ServiceExceptionEnum {

    SUCCESS(0, "成功"),
    SYS_ERROR(1, "服务端发生异常"),
    NO_RESULT(2, "查询不到结果"),
    REPEAT_PASSWORD_ERROR(3,"注册时重复密码错误"),
    UNKNOWN_ACCOUNT_ERROR(4,"用户名错误"),
    INCORRECT_PASSWORD(5,"密码错误");

    /**
     * 错误码
     */
    private int status;
    /**
     * 错误提示
     */
    private String msg;

    ServiceExceptionEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}