package com.decade.mall.ums.security.handler;

import cn.hutool.json.JSONUtil;
import com.decade.mall.ums.util.CommonResult;
import com.decade.mall.ums.util.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户没有权限时的处理
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        CommonResult failure = CommonResult.failure(ResultCodeEnum.ACCESS_UNAUTHORIZED);
        response.getWriter().println(JSONUtil.parse(failure));
        response.getWriter().flush();
    }
}
