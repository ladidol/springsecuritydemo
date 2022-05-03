package com.feng.handler.authentication;

import com.alibaba.fastjson.JSON;
import com.feng.utils.WebUtils;
import com.feng.utils.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        Result<Integer> result = new Result<Integer>(HttpStatus.UNAUTHORIZED.value(), false,"用户认证失败，请检查用户名或密码是否正确");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}