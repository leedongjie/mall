package com.decade.mall.ums.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一结果封装
 */
@Data
public class CommonResult implements Serializable {

    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public CommonResult(ResultCodeEnum resultCodeEnum, Object data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }

    public CommonResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult success(String message, Object data) {
        return new CommonResult("00000", message, data);
    }

    public static CommonResult success(Object data) {
        return new CommonResult(ResultCodeEnum.SUCCESS, data);
    }

    public static CommonResult failure(ResultCodeEnum resultCodeEnum) {
        return new CommonResult(resultCodeEnum, null);
    }

    public static CommonResult failure(ResultCodeEnum resultCodeEnum, Object data) {
        return new CommonResult(resultCodeEnum, data);
    }

}
