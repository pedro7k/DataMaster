package com.pedro.common.enums;

// ServiceExceptionEnum.java

/**
 * 返回状态枚举
 */
public enum ServiceExceptionEnum {

    /**
     * ===========系统级别===========
     * 共1位
     */
    SUCCESS(0, "成功"),
    SYS_ERROR(1, "服务端发生异常"),
    NO_RESULT(2, "查询不到结果"),

    /**
     * ===========业务级别===========
     * 共6位，前2位代表模块，后2位代表错误码
     * - user模块:01
     * - form模块:02
     * - score模块:03
     */
    // 01 user模块
    REPEAT_PASSWORD_ERROR(0101, "注册时重复密码错误"),
    UNKNOWN_ACCOUNT_ERROR(0102, "登陆时用户名不存在"),
    INCORRECT_PASSWORD(0103, "登录时密码错误"),
    DUPLICATE_USERNAME(0104, "注册时用户名重复"),
    // 02 form模块
    DELETE_USER_ERROR(0201, "删除用户时出现异常"),
    EDIT_USER_ROLE_ERROR(0202,"编辑用户权限时出现异常"),
    // 03 score模块
    HEALTH_SCORE_ILLEGAL_ERROR(0301,"健康分数据不合法"),
    HEALTH_SCORE_NUM_ERROR(0302,"健康分数据量异常");

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