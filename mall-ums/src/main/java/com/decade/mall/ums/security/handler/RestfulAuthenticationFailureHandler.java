package com.decade.mall.ums.security.handler;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.decade.mall.ums.util.CommonResult;
import com.decade.mall.ums.util.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestfulAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        CommonResult failure = CommonResult.failure(ResultCodeEnum.ACCESS_UNAUTHORIZED, exception.getMessage());
        JSON parse = JSONUtil.parse(failure);
        writer.write(parse.toString());
        writer.flush();
        writer.close();
    }
}
