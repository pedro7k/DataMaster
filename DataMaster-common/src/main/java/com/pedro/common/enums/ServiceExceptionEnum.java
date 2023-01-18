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
    SUCCESS("0", "成功"),
    SYS_ERROR("1", "服务端发生异常"),
    NO_RESULT("2", "查询不到结果"),
    ROLE_DENIED("3", "权限不足"),

    /**
     * ===========业务级别===========
     * 共6位，前2位代表模块，后2位代表错误码
     * - user模块:01
     * - form模块:02
     * - score模块:03
     * - dbProcess模块:04
     */
    // 01 user模块
    REPEAT_PASSWORD_ERROR("0101", "注册时重复密码错误"),
    UNKNOWN_ACCOUNT_ERROR("0102", "登陆时用户名不存在"),
    INCORRECT_PASSWORD("0103", "登录时密码错误"),
    DUPLICATE_USERNAME("0104", "注册时用户名重复"),
    // 02 form模块
    DELETE_USER_ERROR("0201", "删除用户时出现异常"),
    EDIT_USER_ROLE_ERROR("0202", "编辑用户权限时出现异常"),
    INSERT_EXCEPT_TABLE_ERROR("0203", "新增except表失败"),
    EDIT_TABLE_WEIGHT_ERROR("0204","编辑表单权重时出现异常"),
    STORAGE_RULE_TYPE_ERROR("0205","数据库中存储的约束类型有误"),
    EDIT_RULE_WEIGHT_ERROR("0206","编辑约束权重时出现异常"),
    DELETE_RULE_ERROR("0207","删除约束时出现异常"),
    RULE_TYPE_ERROR_CREATION("0208","创建约束使用了不适用的约束类型"),
    RULE_VALUE_ERROR("0209","创建约束时约束细节值异常"),
    EDIT_ALARM_STATE_ERROR("0210","编辑报警状态时出现异常"),
    DELETE_ALARM_ERROR("0211","删除报警时出现异常"),
    // 03 score模块
    HEALTH_SCORE_ILLEGAL_ERROR("0301", "健康分数据不合法"),
    HEALTH_SCORE_NUM_ERROR("0302", "健康分数据量异常"),
    // 04 dbProcess模块
    CREATE_TABLE_ERROR("0401","创建表时出现异常"),
    EMPTY_COLUMN_LIST("0402","创建表时列集合为空"),
    DUPLICATE_PRIMARY_KEY("0403","主键不允许重复"),
    PRIMARY_KEY_WITH_DEFAULT_VALUE("0404","主键不允许有默认值"),
    DUPLICATE_TABLE_NAME("0405","表名重复"),
    RULE_TYPE_ERROR("0406","不适用的约束类型");

    /**
     * 错误码
     */
    private String status;
    /**
     * 错误提示
     */
    private String msg;

    ServiceExceptionEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}