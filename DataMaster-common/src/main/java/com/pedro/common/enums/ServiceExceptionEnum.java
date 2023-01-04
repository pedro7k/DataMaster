package com.pedro.common.enums;

// ServiceExceptionEnum.java

/**
 * 返回状态枚举
 */
public enum ServiceExceptionEnum {

    /**
     * 服务异常
     *
     * 一共 10 位，分成三段
     *
     * 第一段，1 位，类型
     *      1 - 业务级别异常
     *      2 - 系统级别异常
     * 第二段，3 位，系统类型
     *      001 - 用户系统
     *      002 - 商品系统
     *      003 - 订单系统
     *      004 - 支付系统
     *      005 - 优惠劵系统
     *      ... - ...
     * 第三段，3 位，模块
     *      不限制规则。
     *      一般建议，每个系统里面，可能有多个模块，可以再去做分段。以用户系统为例子：
     *          001 - OAuth2 模块
     *          002 - User 模块
     *          003 - MobileCode 模块
     * 第四段，3 位，错误码
     *       不限制规则。
     *       一般建议，每个模块自增。
     */

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
     * User模块:01
     */
    REPEAT_PASSWORD_ERROR(0101,"注册时重复密码错误"),
    UNKNOWN_ACCOUNT_ERROR(0102,"登陆时用户名不存在"),
    INCORRECT_PASSWORD(0103,"登录时密码错误"),
    DUPLICATE_USERNAME(0104,"注册时用户名重复");

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