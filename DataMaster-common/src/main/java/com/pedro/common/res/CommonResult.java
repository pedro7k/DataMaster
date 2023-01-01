package com.pedro.common.res;

// CommonResult.java

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 统一返回结果
 * @param <T>
 */
public class CommonResult<T> implements Serializable {

    public static Integer CODE_SUCCESS = 0;

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 错误提示
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getStatus(), result.getMsg());
    }

    public static <T> CommonResult<T> error(Integer status, String msg) {
        Assert.isTrue(!CODE_SUCCESS.equals(status), "status 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.status = status;
        result.msg = msg;
        return result;
    }

    // 包装成功的Result
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.status = CODE_SUCCESS;
        result.data = data;
        result.msg = "成功！";
        return result;
    }

    @JsonIgnore // 忽略，避免 jackson 序列化给前端
    public boolean isSuccess() { // 方便判断是否成功
        return CODE_SUCCESS.equals(status);
    }

    @JsonIgnore // 忽略，避免 jackson 序列化给前端
    public boolean isError() { // 方便判断是否失败
        return !isSuccess();
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}