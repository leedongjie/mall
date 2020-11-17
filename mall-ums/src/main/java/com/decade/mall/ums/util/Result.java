package com.decade.mall.ums.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一结果封装
 */
@Data
public class Result implements Serializable {

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

    public Result(ResultCodeEnum resultCodeEnum, Object data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }

    public static Result success(ResultCodeEnum resultCodeEnum) {
        return new Result(resultCodeEnum, null);
    }

    public static Result success(ResultCodeEnum resultCodeEnum, Object data) {
        return new Result(resultCodeEnum, data);
    }

    public static Result failure(ResultCodeEnum resultCodeEnum) {
        return new Result(resultCodeEnum, null);
    }

    public static Result failure(ResultCodeEnum resultCodeEnum, Object data) {
        return new Result(resultCodeEnum, data);
    }

}
