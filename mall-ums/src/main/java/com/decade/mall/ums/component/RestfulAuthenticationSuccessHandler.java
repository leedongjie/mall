package com.decade.mall.ums.component;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.decade.mall.ums.util.Result;
import com.decade.mall.ums.util.ResultCodeEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 顾名思义,这个类是认证成功之后的处理逻辑
 * 在前后端分离系统中,认证成功之后,会返回一段JSON数据,而非进行页面跳转
 */
@Component
public class RestfulAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Result result = new Result(ResultCodeEnum.SUCCESS, null);
        JSON parse = JSONUtil.parse(result);
        writer.write(parse.toString());
        writer.flush();
        writer.close();
    }
}
