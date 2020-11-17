package com.decade.mall.ums.service;

import com.decade.mall.ums.util.Result;

public interface MemberService {

    /**
     * 生成验证码
     */
    Result generateAuthCode(String telephone);

    /**
     * 验证验证码
     */
    Result verifyAuthCode(String telephone, String authCode);

}
