package com.pedro.common.exceptions;

// ServiceException.java


import com.pedro.common.res.ServiceExceptionEnum;

public final class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer status;

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        // 使用父类的 message 字段
        super(serviceExceptionEnum.getMsg());
        // 设置错误码
        this.status = serviceExceptionEnum.getStatus();
    }

    public Integer getStatus() {
        return status;
    }
}
