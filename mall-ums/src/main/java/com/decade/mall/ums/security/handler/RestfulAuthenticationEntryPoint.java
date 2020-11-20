package com.decade.mall.ums.security.handler;

import cn.hutool.json.JSONUtil;
import com.decade.mall.ums.util.CommonResult;
import com.decade.mall.ums.util.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录或者Token失效
 */
@Component
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        CommonResult failure = CommonResult.failure(ResultCodeEnum.USER_NOT_LOGIN);
        response.getWriter().println(JSONUtil.parse(failure));
        response.getWriter().flush();
    }
}
