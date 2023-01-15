package com.pedro.common.enums;

import com.pedro.common.exceptions.ServiceException;

/**
 * 约束类型枚举
 */
public enum RuleTypeEnum {

    NULL_PERCENT_RULE(0, "空缺率"), // appear_ratio有值
    VALUE_APPEAR_TIMES_RESTRICTION(1, "值出现次数"), // value_appear和appear_times有值
    VALUE_APPEAR_RATIO_RESTRICTION(2, "值出现率"), // value_appear和appear_ratio有值
    RANGE_APPEAR_TIMES_RESTRICTION(3, "数值范围出现次数"), // value_range和appear_times有值
    RANGE_APPEAR_RATIO_RESTRICTION(4, "数值范围出现率"); // value_range和appear_ratio有值

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

    public static String castTypeToString(int typeKey) {
        // 遍历查找
        for (RuleTypeEnum enumItem : RuleTypeEnum.values()) {
            if (typeKey == enumItem.type) {
                return enumItem.msg;
            }
        }

        throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
    }

    public static int castTypeToInt(String typeString) {
        // 遍历查找
        for (RuleTypeEnum enumItem : RuleTypeEnum.values()) {
            if (typeString.equals(enumItem.msg)) {
                return enumItem.type;
            }
        }

        throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
    }
}
