package com.pedro.common.enums;

/**
 * 约束类型枚举
 */
public enum RuleTypeEnum {

    NULL_PERCENT_RULE(0,"空缺率控制"),
    VALUE_APPEAR_TIMES_RESTRICTION(1,"值出现次数规则"),
    VALUE_APPEAR_RATIO_RESTRICTION(2,"值出现比例规则"),
    RANGE_APPEAR_TIMES_RESTRICTION(3,"范围内值出现次数规则"),
    RANGE_APPEAR_RATIO_RESTRICTION(4,"范围内值出现比例规则");



    /**
     * 约束类型
     */
    private int type;

    /**
     * 说明
     */
    private String msg;

    RuleTypeEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
