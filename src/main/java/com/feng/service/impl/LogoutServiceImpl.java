package com.feng.service.impl;

import com.feng.entity.LoginUser;
import com.feng.service.LogoutService;
import com.feng.utils.RedisService;
import com.feng.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@SuppressWarnings("all")
@Slf4j
public class LogoutServiceImpl implements LogoutService {

    @Resource
    RedisService redisService;
    @Override
    public Result logout() {
        /**
         * 获取SecurityContext对象中的用户id
         * 当用户发起退出登录的请求时经过JwtAuthenticationTokenFilter过滤器认证时
         * 会将authentication(UsernamePasswordAuthenticationToken)对象存入到SecurityContext中
         * 这里可以通过authentication.getPrincipal()直接获取到LoginUser对象
         */
        UsernamePasswordAuthenticationToken authentication
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        log.info("Principal: "+String.valueOf(authentication.getPrincipal()));

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String id = loginUser.getUser().getId().toString();

        // 删除redis中的值
        redisService.del("login" + id);
        return new Result(true, "注销成功");
    }
}