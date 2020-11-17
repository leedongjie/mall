package com.decade.mall.ums.service.impl;

import com.decade.mall.ums.service.MemberService;
import com.decade.mall.ums.service.RedisService;
import com.decade.mall.ums.util.Result;
import com.decade.mall.ums.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * 会员管理Service实现类
 */
@Service
public class MemberServiceImpl implements MemberService {

    private final RedisService redisService;

    @Autowired
    public MemberServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    private final String REDIS_KEY_PREFIX_AUTH_CODE = "portal:authCode:";

    @SuppressWarnings("FieldCanBeLocal")
    private final Long AUTH_CODE_EXPIRE_SECONDS = 120L;

    @Override
    public Result generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return Result.success(ResultCodeEnum.SUCCESS, sb.toString());
    }


    //对输入的验证码进行校验
    @Override
    public Result verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return Result.failure(ResultCodeEnum.FAILURE);
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return Result.success(ResultCodeEnum.SUCCESS);
        } else {
            return Result.failure(ResultCodeEnum.FAILURE);
        }
    }

}
