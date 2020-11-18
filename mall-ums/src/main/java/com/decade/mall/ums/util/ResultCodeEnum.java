package com.decade.mall.ums.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回结果状态码与消息枚举
 * 参考编程规范中命名风格17条
 * 推荐:枚举类名带上Enum后缀，枚举成员名称需要全大写，单词间用下划线隔开
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    // 成功状态码
    SUCCESS("00000", "成功"),

    // 失败状态码
    FAILURE("B0001", "系统执行出错"),
    ACCESS_DENIED("B0002", "访问被拒绝"),
    AUTHENTICATION_FAILURE("444", "认证失败了");

    private final String code;

    private final String message;

}
