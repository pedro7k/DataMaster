package com.pedro.common.enums;

/**
 * 用户角色枚举
 */
public enum UserRoleEnum {

    ROOT(0,"ROOT用户"),
    ADMIN(1,"管理员用户"),
    NORMAL_USER(2,"普通用户");

    /**
     * 权限等级
     */
    private int level;
    /**
     * 权限信息
     */
    private String msg;

    UserRoleEnum(int level, String msg) {
        this.level = level;
        this.msg = msg;
    }

    public int getLevel() {
        return level;
    }

    public String getMsg() {
        return msg;
    }
}
