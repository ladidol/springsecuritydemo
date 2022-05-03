package com.feng.handler.authentication;

import com.alibaba.fastjson.JSON;
import com.feng.utils.WebUtils;
import com.feng.utils.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        Result<Integer> result = new Result<Integer>(HttpStatus.FORBIDDEN.value(), false,"权限不够!");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);

    }
}