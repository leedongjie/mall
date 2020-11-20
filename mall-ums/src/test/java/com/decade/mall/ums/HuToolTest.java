package com.decade.mall.ums;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.decade.mall.ums.util.CommonResult;
import com.decade.mall.ums.util.ResultCodeEnum;
import org.junit.jupiter.api.Test;

public class HuToolTest {

    @Test
    public void jsonTest() {
        CommonResult commonResult = new CommonResult(ResultCodeEnum.SUCCESS, null);
        JSON parse = JSONUtil.parse(commonResult);
        System.out.println(parse);
    }
}
