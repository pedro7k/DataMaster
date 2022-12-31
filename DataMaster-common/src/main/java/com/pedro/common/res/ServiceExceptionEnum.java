package com.pedro.common.res;

// ServiceExceptionEnum.java

public enum ServiceExceptionEnum {

    SUCCESS(0, "成功"),
    SYS_ERROR(1, "服务端发生异常"),
    NO_RESULT(2, "查询不到结果");

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