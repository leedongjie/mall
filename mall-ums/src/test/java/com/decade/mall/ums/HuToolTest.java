package com.decade.mall.ums;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.decade.mall.ums.util.Result;
import com.decade.mall.ums.util.ResultCodeEnum;
import org.junit.jupiter.api.Test;

public class HuToolTest {

    @Test
    public void jsonTest() {
        Result result = new Result(ResultCodeEnum.SUCCESS, null);
        JSON parse = JSONUtil.parse(result);
        System.out.println(parse);
    }
}
