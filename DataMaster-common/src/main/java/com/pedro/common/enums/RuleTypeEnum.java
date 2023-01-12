package com.pedro.common.enums;

/**
 * 约束类型枚举
 */
public enum RuleTypeEnum {

    NULL_PERCENT_RULE(0,"空缺率控制"), // appear_ratio有值
    VALUE_APPEAR_TIMES_RESTRICTION(1,"值出现次数规则"), // value_appear和appear_times有值
    VALUE_APPEAR_RATIO_RESTRICTION(2,"值出现比例规则"), // value_appear和appear_ratio有值
    RANGE_APPEAR_TIMES_RESTRICTION(3,"范围内值出现次数规则"), // value_range和appear_times有值
    RANGE_APPEAR_RATIO_RESTRICTION(4,"范围内值出现比例规则"); // value_range和appear_ratio有值



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
